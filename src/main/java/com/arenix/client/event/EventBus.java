package com.arenix.client.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Class<? extends Event>, List<EventHandler>> listeners = new HashMap<>();

    public void subscribe(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && Event.class.isAssignableFrom(parameterTypes[0])) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Event> eventType = (Class<? extends Event>) parameterTypes[0];
                    List<EventHandler> handlers = listeners.computeIfAbsent(eventType, k -> new ArrayList<>());
                    handlers.add(new EventHandler(listener, method));
                }
            }
        }
    }

    public Event post(Event event) {
        Class<? extends Event> eventType = event.getClass();
        List<EventHandler> handlers = listeners.get(eventType);

        if (handlers != null) {
            for (EventHandler handler : handlers) {
                try {
                    handler.invoke(event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println("Error invoking event handler for " + eventType.getSimpleName() + ": " + handler.method.getName());
                    e.printStackTrace();
                }
            }
        }
        return event;
    }

    public static class EventHandler {
        private final Object listener;
        private final Method method;

        public EventHandler(Object listener, Method method) {
            this.listener = listener;
            this.method = method;
            this.method.setAccessible(true); // Allow access to private methods if necessary
        }

        public void invoke(Event event) throws IllegalAccessException, InvocationTargetException {
            method.invoke(listener, event);
        }
    }

    public static abstract class Event {
    }

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
    public @interface SubscribeEvent {
    }
}
