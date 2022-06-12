package dds.grupo4.tpimpacto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ConfigurationPropertiesScan
public class Main {

    public static void main(String[] args) throws Exception {
        // Esto va al SpringCommandLineRunner.run() y le inyecta todas las dependencias a traves del constructor
        // (por ejemplo los Controllers)
        SpringApplication.run(Main.class, args);
    }
}
