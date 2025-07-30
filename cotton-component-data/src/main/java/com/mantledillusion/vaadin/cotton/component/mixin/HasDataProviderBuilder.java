package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.model.InMemoryDataProviderBinding;
import com.mantledillusion.vaadin.cotton.model.ModelContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasDataProvider;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.InMemoryDataProvider;

import java.util.function.Supplier;

/**
 * {@link ConfigurationBuilder} for {@link HasDataProvider} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasDataProvider}.
 * @param <T> The value type of the {@link HasDataProvider}.
 * @param <CF> The configurable filter type for bound @{@link DataProvider}s.
 * @param <B> The final implementation type of {@link HasDataProviderBuilder}.
 */
public interface HasDataProviderBuilder<C extends HasDataProvider<T>, T, CF extends ConfigurableFilter<T>, B extends HasDataProviderBuilder<C, T, CF, B>> extends
        HasItemsBuilder<C, T, B> {

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider The {@link DataProvider} to configure; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, ?> dataProvider) {
        return configure(hasDataProvider -> hasDataProvider.setDataProvider(dataProvider), true);
    }

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, CF> dataProvider, CF filter) {
        return setDataProvider(dataProvider, () -> filter);
    }

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, CF> dataProvider, Supplier<CF> filterSupplier) {
        return configure(hasDataProvider -> {
            CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
            set(ConfigurableFilter.class, filter);
            ConfigurableFilterDataProvider<T, Void, CF> configurableFilterDataProvider =
                    dataProvider.withConfigurableFilter();
            configurableFilterDataProvider.setFilter(filter);
            filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
            hasDataProvider.setDataProvider(configurableFilterDataProvider);
        }, true);
    }

    /**
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all
     *                      elements matching the query; might <b>not</b> be null.
     * @param fetchCallback The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching
     *                      the query; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, Void> countCallback,
                              CallbackDataProvider.FetchCallback<T, Void> fetchCallback) {
        return configure(hasDataProvider -> hasDataProvider.setDataProvider(DataProvider.
                fromCallbacks(fetchCallback, countCallback)), true);
    }

    /**
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all
     *                      elements matching the query; might <b>not</b> be null.
     * @param fetchCallback The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching
     *                      the query; might <b>not</b> be null.
     * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, CF> countCallback,
                              CallbackDataProvider.FetchCallback<T, CF> fetchCallback, CF filter) {
        return setDataProvider(countCallback, fetchCallback, () -> filter);
    }

    /**
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all
     *                      elements matching the query; might <b>not</b> be null.
     * @param fetchCallback The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching
     *                      the query; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, CF> countCallback,
                              CallbackDataProvider.FetchCallback<T, CF> fetchCallback, Supplier<CF> filterSupplier) {
        if (countCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null count callback.");
        } else if (fetchCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null fetch callback.");
        }
        return configure(hasDataProvider -> {
            CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
            set(ConfigurableFilter.class, filter);
            CallbackDataProvider<T, CF> callbackDataProvider = DataProvider.fromFilteringCallbacks(fetchCallback, countCallback);
            ConfigurableFilterDataProvider<T, Void, CF> configurableFilterDataProvider =
                    callbackDataProvider.withConfigurableFilter();
            configurableFilterDataProvider.setFilter(filter);
            filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
            hasDataProvider.setDataProvider(configurableFilterDataProvider);
        }, true);
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property) {
        return configure(hasDataProvider -> binder.bindHasDataProvider(hasDataProvider, property));
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property, CF filter) {
        return setDataProvider(binder, property, () -> filter);
    }

    /**
     * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param binder The {@link ModelContainer} to bind the {@link HasDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property, Supplier<CF> filterSupplier) {
        return configure(hasDataProvider -> {
            CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
            set(ConfigurableFilter.class, filter);
            InMemoryDataProviderBinding<ModelType, T> binding = binder.bindHasDataProvider(hasDataProvider, property, filter);
            filter.addConfigurationChangedListener(() -> binding.getDataProvider().refreshAll());
        }, true);
    }
}
