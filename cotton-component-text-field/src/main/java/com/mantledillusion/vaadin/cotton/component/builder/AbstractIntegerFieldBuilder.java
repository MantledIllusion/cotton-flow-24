package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

public abstract class AbstractIntegerFieldBuilder<C extends IntegerField, B extends AbstractIntegerFieldBuilder<C, B>> extends AbstractTextFieldBaseBuilder<C, IntegerField, Integer, B> implements
        HasThemeVariantBuilder<C, TextFieldVariant, B> {
    /**
     * Builder method, configures the maximum the {@link IntegerField}s value might grow up to.
     *
     * @param max The max value.
     * @return this
     */
    public B setMax(int max) {
        return configure(IntegerField -> IntegerField.setMax(max));
    }

    /**
     * Builder method, configures the minimum the {@link IntegerField}s value might shrink down to.
     *
     * @param min The min value.
     * @return this
     */
    public B setMin(int min) {
        return configure(IntegerField -> IntegerField.setMin(min));
    }
}
