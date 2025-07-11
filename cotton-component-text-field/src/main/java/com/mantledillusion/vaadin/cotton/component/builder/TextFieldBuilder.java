package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

/**
 * {@link ConfigurationBuilder} for {@link TextField}s.
 */
public class TextFieldBuilder extends AbstractTextFieldBuilder<TextField, TextFieldBuilder> {

	private TextFieldBuilder() {}

	@Override
	protected TextField instantiate() {
		return new TextField();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TextFieldBuilder create() {
		return new TextFieldBuilder();
	}

}