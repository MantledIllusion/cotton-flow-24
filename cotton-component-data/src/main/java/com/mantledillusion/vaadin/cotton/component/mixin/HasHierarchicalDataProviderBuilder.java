package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.model.InMemoryDataProviderBinding;
import com.mantledillusion.vaadin.cotton.model.ModelContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HasHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalDataProvider;

import java.util.function.Supplier;

/**
 * {@link ConfigurationBuilder} for {@link HasHierarchicalDataProvider} implementing {@link Component}s.
 *
 * @param <C>
 *           The {@link Component} type implementing {@link HasHierarchicalDataProvider}.
 * @param <T>
 *           The value type of the {@link HasHierarchicalDataProvider}.
 * @param <F>
 *           The type of {@link ConfigurableFilter} that is used to filter the {@link DataProvider}.
 * @param <B>
 *           The final implementation type of {@link HasHierarchicalDataProviderBuilder}.
 */
public interface HasHierarchicalDataProviderBuilder<C extends HasHierarchicalDataProvider<T>, T, F extends ConfigurableFilter<T>, B extends HasHierarchicalDataProviderBuilder<C, T, F, B>>
        extends ConfigurationBuilder<C, B> {


    /**
     * Builder method, configures the given {@link HierarchicalDataProvider}.
     *
     * @param dataProvider
     *            The {@link HierarchicalDataProvider} to configure; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(HierarchicalDataProvider<T, ?> dataProvider) {
        return configure(hasDataProvider -> hasDataProvider.setDataProvider(dataProvider), true);
    }

    /**
     * Builder method, configures the given {@link HierarchicalDataProvider}.
     *
     * @param dataProvider
     *            The {@link HierarchicalDataProvider} to configure; might <b>not</b> be null.
     * @param filter
     *            The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(HierarchicalDataProvider<T, F> dataProvider, F filter) {
        return setDataProvider(dataProvider, () -> filter);
    }

    /**
     * Builder method, configures the given {@link HierarchicalDataProvider}.
     *
     * @param dataProvider
     *            The {@link HierarchicalDataProvider} to configure; might <b>not</b> be null.
     * @param filterSupplier
     *            A {@link Supplier} of {@link ConfigurableFilter}s to use; might be null.
     * @return this
     */
    default B setDataProvider(HierarchicalDataProvider<T, F> dataProvider, Supplier<F> filterSupplier) {
        return configure(hasDataProvider -> {
            F filter = filterSupplier != null ?  filterSupplier.get() : null;
            set(ConfigurableFilter.class, filter);
            if (filter != null) {
                HierarchicalConfigurableFilterDataProvider<T, Void, F> configurableFilterDataProvider =
                        dataProvider.withConfigurableFilter();
                configurableFilterDataProvider.setFilter(filter);
                filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
                hasDataProvider.setDataProvider(configurableFilterDataProvider);
            } else {
                hasDataProvider.setDataProvider(dataProvider);
            }
        }, true);
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property) {
        return configure(hasDataProvider -> binder.bindHasHierarchicalDataProvider(hasDataProvider, property));
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @param filter The {@link ConfigurableFilter} to use; might be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property, F filter) {
        return setDataProvider(binder, property, () -> filter);
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasHierarchicalDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property, Supplier<F> filterSupplier) {
        return configure(hasDataProvider -> {
            F filter = filterSupplier != null ? filterSupplier.get() : null;
            set(ConfigurableFilter.class, filter);
            InMemoryDataProviderBinding<T> binding = binder.bindHasHierarchicalDataProvider(hasDataProvider, property, filter);
            if (filter != null) {
                filter.addConfigurationChangedListener(() -> binding.getDataProvider().refreshAll());
            }
        }, true);
    }
}
