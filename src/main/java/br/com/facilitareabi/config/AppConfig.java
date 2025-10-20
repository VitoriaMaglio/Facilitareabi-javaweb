package br.com.facilitareabi.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

    public AppConfig(){

        packages("br.com.facilitareabi.resource");
        register(CorsFilter.class);
    }
}
