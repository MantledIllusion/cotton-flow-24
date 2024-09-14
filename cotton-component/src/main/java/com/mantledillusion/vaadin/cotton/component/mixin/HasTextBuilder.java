package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;

/**
 * {@link ConfigurationBuilder} for {@link HasText} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasText}.
 * @param <B>
 *            The final implementation type of {@link HasTextBuilder}.
 */
public interface HasTextBuilder<C extends HasText, B extends HasTextBuilder<C, B>> extends
		HasElementBuilder<C, B> {

	/**
	 * Builder method, configures the initial text of the component after building.
	 * 
	 * @see HasText#setText(String)
	 * @param msgId The initial text, or a message id to localize; might be null.
	 * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
	 * @return this
	 */
	default B setText(String msgId, Object... indexedMessageParameters) {
		return configure(hasValue -> hasValue.setText(I18N.getTranslation(msgId, indexedMessageParameters)));
	}

	/**
	 * Builder method, configures the whitespace behavior of the component after building.
	 *
	 * @see HasText#setWhiteSpace(HasText.WhiteSpace)
	 * @param whiteSpace The behavior type, might <b>not</b> be null.
	 * @return this
	 */
	default B setWhiteSpace(HasText.WhiteSpace whiteSpace) {
		return configure(hasValue -> hasValue.setWhiteSpace(whiteSpace));
	}
}
