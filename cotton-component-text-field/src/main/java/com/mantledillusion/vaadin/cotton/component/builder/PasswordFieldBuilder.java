package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.textfield.PasswordField;


/**
 * {@link ConfigurationBuilder} for {@link PasswordField}s.
 */
public class PasswordFieldBuilder extends AbstractPasswordFieldBuilder<PasswordField, PasswordFieldBuilder> {

    private PasswordFieldBuilder() {}

    @Override
    protected PasswordField instantiate() {
        return new PasswordField();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static PasswordFieldBuilder create() {
        return new PasswordFieldBuilder();
    }

}
