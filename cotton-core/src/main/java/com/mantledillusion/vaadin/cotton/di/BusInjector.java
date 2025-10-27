package com.mantledillusion.vaadin.cotton.di;

import com.mantledillusion.vaadin.cotton.event.Bus;
import com.mantledillusion.vaadin.cotton.event.BusFactory;
import com.mantledillusion.vaadin.cotton.event.Subscribe;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

class BusInjector {

    private static class CottonEventBus implements Bus {

        private interface Subscriber {

            void handle(Object event);
        }

        private final Map<Class<?>, List<Subscriber>> subscribers;

        private CottonEventBus() {
            this.subscribers = new HashMap<>();
        }

        @Override
        public void subscribe(Object subscriber) {
            if (subscriber == null) {
                throw new IllegalArgumentException("Cannot subscribe a null subscriber");
            }

            for (Method method: MethodUtils.getMethodsWithAnnotation(subscriber.getClass(), Subscribe.class, true, true)) {
                Subscribe annotationInstance = method.getAnnotation(Subscribe.class);
                Parameter[] parameters = method.getParameters();

                // VALIDATE METHOD ERASURE
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Subscribe.class.getSimpleName() + " but is declared static, which is " + "not allowed.");
                } else if (parameters.length > 1) {
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Subscribe.class.getSimpleName() + " but declares " + parameters.length
                            + " parameters; subscribing methods might only receive the event or nothing as argument.");
                } else if (annotationInstance.extensions().length == 0) {
                    if (parameters.length == 0) {
                        throw new IllegalStateException("The method" + method.getName() + " is annotated with @"
                                + Subscribe.class.getSimpleName() + " and declares no parameters, but does also not "
                                + "declare at least one event extension class which is required when not using a parameter.");
                    } else {
                        addSubscriber(parameters[0].getType(), subscriber, method, true);
                    }
                } else {
                    Class<?> parameterType = parameters.length == 0 ? Object.class : parameters[0].getType();
                    for (Class<?> extensionType: annotationInstance.extensions()) {
                        if (!parameterType.isAssignableFrom(extensionType)) {
                            throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                                    + Subscribe.class.getSimpleName() + " and declares a parameter of the type "
                                    + parameterType.getSimpleName() + ", but also declares an extension of the type "
                                    + extensionType.getSimpleName() + " which is not assignable.");
                        } else {
                            addSubscriber(extensionType, subscriber, method, parameters.length == 1);
                        }
                    }
                }
            }
        }

        private void addSubscriber(Class<?> eventType, Object subscriber, Method method, boolean asParameter) {
            if (!method.canAccess(subscriber)) {
                try {
                    method.setAccessible(true);
                } catch (SecurityException e) {
                    throw new IllegalStateException("Unable to gain access to the method '" + method.getName()
                            + "' of the type " + method.getDeclaringClass().getSimpleName() + ".", e);
                }
            }

            this.subscribers.computeIfAbsent(eventType, type -> new ArrayList<>())
                    .add(event -> {
                        try {
                            if (asParameter) {
                                method.invoke(subscriber, event);
                            } else {
                                method.invoke(subscriber);
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            throw new RuntimeException("The method '" + method.getName() + "' failed to handle the event '" + event + "'", e);
                        }
                    });
        }

        @Override
        public void dispatch(Object event) {
            if (event == null) {
                throw new IllegalArgumentException("Cannot dispatch a null event");
            }

            this.subscribers.getOrDefault(event.getClass(), Collections.emptyList())
                    .forEach(subscriber -> subscriber.handle(event));
        }
    }

    static void subscribe(UI ui, Object subscriber) {
        Bus bus;

        synchronized (ui) {
            bus = Optional.ofNullable(ComponentUtil.getData(ui, Bus.class))
                    .orElseGet(() -> {
                        var serviceLoader = ServiceLoader.load(BusFactory.class);

                        var eventBus =  serviceLoader.stream()
                                .reduce((p1, p2) -> {
                                    var parsers = serviceLoader.stream()
                                            .map(parser -> parser.getClass().getSimpleName())
                                            .toList();
                                    throw new IllegalStateException(String.format("Found %s implementations of %s ([%s]); only one should be available.",
                                            parsers.size(), BusFactory.class.getSimpleName(), StringUtils.join(parsers, ", ")));
                                })
                                .map(ServiceLoader.Provider::get)
                                .map(BusFactory::create)
                                .orElseGet(CottonEventBus::new);

                        ComponentUtil.setData(ui, Bus.class, eventBus);

                        return eventBus;
                    });
        }

        bus.subscribe(subscriber);
    }
}
