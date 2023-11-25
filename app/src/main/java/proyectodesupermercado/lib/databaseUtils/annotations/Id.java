package proyectodesupermercado.lib.databaseUtils.annotations;

import proyectodesupermercado.lib.databaseUtils.IdStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks field as the id of the current class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    String name() default "";
    IdStrategy idStrategy() default IdStrategy.IDENTITY;
}
