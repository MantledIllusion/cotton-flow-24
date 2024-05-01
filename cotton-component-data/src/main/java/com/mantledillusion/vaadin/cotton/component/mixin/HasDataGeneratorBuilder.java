package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataGenerator;
import com.vaadin.flow.data.provider.HasDataGenerators;

/**
 * {@link ConfigurationBuilder} for {@link HasDataGenerators} implementing {@link Component}s.
 *
 * @param <C>
 *           The {@link Component} type implementing {@link HasDataGenerators}.
 * @param <T>
 *           The value type of the {@link HasDataGenerators}.
 * @param <B>
 *           The final implementation type of {@link HasDataGeneratorBuilder}.
 */
public interface HasDataGeneratorBuilder<C extends HasDataGenerators<T>, T, B extends HasDataGeneratorBuilder<C, T, B>> extends
        ConfigurationBuilder<C, B> {

    /**
     * Builder method, configures the given {@link DataGenerator}.
     *
     * @param dataProvider The {@link DataGenerator} to configure; might <b>not</b> be null.
     * @return this
     */
    default B addDataGenerator(DataGenerator<T> dataProvider) {
        return configure(hasDataProvider -> hasDataProvider.addDataGenerator(dataProvider));
    }
}
