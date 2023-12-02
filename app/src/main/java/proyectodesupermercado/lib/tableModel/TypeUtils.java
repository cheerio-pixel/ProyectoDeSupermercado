package proyectodesupermercado.lib.tableModel;

import java.util.HashMap;
import java.util.Map;

public final class TypeUtils {
    public final static Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();

    static {
        map.putAll(Map.of(
                boolean.class, Boolean.class,
                byte.class, Byte.class,
                short.class, Short.class,
                char.class, Character.class,
                int.class, Integer.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class));
    }

    private TypeUtils() {
    }

    public static Class<?> getClass(Class<?> primitive) {
        return map.getOrDefault(primitive, primitive);
    }
}
