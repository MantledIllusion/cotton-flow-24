package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

/**
 * {@link ConfigurationBuilder} for {@link IntegerField}s.
 */
public class IntegerFieldBuilder extends AbstractTextFieldBaseBuilder<IntegerField, Integer, IntegerFieldBuilder> implements
        HasThemeVariantBuilder<IntegerField, TextFieldVariant, IntegerFieldBuilder> {

    private IntegerFieldBuilder() {}
    
    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static IntegerFieldBuilder create() {
        return new IntegerFieldBuilder();
    }

    @Override
    protected IntegerField instantiate() {
        return new IntegerField();
    }

    /**
     * Builder method, configures the maximum the {@link IntegerField}s value might grow up to.
     *
     * @param max The max value.
     * @return this
     */
    public IntegerFieldBuilder setMax(int max) {
        return configure(IntegerField -> IntegerField.setMax(max));
    }

    /**
     * Builder method, configures the minimum the {@link IntegerField}s value might shrink down to.
     *
     * @param min The min value.
     * @return this
     */
    public IntegerFieldBuilder setMin(int min) {
        return configure(IntegerField -> IntegerField.setMin(min));
    }
}
