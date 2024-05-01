package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;

/**
 * Extension of {@link ConfigurationBuilder} offering {@link AbstractComponentBuilder#build()} by creating a new
 * {@link Component} through {@link AbstractComponentBuilder#instantiate()} and letting
 * {@link AbstractConfigurationBuilder#apply(Object)} configure it.
 *
 * @param <C>
 *            The {@link Component} type this builder builds. Not an extension of {@link Component} on purpose, since
 *            Vaadin handles shared {@link Component} behavior using interfaces that are not necessarily bound to
 *            {@link Component}s.
 * @param <B>
 *            The final implementation type of this {@link AbstractComponentBuilder}. Necessary to allow builder
 *            methods of non-final implementations to return the builder instance in the correct type.
 */
abstract class AbstractComponentBuilder<C extends Component, B extends AbstractComponentBuilder<C, B>>
		extends AbstractConfigurationBuilder<C, B> {

	/**
	 * Builder method, configures a {@link Component}'s id.
	 *
	 * @see Component#setId(String)
	 * @param id The id to set; might <b>not</b> be null.
	 * @return this
	 */
	public final B setId(String id) {
		return configure(activeComponent -> activeComponent.setId(id));
	}

	/**
	 * Adds a listener for {@link AttachEvent}s.
	 *
	 * @see Component#addAttachListener(ComponentEventListener)
	 * @param listener The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public final B addAttachListener(ComponentEventListener<AttachEvent> listener) {
		return configure(component -> component.addAttachListener(listener));
	}

	/**
	 * Adds a listener for {@link DetachEvent}s.
	 *
	 * @see Component#addDetachListener(ComponentEventListener)
	 * @param listener The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public final B addDetachListener(ComponentEventListener<DetachEvent> listener) {
		return configure(component -> component.addDetachListener(listener));
	}

	/**
	 * Creates a new {@link Component} instance using {@link #instantiate()}, applies all currently contained
	 * {@link Configurer}s to it and returns it.
	 *
	 * @return A new {@link Component} instance, fully configured, never null
	 */
	public C build() {
		C component = instantiate();
		apply(component);
		return component;
	}

	/**
	 * Instantiates a new instance of the {@link Component} to build on every invocation.
	 *
	 * @return A new instance of the {@link Component} to build, never null
	 */
	protected abstract C instantiate();
}