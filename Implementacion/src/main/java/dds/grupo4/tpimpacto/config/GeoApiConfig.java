package dds.grupo4.tpimpacto.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

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
