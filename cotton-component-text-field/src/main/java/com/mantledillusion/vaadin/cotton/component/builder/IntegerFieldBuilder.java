package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.textfield.IntegerField;


/**
 * {@link ConfigurationBuilder} for {@link IntegerField}s.
 */
public class IntegerFieldBuilder extends AbstractIntegerFieldBuilder<IntegerField, IntegerFieldBuilder> {

    private IntegerFieldBuilder() {}

    @Override
    protected IntegerField instantiate() {
        return new IntegerField();
    }
    
    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static IntegerFieldBuilder create() {
        return new IntegerFieldBuilder();
    }

}
