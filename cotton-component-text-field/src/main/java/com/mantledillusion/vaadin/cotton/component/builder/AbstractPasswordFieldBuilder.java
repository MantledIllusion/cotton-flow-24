package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasAllowedCharPatternBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

public abstract class AbstractPasswordFieldBuilder<C extends PasswordField, B extends AbstractPasswordFieldBuilder<C, B>> extends AbstractTextFieldBaseBuilder<C, PasswordField, String, B> implements
        HasAllowedCharPatternBuilder<C, B>,
        HasThemeVariantBuilder<C, TextFieldVariant, B> {
    /**
     * Builder method, configures the maximum length the {@link PasswordField}s value might grow up to.
     *
     * @param maxLength The max length.
     * @return this
     * @see PasswordField#setMaxLength(int)
     */
    public B setMaxLength(int maxLength) {
        return configure(textField -> textField.setMaxLength(maxLength));
    }

    /**
     * Builder method, configures the minimum length the {@link PasswordField}s value might shrink down to.
     *
     * @param minLength The min length.
     * @return this
     * @see PasswordField#setMinLength(int)
     */
    public B setMinLength(int minLength) {
        return configure(textField -> textField.setMinLength(minLength));
    }

    /**
     * Builder method, configures {@link Pattern} the {@link PasswordField}'s content
     * has to match.
     *
     * @param pattern The pattern the value has to match; might be null.
     * @return this
     * @see PasswordField#setPattern(String)
     */
    public B setPattern(String pattern) {
        return configure(textField -> textField.setPattern(pattern));
    }

    /**
     * Builder method, configures whether the button to reveal the password is visible.
     *
     * @param revealButtonVisible Whether the reveal button should be visible.
     * @return this
     * @see PasswordField#setRevealButtonVisible(boolean)
     */
    public B setRevealButtonVisible(boolean revealButtonVisible) {
        return configure(textField -> textField.setRevealButtonVisible(revealButtonVisible));
    }
}
