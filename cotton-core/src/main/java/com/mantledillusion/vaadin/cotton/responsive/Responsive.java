package com.mantledillusion.vaadin.cotton.responsive;

import com.vaadin.flow.component.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Declares to inject either the annotated {@link Component} or one of the surrogates declared by
 * {@link Responsive#value()} responsive to the client.
 * <p>
 * The annotated {@link Component}'s @{@link Respond} and @{@link Refrain} annotations will be evaluated against the
 * requesting client; if it does not match, the surrogates declared by {@link Responsive#value()} are iterated until a
 * match is found. If none is found, the annotated @{@link Component} is injected as a fallback.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Responsive {

    /**
     * The {@link Component}s to injected instead of the annotated @{@link Component}.
     *
     * @return The surrogate {@link Component}s, never null
     */
    Class<? extends Component>[] value();
}
