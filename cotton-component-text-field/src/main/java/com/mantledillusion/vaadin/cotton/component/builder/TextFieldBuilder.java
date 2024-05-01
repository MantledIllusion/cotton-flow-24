package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

/**
 * {@link ConfigurationBuilder} for {@link TextField}s.
 */
public class TextFieldBuilder extends AbstractTextFieldBaseBuilder<TextField, String, TextFieldBuilder> implements
		HasThemeVariantBuilder<TextField, TextFieldVariant, TextFieldBuilder> {

	private TextFieldBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TextFieldBuilder create() {
		return new TextFieldBuilder();
	}

	@Override
	protected TextField instantiate() {
		return new TextField();
	}

	/**
	 * Builder method, configures the maximum length the {@link TextField}s value
	 * might grow up to.
	 * 
	 * @see TextField#setMaxLength(int)
	 * @param maxLength
	 *            The max length.
	 * @return this
	 */
	public TextFieldBuilder setMaxLength(int maxLength) {
		return configure(textField -> textField.setMaxLength(maxLength));
	}

	/**
	 * Builder method, configures the minimum length the {@link TextField}s value
	 * might shrink down to.
	 * 
	 * @see TextField#setMinLength(int)
	 * @param minLength
	 *            The min length.
	 * @return this
	 */
	public TextFieldBuilder setMinLength(int minLength) {
		return configure(textField -> textField.setMinLength(minLength));
	}

	/**
	 * Builder method, configures {@link Pattern} the {@link TextField}'s content
	 * has to match.
	 * 
	 * @see TextField#setPattern(String)
	 * @param pattern
	 *            The pattern the value has to match; might be null.
	 * @return this
	 */
	public TextFieldBuilder setPattern(String pattern) {
		return configure(textField -> textField.setPattern(pattern));
	}
}