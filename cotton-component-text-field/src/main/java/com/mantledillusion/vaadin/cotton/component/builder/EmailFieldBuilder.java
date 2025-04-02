package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasAllowedCharPatternBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

/**
 * {@link ConfigurationBuilder} for {@link EmailField}s.
 */
public class EmailFieldBuilder extends AbstractTextFieldBaseBuilder<EmailField, String, EmailFieldBuilder> implements
        HasAllowedCharPatternBuilder<EmailField, EmailFieldBuilder>,
        HasThemeVariantBuilder<EmailField, TextFieldVariant, EmailFieldBuilder> {

    private EmailFieldBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static EmailFieldBuilder create() {
        return new EmailFieldBuilder();
    }

    @Override
    protected EmailField instantiate() {
        return new EmailField();
    }

    /**
     * Builder method, configures the maximum length the {@link EmailField}s value might grow up to.
     *
     * @see EmailField#setMaxLength(int)
     * @param maxLength The max length.
     * @return this
     */
    public EmailFieldBuilder setMaxLength(int maxLength) {
        return configure(textField -> textField.setMaxLength(maxLength));
    }

    /**
     * Builder method, configures the minimum length the {@link EmailField}s value might shrink down to.
     *
     * @see EmailField#setMinLength(int)
     * @param minLength The min length.
     * @return this
     */
    public EmailFieldBuilder setMinLength(int minLength) {
        return configure(textField -> textField.setMinLength(minLength));
    }

    /**
     * Builder method, configures {@link Pattern} the {@link EmailField}'s content
     * has to match.
     *
     * @see EmailField#setPattern(String)
     * @param pattern The pattern the value has to match; might be null.
     * @return this
     */
    public EmailFieldBuilder setPattern(String pattern) {
        return configure(textField -> textField.setPattern(pattern));
    }
}
