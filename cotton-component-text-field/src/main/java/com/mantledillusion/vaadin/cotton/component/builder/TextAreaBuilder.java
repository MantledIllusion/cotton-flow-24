package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;

/**
 * {@link ConfigurationBuilder} for {@link TextArea}s.
 */
public class TextAreaBuilder extends AbstractTextFieldBaseBuilder<TextArea, String, TextAreaBuilder> implements
		HasAllowedCharPatternBuilder<TextArea, TextAreaBuilder>,
		HasThemeVariantBuilder<TextArea, TextAreaVariant, TextAreaBuilder> {

	private TextAreaBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TextAreaBuilder create() {
		return new TextAreaBuilder();
	}

	@Override
	protected TextArea instantiate() {
		return new TextArea();
	}

	/**
	 * Builder method, configures the maximum length the {@link TextArea}s value might grow up to.
	 * 
	 * @see TextArea#setMaxLength(int)
	 * @param maxLength
	 *            The max length.
	 * @return this
	 */
	public TextAreaBuilder setMaxLength(int maxLength) {
		return configure(textArea -> textArea.setMaxLength(maxLength));
	}

	/**
	 * Builder method, configures the minimum length the {@link TextArea}s value might shrink down to.
	 * 
	 * @see TextArea#setMinLength(int)
	 * @param minLength
	 *            The min length.
	 * @return this
	 */
	public TextAreaBuilder setMinLength(int minLength) {
		return configure(textArea -> textArea.setMinLength(minLength));
	}
}