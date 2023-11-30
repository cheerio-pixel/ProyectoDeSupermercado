package proyectodesupermercado.lib.databaseUtils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    /**
     * The name of the column in the table
     */
    String name() default "";

    /**
     * If current field is actually an object to save in a blob
     *
     * @return
     */
    boolean isJavaObject() default false;
}
