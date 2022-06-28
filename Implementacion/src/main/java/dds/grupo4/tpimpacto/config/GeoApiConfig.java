package dds.grupo4.tpimpacto.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "geoapi")
@ConstructorBinding
@Getter
@Setter
public class GeoApiConfig {

    private final String token;

    public GeoApiConfig(String token) {
        this.token = token;
    }

}
