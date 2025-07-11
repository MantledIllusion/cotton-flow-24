package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.textfield.NumberField;


/**
 * {@link ConfigurationBuilder} for {@link NumberField}s.
 */
public class NumberFieldBuilder extends AbstractNumberFieldBuilder<NumberField, NumberFieldBuilder> {

    private NumberFieldBuilder() {}

    @Override
    protected NumberField instantiate() {
        return new NumberField();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static NumberFieldBuilder create() {
        return new NumberFieldBuilder();
    }

}
