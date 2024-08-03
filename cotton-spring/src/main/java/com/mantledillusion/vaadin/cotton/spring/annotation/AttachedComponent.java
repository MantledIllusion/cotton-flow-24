package com.mantledillusion.vaadin.cotton.spring.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Spring @{@link Component} alias that includes @{@link AttachScope}.
 */
@Component
@AttachScope
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AttachedComponent {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
