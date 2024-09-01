package com.mantledillusion.vaadin.cotton.router;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Declares to reroute navigation to the annotated @{@link Route} to a different @{@link Route} the
 * current {@link com.vaadin.flow.server.VaadinRequest}'s {@link java.security.Principal} applies to.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Reroute {

    /**
     * The @{@link Route} {@link Component}s to reroute to instead of the annotated {@link Component}.
     * <p>
     * The {@link Component}s are iterated in order until a match is found. If none matches, no rerouting is performed.
     *
     * @return The surrogate {@link Component}s, never null
     */
    Class<? extends Component>[] value();

    /**
     * Declares whether the URL should be changed to the respective @{@link Route} when rerouting.
     *
     * @return False if a {@link BeforeEnterEvent#rerouteTo(Class)} should be performed, true if it should
     * be {@link BeforeEnterEvent#forwardTo(Class)} (Class)}
     */
    boolean changeUrl() default false;
}
