package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

/**
 * {@link ConfigurationBuilder} for {@link NumberField}s.
 */
public class NumberFieldBuilder extends AbstractTextFieldBaseBuilder<NumberField, Double, NumberFieldBuilder> implements
        HasThemeVariantBuilder<NumberField, TextFieldVariant, NumberFieldBuilder> {

    private NumberFieldBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static NumberFieldBuilder create() {
        return new NumberFieldBuilder();
    }

    @Override
    protected NumberField instantiate() {
        return new NumberField();
    }

    /**
     * Builder method, configures the maximum the {@link NumberField}s value might grow up to.
     *
     * @param max The max value.
     * @return this
     */
    public NumberFieldBuilder setMax(double max) {
        return configure(numberField -> numberField.setMax(max));
    }

    /**
     * Builder method, configures the minimum the {@link NumberField}s value might shrink down to.
     *
     * @param min The min value.
     * @return this
     */
    public NumberFieldBuilder setMin(double min) {
        return configure(numberField -> numberField.setMin(min));
    }
}
