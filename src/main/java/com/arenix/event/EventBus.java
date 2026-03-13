package com.arenix.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Class<? extends Event>, List<EventHandler>> listeners = new HashMap<>();

    /**
     * Subscribes an object to receive events.
     * All methods annotated with {@link SubscribeEvent} within the object will be registered.
     *
     * @param listener The object containing the event listener methods.
     */
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

    /**
     * Posts an event to the bus. All subscribed listeners for this event type will be notified.
     *
     * @param event The event to post.
     * @return The event object, potentially modified by listeners.
     */
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

    /**
     * Represents a single event handler method and its associated listener object.
     */
    private static class EventHandler {
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

    /**
     * Marker interface for all events.
     */
    public static abstract class Event {}

    /**
     * Annotation to mark methods that should be registered as event listeners.
     */
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
    public @interface SubscribeEvent {}
}
