package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.checkbox.Checkbox;

/**
 * {@link ConfigurationBuilder} for {@link Checkbox}es.
 */
public class CheckBoxBuilder extends AbstractCheckBoxBuilder<Checkbox, CheckBoxBuilder> {

	private CheckBoxBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static CheckBoxBuilder create() {
		return new CheckBoxBuilder();
	}

	@Override
	protected Checkbox instantiate() {
		return new Checkbox();
	}

}
