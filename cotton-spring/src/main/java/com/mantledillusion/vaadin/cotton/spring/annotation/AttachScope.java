package com.mantledillusion.vaadin.cotton.spring.annotation;

import com.mantledillusion.vaadin.cotton.spring.scopes.CottonAttachScope;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * Spring scope providing the combined behaviour of @{@link com.vaadin.flow.spring.annotation.RouteScope} and
 * {@link Scope#value()}="prototype", creating prototype style bean instances with a lifecycle bound to Vaadin
 * {@link com.vaadin.flow.router.Router} navigation.
 * <p>
 * Beans defined as attach-scoped will autowire as individually provided instances at every injection point. They
 * cannot be destroyed manually, but will rather be destroyed right before navigating out of the route they were
 * injected into.
 */
@Scope(CottonAttachScope.COTTON_ATTACH_SCOPE_NAME)
@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AttachScope {

}
