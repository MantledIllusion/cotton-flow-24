package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.details.Details;


/**
 * {@link ConfigurationBuilder} for {@link Details}s.
 */
@SuppressWarnings("unused")
public class DetailsBuilder extends AbstractDetailsBuilder<Details, DetailsBuilder> {
    
    private DetailsBuilder() {}

    @Override
    protected Details instantiate() {
        return new Details();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static DetailsBuilder create() {
        return new DetailsBuilder();
    }
}
