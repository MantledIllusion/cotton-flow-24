package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.button.Button;

/**
 * {@link ConfigurationBuilder} for {@link Button}s.
 */
public class ButtonBuilder extends AbstractButtonBuilder<Button, ButtonBuilder> {

	private ButtonBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static ButtonBuilder create() {
		return new ButtonBuilder();
	}

	@Override
	protected Button instantiate() {
		return new Button();
	}

}
