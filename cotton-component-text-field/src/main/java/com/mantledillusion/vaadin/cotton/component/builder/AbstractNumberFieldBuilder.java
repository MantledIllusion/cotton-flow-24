package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasAllowedCharPatternBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

public abstract class AbstractNumberFieldBuilder<C extends NumberField, B extends AbstractNumberFieldBuilder<C, B>> extends AbstractTextFieldBaseBuilder<C, NumberField, Double, B> implements
        HasAllowedCharPatternBuilder<C, B>,
        HasThemeVariantBuilder<C, TextFieldVariant, B> {
    /**
     * Builder method, configures the maximum the {@link NumberField}s value might grow up to.
     *
     * @param max The max value.
     * @return this
     */
    public B setMax(double max) {
        return configure(numberField -> numberField.setMax(max));
    }

    /**
     * Builder method, configures the minimum the {@link NumberField}s value might shrink down to.
     *
     * @param min The min value.
     * @return this
     */
    public B setMin(double min) {
        return configure(numberField -> numberField.setMin(min));
    }
}
