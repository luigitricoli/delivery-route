package br.com.tricoli.web.provider;

import br.com.tricoli.web.MapService;
import br.com.tricoli.web.RouteService;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by luigitricoli on 8/4/15.
 */
@ApplicationPath("/")
public class FirstVersion extends ResourceConfig {

    public FirstVersion() {
        register(MoxyJsonFeature.class);
        register(MoxyXmlFeature.class);
        register(MapService.class);
        register(RouteService.class);
    }

}
