package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link Annotation} for {@link Method}s of presenters that need to listen to events of active child {@link Component}s
 * of that presenter's root {@link Component}.
 * <p>
 * The {@link Method} will be auto-registered for specific event classes; which ones depend on the {@link Method}s
 * parameter and the declared {@link #extensions()}.
 * <p>
 * The {@link Method} will also receive any events of sub-{@link Class}es of the event {@link Class}es it is registered for.
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Listen {

	/**
	 * Defines a matcher for component ids whose corresponding {@link Component} the {@link Method} annotated
	 * with @{@link Listen} should listen to.
	 * <p>
	 * By default, the matcher is <code>.+</code>, so the {@link Method} will listen to <B>all</B> components with an ID.
	 * 
	 * @return The {@link Component}s to listen to; never null, might be empty
	 */
	String value() default ".+";

	/**
	 * Event {@link Class}es the annotated {@link Method} is interested in.
	 * <p>
	 * If the {@link Method} has a {@link java.lang.reflect.Parameter}...
	 * <ul>
	 * <li>...it always needs to be assignable from every extension {@link Class}es specified</li>
	 * <li>...it will not be used for registration; instead, the extension {@link Class}es are</li>
	 * </ul>
	 *
	 * @return The event {@link Class}es to register for, never null
	 */
	Class<? extends ComponentEvent<?>>[] extensions() default {};
}