package proyectodesupermercado.lib.databaseUtils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    /**
     * The name of the table in the database.
     * By default is the downcased version of class name.
     */
    String name() default "";
}
