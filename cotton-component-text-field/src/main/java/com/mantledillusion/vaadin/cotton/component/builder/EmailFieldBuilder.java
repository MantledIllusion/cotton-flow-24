package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.textfield.EmailField;


/**
 * {@link ConfigurationBuilder} for {@link EmailField}s.
 */
public class EmailFieldBuilder extends AbstractEmailFieldBuilder<EmailField, EmailFieldBuilder> {

    private EmailFieldBuilder() {}

    @Override
    protected EmailField instantiate() {
        return new EmailField();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static EmailFieldBuilder create() {
        return new EmailFieldBuilder();
    }

}
