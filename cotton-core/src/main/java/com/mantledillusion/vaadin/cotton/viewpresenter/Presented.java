package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link Annotation} for {@link com.vaadin.flow.component.Component}s that need controlling by a presenter.
 * <p>
 * Presenters are instantiated to be completely autonomous, without any possibility to be injected elsewhere.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Presented {

	/**
	 * Defines the presenter's implementation type that will be instantiated individually for every instance of the
	 * annotated {@link com.vaadin.flow.component.Component}.
	 * <p>
	 * If the given type is an instance of {@link Presenter}, the {@link Presenter#setView(Component)} method is
	 * called after instantiation.
	 *
	 * @return The presenter's type, never null
	 */
	Class<?> value();
}