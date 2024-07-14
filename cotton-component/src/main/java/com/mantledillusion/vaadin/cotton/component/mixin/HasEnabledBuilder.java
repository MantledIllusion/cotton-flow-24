package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.server.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;

/**
 * {@link ConfigurationBuilder} for {@link HasEnabled} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasEnabled}.
 * @param <B>
 *            The final implementation type of {@link HasEnabledBuilder}.
 */
public interface HasEnabledBuilder<C extends HasEnabled, B extends HasEnabledBuilder<C, B>> extends
		HasElementBuilder<C, B> {

	/**
	 * Builder method, configures whether the {@link Component} is enabled.
	 * 
	 * @see HasEnabled#setEnabled(boolean)
	 * @param enabled True if the {@link Component} has to be enabled, false otherwise.
	 * @return this
	 */
	default B setEnabled(boolean enabled) {
		return configure(hasEnabled -> hasEnabled.setEnabled(enabled));
	}

	/**
	 * Builder method, configures the {@link Component} to be enabled if there is a principal in the given roles.
	 *
	 * @see HasEnabled#setEnabled(boolean)
	 * @param roles The roles the principal has to be in; might <b>not</b> be null.
	 * @return this
	 */
	default B setEnabledIfPermitted(String... roles) {
		return configure(hasEnabled -> hasEnabled.setEnabled(Authorization.isPermitted(roles)));
	}
}
