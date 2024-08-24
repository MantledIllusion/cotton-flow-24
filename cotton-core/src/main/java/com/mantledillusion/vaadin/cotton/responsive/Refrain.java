package com.mantledillusion.vaadin.cotton.responsive;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link Responsive} annotation declaring the annotated {@link com.vaadin.flow.component.Component} to <b>not</b>be
 * injected when matching the given {@link ResponsiveClass} {@link #with()} the given values.
 * <p>
 * Requires the annotated {@link com.vaadin.flow.component.Component} to either be annotated with @{@link Responsive}
 * or be declared in {@link Responsive#value()} of another {@link com.vaadin.flow.component.Component}.
 */
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(Refrains.class)
public @interface Refrain {

    /**
     * The {@link ResponsiveClass} to match against.
     *
     * @return The {@link ResponsiveClass}, never null
     */
    Class<? extends ResponsiveClass> from();

    /**
     * The values to match against.
     *
     * @return The values, never null
     */
    String[] with();
}
