package com.mantledillusion.vaadin.cotton.responsive;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link java.lang.annotation.Repeatable} of {@link Respond}.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Responds {

    Respond[] value();
}
