package dds.grupo4.tpimpacto.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "geoapi")
@ConstructorBinding
public class GeoApiConfig {

    private final String token;

    public GeoApiConfig(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
