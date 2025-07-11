package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.textfield.TextArea;


/**
 * {@link ConfigurationBuilder} for {@link TextArea}s.
 */
public class TextAreaBuilder extends AbstractTextAreaBuilder<TextArea, TextAreaBuilder> {

	private TextAreaBuilder() {}

	@Override
	protected TextArea instantiate() {
		return new TextArea();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TextAreaBuilder create() {
		return new TextAreaBuilder();
	}

}