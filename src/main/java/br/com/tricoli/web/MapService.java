package br.com.tricoli.web;

import br.com.tricoli.core.entity.Map;
import br.com.tricoli.core.repository.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@Path("mapas")
public class MapService {

    private static final Logger log = LoggerFactory.getLogger(MapService.class);
    private Maps maps;

    @Inject
    public MapService(Maps maps) {
        this.maps = maps;
    }

    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map map(@PathParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).
                    entity("Obrigatório enviar o nome do mapa").type("text/plain").build());
        }

        Map m = maps.get(name);

        if(m.getDistances().isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return m;
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response add(Map map) {
        if (map == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).
                    entity("Obrigatório um JSON como: {\"name\":\"Mapa\",\"distances\":[{\"source\":\"A\",\"target\":\"B\",\"length\":5}]}").type("text/plain").build());
        }

        try {
            maps.add(map);
        } catch (SQLException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).type("text/plain").build());
        }

        URI resourceUri = URI.create(String.format("/mapas/%s", map.getName()));

        return Response.created(resourceUri).build();
    }

}
