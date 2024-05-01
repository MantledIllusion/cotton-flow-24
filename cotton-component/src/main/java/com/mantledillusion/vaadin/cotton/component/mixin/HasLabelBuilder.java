package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasLabel;

/**
 * {@link ConfigurationBuilder} for {@link HasLabel} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasLabel}.
 * @param <B>
 *            The final implementation type of {@link HasLabelBuilder}.
 */
public interface HasLabelBuilder<C extends HasLabel, B extends HasLabelBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasLabel}s label.
     *
     * @see HasLabel#setLabel(String)
     * @param msgId The label text, or a message id to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
     * @return this
     */
    default B setLabel(Object msgId, Object... indexedMessageParameters) {
        return configure(hasLabel -> hasLabel.setLabel(I18N.getTranslation(msgId, indexedMessageParameters)));
    }
}
