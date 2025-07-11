package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.formlayout.FormLayout;


/**
 * {@link ConfigurationBuilder} for {@link FormLayout}s.
 */
public class FormLayoutBuilder extends AbstractFormLayoutBuilder<FormLayout, FormLayoutBuilder> {

    private FormLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static FormLayoutBuilder create() {
        return new FormLayoutBuilder();
    }

    @Override
    protected FormLayout instantiate() {
        return new FormLayout();
    }
}
