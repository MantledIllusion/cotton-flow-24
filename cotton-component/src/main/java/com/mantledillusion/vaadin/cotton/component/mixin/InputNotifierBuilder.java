package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.InputEvent;
import com.vaadin.flow.component.InputNotifier;

/**
 * {@link ConfigurationBuilder} for {@link InputNotifier} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link InputNotifier}.
 * @param <B>
 *            The final implementation type of {@link InputNotifierBuilder}.
 */
public interface InputNotifierBuilder<C extends Component & InputNotifier, B extends InputNotifierBuilder<C, B>>
		extends ConfigurationBuilder<C, B> {

	/**
     * Builder method, configures a listener for {@link InputEvent}s.
	 *
	 * @see InputNotifier#addInputListener(ComponentEventListener)
	 * @param listener
	 * 				The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	default B addInputListener(ComponentEventListener<InputEvent> listener) {
		return configure(compositionNotifier -> compositionNotifier.addInputListener(listener));
	}
}
