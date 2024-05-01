package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.regex.Pattern;

/**
 * {@link ConfigurationBuilder} for {@link PasswordField}s.
 */
public class PasswordFieldBuilder extends AbstractTextFieldBaseBuilder<PasswordField, String, PasswordFieldBuilder> implements
        HasThemeVariantBuilder<PasswordField, TextFieldVariant, PasswordFieldBuilder> {

    private PasswordFieldBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static PasswordFieldBuilder create() {
        return new PasswordFieldBuilder();
    }

    @Override
    protected PasswordField instantiate() {
        return new PasswordField();
    }

    /**
     * Builder method, configures the maximum length the {@link PasswordField}s value might grow up to.
     *
     * @see PasswordField#setMaxLength(int)
     * @param maxLength The max length.
     * @return this
     */
    public PasswordFieldBuilder setMaxLength(int maxLength) {
        return configure(textField -> textField.setMaxLength(maxLength));
    }

    /**
     * Builder method, configures the minimum length the {@link PasswordField}s value might shrink down to.
     *
     * @see PasswordField#setMinLength(int)
     * @param minLength The min length.
     * @return this
     */
    public PasswordFieldBuilder setMinLength(int minLength) {
        return configure(textField -> textField.setMinLength(minLength));
    }

    /**
     * Builder method, configures {@link Pattern} the {@link PasswordField}'s content
     * has to match.
     *
     * @see PasswordField#setPattern(String)
     * @param pattern The pattern the value has to match; might be null.
     * @return this
     */
    public PasswordFieldBuilder setPattern(String pattern) {
        return configure(textField -> textField.setPattern(pattern));
    }

    /**
     * Builder method, configures whether the button to reveal the password is visible.
     *
     * @see PasswordField#setRevealButtonVisible(boolean)
     * @param revealButtonVisible Whether the reveal button should be visible.
     * @return this
     */
    public PasswordFieldBuilder setRevealButtonVisible(boolean revealButtonVisible) {
        return configure(textField -> textField.setRevealButtonVisible(revealButtonVisible));
    }
}
