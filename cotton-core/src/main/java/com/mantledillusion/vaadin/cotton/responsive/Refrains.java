package com.mantledillusion.vaadin.cotton.responsive;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link java.lang.annotation.Repeatable} of @{@link Refrain}.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Refrains {

    Refrain[] value();
}
