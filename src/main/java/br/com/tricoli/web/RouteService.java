package br.com.tricoli.web;

import br.com.tricoli.core.entity.CostException;
import br.com.tricoli.core.entity.Map;
import br.com.tricoli.core.entity.RouteCost;
import br.com.tricoli.core.repository.Maps;
import br.com.tricoli.core.route.RouteNotFoundException;
import br.com.tricoli.core.route.ShorterRoute;
import br.com.tricoli.shortest.route.DijkstrasRoute;
import br.com.tricoli.shortest.route.Graph;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@Path("rotas")
public class RouteService {

    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private Maps maps;

    @Inject
    public RouteService(Maps maps) {
        this.maps = maps;
    }

    @GET
    @Path("{mapa}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RouteCost findShortestRoute(@PathParam("mapa") String mapa,
                   @QueryParam("origem") String source,
                   @QueryParam("destino") String target,
                   @QueryParam("autonomia") BigDecimal performance,
                   @QueryParam("valor_litro") BigDecimal value) {

        log.debug(String.format("mapa=" + mapa +
                " origem=" + source +
                " destino=" + target +
                " autonomia=" + performance +
                " valor_litro=" + value ));

        if (StringUtils.isBlank(mapa) || StringUtils.isBlank(source) || StringUtils.isBlank(target) || performance == null || value == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).
                    entity("Obrigatório enviar os parâmetros: mapa, origem, destino, autonomia, valor_litro").type("text/plain").build());
        }

        Map m = maps.get(mapa);

        ShorterRoute shortest = null;
        try {
            DijkstrasRoute route = new DijkstrasRoute(new Graph(m.getDistances()));
            shortest = route.from(source).to(target);
        } catch (RouteNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).
                    entity("Não há rota de %s a %s").type("text/plain").build());
        }

        log.debug(shortest.getUtmost().toString());
        try {
            return new RouteCost(shortest.getFullPath(), shortest.getUtmost(), performance, value);
        } catch (CostException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).type("text/plain").build());
        }
    }

}
