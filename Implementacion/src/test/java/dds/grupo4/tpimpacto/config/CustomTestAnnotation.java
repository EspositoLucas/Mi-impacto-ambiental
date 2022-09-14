package dds.grupo4.tpimpacto.config;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public @interface CustomTestAnnotation {
    /*
     * Las clases de tests tienen que tener todas estas anotaciones, asi que para no tener que
     *  repetirlas todo el tiempo se puede usar esta meta-anotacion, que aplica todas las anotaciones
     *  correspondientes directamente
     */
}
