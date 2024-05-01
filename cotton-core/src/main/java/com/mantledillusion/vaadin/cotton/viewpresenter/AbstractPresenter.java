package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentUtil;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * A basic implementation of a {@link Presenter} that holds its presented component.
 *
 * @param <V> The type of view presented by this {@link Presenter}.
 */
public abstract class AbstractPresenter<V extends Component> implements Presenter<V> {

    private V view;

    /**
     * Returns this presenter's view.
     *
     * @return The view, never null
     */
    protected V getView() {
        if (this.view == null) {
            throw new IllegalStateException("The presenter has not been initialized with its view yet.");
        }

        return this.view;
    }

    @Override
    public void setView(V view) {
        this.view = view;
    }

    static <P, V extends Component> void present(V presentable, Function<Class<P>, P> presenterInstantiator) {
        if (presentable.getClass().isAnnotationPresent(Presented.class)) {
            Class<P> presenterType = (Class<P>) presentable.getClass().getAnnotation(Presented.class).value();
            if (Presenter.class.isAssignableFrom(presenterType)) {
                Type viewType = TypeUtils.getTypeArguments(presenterType, Presenter.class)
                        .get(Presenter.class.getTypeParameters()[0]);
                if (!TypeUtils.isAssignable(viewType, presentable.getClass())) {
                    throw new IllegalStateException("The type " + presentable.getClass().getSimpleName()
                            + " requires to be presented by the type " + presenterType.getSimpleName()
                            + " but is not assignable to its view type " + viewType.getTypeName());
                }
            }

            Map<String, Map<Class<? extends ComponentEvent<?>>, List<Method>>> methodRegistry = new HashMap<>();
            for (Method method: MethodUtils.getMethodsWithAnnotation(presenterType, Listen.class, true, true)) {
                Listen annotationInstance = method.getAnnotation(Listen.class);
                Parameter[] parameters = method.getParameters();

                // VALIDATE METHOD ERASURE
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Listen.class.getSimpleName() + " but is declared static, which is " + "not allowed.");
                } else if (!isValidPattern(annotationInstance.value())) {
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Listen.class.getSimpleName() + " but declares the component id matcher '"
                            + annotationInstance.value() + "', which is not a valid pattern.");
                } else if (parameters.length > 1) {
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Listen.class.getSimpleName() + " but declares " + parameters.length
                            + " parameters; subscribing methods might only receive the event or nothing as argument.");
                } else if (annotationInstance.extensions().length == 0) {
                    if (parameters.length == 0) {
                        throw new IllegalStateException("The method" + method.getName() + " is annotated with @"
                                + Listen.class.getSimpleName() + " and declares no parameters, but does also not "
                                + "declare at least one event extension class which is required when not using a parameter.");
                    } if (ComponentEvent.class.isAssignableFrom(parameters[0].getType())) {
                        methodRegistry.computeIfAbsent(annotationInstance.value(), pattern -> new HashMap<>())
                                .computeIfAbsent((Class<? extends ComponentEvent<?>>) parameters[0].getType(), eventType -> new ArrayList<>())
                                .add(method);
                    } else {
                        throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                                + Listen.class.getSimpleName() + " but declares a parameter "  + parameters[0].getName()
                                + " of type " + parameters[0].getType().getSimpleName() + " which is not an "
                                + "assignable of " + ComponentEvent.class.getSimpleName());
                    }
                } else {
                    Class<?> parameterType = parameters.length == 0 ? Object.class : parameters[0].getType();
                    for (Class<? extends ComponentEvent<?>> extensionType: annotationInstance.extensions()) {
                        if (!parameterType.isAssignableFrom(extensionType)) {
                            throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                                    + Listen.class.getSimpleName() + " and declares a parameter of the type "
                                    + parameterType.getSimpleName() + ", but also declares an extension of the type "
                                    + extensionType.getSimpleName() + " which is not assignable.");
                        } else {
                            methodRegistry.computeIfAbsent(annotationInstance.value(), pattern -> new HashMap<>())
                                    .computeIfAbsent(extensionType, eventType -> new ArrayList<>())
                                    .add(method);
                        }
                    }
                }
            }

            // VALIDATE COMPONENT IDS
            Map<String, Map<Class<? extends ComponentEvent<?>>, List<Consumer<Object>>>> listenerRegistry = new HashMap<>();
            registerComponent(presentable, methodRegistry, listenerRegistry);

            for (String pattern: methodRegistry.keySet()) {
                if (!listenerRegistry.containsKey(pattern)) {
                    Method method = methodRegistry.get(pattern).values().iterator().next().iterator().next();
                    throw new IllegalStateException("The method " + method.getName() + " is annotated with @"
                            + Listen.class.getSimpleName() + " and declares the pattern '" + pattern
                            + "', but no component with an ID matching this pattern was found.");
                }
            }

            // INSTANTIATE PRESENTER
            Object presenter = presenterInstantiator.apply(presenterType);

            if (presenter instanceof Presenter) {
                ((Presenter<V>) presenter).setView(presentable);
            }

            listenerRegistry.values().stream()
                    .map(Map::values)
                    .flatMap(Collection::stream)
                    .flatMap(List::stream)
                    .forEach(listener -> listener.accept(presenter));
        }
    }

    private static boolean isValidPattern(String regex) {
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            return false;
        }
        return true;
    }

    private static void registerComponent(Component component, Map<String, Map<Class<? extends ComponentEvent<?>>, List<Method>>> methodRegistry,
                                          Map<String, Map<Class<? extends ComponentEvent<?>>, List<Consumer<Object>>>> listenerRegistry) {
        if (component.getId().isPresent()) {
            String componentId = component.getId().get();
            for (String pattern: methodRegistry.keySet()) {
                if (componentId.matches(pattern)) {
                    for (Class<? extends ComponentEvent<?>> eventType: methodRegistry.get(pattern).keySet()) {
                        for (Method method: methodRegistry.get(pattern).get(eventType)) {
                            listenerRegistry.computeIfAbsent(pattern, p -> new HashMap<>())
                                    .computeIfAbsent(eventType, e -> new ArrayList<>())
                                    .add(presenter -> addListener(component, eventType, presenter, method));
                        }
                    }
                }
            }
        }
        component.getChildren().forEach(childComponent -> registerComponent(childComponent, methodRegistry, listenerRegistry));
    }

    private static void addListener(Component component, Class<? extends ComponentEvent<?>> eventType, Object presenter, Method method) {
        if (!method.canAccess(presenter)) {
            try {
                method.setAccessible(true);
            } catch (SecurityException e) {
                throw new IllegalStateException("Unable to gain access to the method '" + method.getName()
                        + "' of the type " + method.getDeclaringClass().getSimpleName() + ".", e);
            }
        }

        ComponentUtil.addListener(component, eventType, event -> {
            try {
                method.invoke(presenter, event);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException("The method '" + method.getName() + "' failed to handle the event '" + event + "'", e);
            }
        });
    }
}
