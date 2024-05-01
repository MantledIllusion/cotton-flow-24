package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasLabel;

/**
 * {@link ConfigurationBuilder} for {@link HasAriaLabel} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasAriaLabel}.
 * @param <B>
 *            The final implementation type of {@link HasAriaLabelBuilder}.
 */
public interface HasAriaLabelBuilder<C extends HasAriaLabel, B extends HasAriaLabelBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasAriaLabel}s aria label.
     *
     * @see HasAriaLabel#setAriaLabel(String) (String)
     * @param msgId The label text, or a message id to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
     * @return this
     */
    default B setAriaLabel(Object msgId, Object... indexedMessageParameters) {
        return configure(hasLabel -> hasLabel.setAriaLabel(I18N.getTranslation(msgId, indexedMessageParameters)));
    }

    /**
     * Builder method, sets the {@link HasAriaLabel}s aria label.
     *
     * @see HasAriaLabel#setAriaLabelledBy(String) (String)
     * @param ariaComponentId The ID of the other aria component to label this component by; might <b>not</b> be null.
     * @return this
     */
    default B setAriaLabelledBy(String ariaComponentId) {
        return configure(hasLabel -> hasLabel.setAriaLabelledBy(ariaComponentId));
    }
}
