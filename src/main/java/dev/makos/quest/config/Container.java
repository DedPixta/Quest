package dev.makos.quest.config;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Container {
    private final Map<Class<?>, Object> container = new HashMap<>();

    public <T> void setBean(Class<T> type, Object instance){
        container.put(type, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> type) {
        try {
            if (container.containsKey(type)) {
                return (T) container.get(type);
            } else {
                Constructor<?>[] constructors = type.getConstructors();
                Constructor<?> constructor = constructors[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameters.length; i++) {
                    parameters[i] = getInstance(parameterTypes[i]);
                }
                Object component = constructor.newInstance(parameters);
                container.put(type, component);
                return (T) component;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Context broken for " + type, e);
        }
    }
}
