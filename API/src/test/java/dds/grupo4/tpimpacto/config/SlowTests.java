package dds.grupo4.tpimpacto.config;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Tag("slow")
public @interface SlowTests {
}
