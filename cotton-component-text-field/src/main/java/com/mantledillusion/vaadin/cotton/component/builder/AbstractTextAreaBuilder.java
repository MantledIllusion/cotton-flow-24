package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasAllowedCharPatternBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;

public abstract class AbstractTextAreaBuilder<C extends TextArea, B extends AbstractTextAreaBuilder<C, B>> extends AbstractTextFieldBaseBuilder<C, TextArea, String, B> implements
        HasAllowedCharPatternBuilder<C, B>,
        HasThemeVariantBuilder<C, TextAreaVariant, B> {
    /**
     * Builder method, configures the maximum length the {@link TextArea}s value might grow up to.
     *
     * @param maxLength The max length.
     * @return this
     * @see TextArea#setMaxLength(int)
     */
    public B setMaxLength(int maxLength) {
        return configure(textArea -> textArea.setMaxLength(maxLength));
    }

    /**
     * Builder method, configures the minimum length the {@link TextArea}s value might shrink down to.
     *
     * @param minLength The min length.
     * @return this
     * @see TextArea#setMinLength(int)
     */
    public B setMinLength(int minLength) {
        return configure(textArea -> textArea.setMinLength(minLength));
    }
}
