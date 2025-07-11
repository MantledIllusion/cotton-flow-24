package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasAllowedCharPatternBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

public abstract class AbstractTextFieldBuilder<C extends TextField, B extends AbstractTextFieldBuilder<C, B>> extends AbstractTextFieldBaseBuilder<C, TextField, String, B> implements
        HasAllowedCharPatternBuilder<C, B>,
        HasThemeVariantBuilder<C, TextFieldVariant, B> {

    /**
     * Builder method, configures the maximum length the {@link TextField}s value
     * might grow up to.
     *
     * @param maxLength The max length.
     * @return this
     * @see TextField#setMaxLength(int)
     */
    public B setMaxLength(int maxLength) {
        return configure(textField -> textField.setMaxLength(maxLength));
    }

    /**
     * Builder method, configures the minimum length the {@link TextField}s value
     * might shrink down to.
     *
     * @param minLength The min length.
     * @return this
     * @see TextField#setMinLength(int)
     */
    public B setMinLength(int minLength) {
        return configure(textField -> textField.setMinLength(minLength));
    }

    /**
     * Builder method, configures {@link Pattern} the {@link TextField}'s content
     * has to match.
     *
     * @param pattern The pattern the value has to match; might be null.
     * @return this
     * @see TextField#setPattern(String)
     */
    public B setPattern(String pattern) {
        return configure(textField -> textField.setPattern(pattern));
    }
}
