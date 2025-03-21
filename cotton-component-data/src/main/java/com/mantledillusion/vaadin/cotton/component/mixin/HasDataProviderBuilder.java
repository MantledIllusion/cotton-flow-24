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
 * @param <C>
 *           The {@link Component} type implementing {@link HasDataProvider}.
 * @param <T>
 *           The value type of the {@link HasDataProvider}.
 * @param <F>
 *           The type of {@link ConfigurableFilter} that is used to filter the {@link DataProvider}.
 * @param <B>
 *           The final implementation type of {@link HasDataProviderBuilder}.
 */
public interface HasDataProviderBuilder<C extends HasDataProvider<T>, T, F extends ConfigurableFilter<T>, B extends HasDataProviderBuilder<C, T, F, B>> extends
        HasItemsBuilder<C, T, B> {

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider
     *            The {@link DataProvider} to configure; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, ?> dataProvider) {
        return configure(hasDataProvider -> hasDataProvider.setDataProvider(dataProvider), true);
    }

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider
     *            The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filter
     *            The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, F> dataProvider, F filter) {
        return setDataProvider(dataProvider, () -> filter);
    }

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider
     *            The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filterSupplier
     *            A {@link Supplier} of {@link ConfigurableFilter}s to use; might be null.
     * @return this
     */
    default B setDataProvider(DataProvider<T, F> dataProvider, Supplier<F> filterSupplier) {
        return configure(hasDataProvider -> {
            F filter = filterSupplier != null ?  filterSupplier.get() : null;
            set(ConfigurableFilter.class, filter);
            if (filter != null) {
                ConfigurableFilterDataProvider<T, Void, F> configurableFilterDataProvider =
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
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback
     *            The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all elements
     *            matching the query; might <b>not</b> be null.
     * @param fetchCallback
     *            The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching the
     *            query; might <b>not</b> be null.
     * @return A new {@link HasDataProvider} instance, fully configured and bound, never null
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, Void> countCallback,
                              CallbackDataProvider.FetchCallback<T, Void> fetchCallback) {
        return configure(hasDataProvider -> hasDataProvider.setDataProvider(DataProvider.
                fromCallbacks(fetchCallback, countCallback)), true);
    }

    /**
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback
     *            The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all elements
     *            matching the query; might <b>not</b> be null.
     * @param fetchCallback
     *            The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching the
     *            query; might <b>not</b> be null.
     * @param filter
     *            The {@link ConfigurableFilter} to use; might be null.
     * @return A new {@link HasDataProvider} instance, fully configured and bound, never null
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, F> countCallback,
                              CallbackDataProvider.FetchCallback<T, F> fetchCallback, F filter) {
        return setDataProvider(countCallback, fetchCallback, () -> filter);
    }

    /**
     * Builder method, configures a new {@link CallbackDataProvider} that uses the given callbacks.
     *
     * @param countCallback
     *            The {@link CallbackDataProvider.CountCallback} that is able to determine the count of all elements
     *            matching the query; might <b>not</b> be null.
     * @param fetchCallback
     *            The {@link CallbackDataProvider.FetchCallback} that is able to fetch all elements matching the
     *            query; might <b>not</b> be null.
     * @param filterSupplier
     *            A {@link Supplier} of {@link ConfigurableFilter}s to use; might be null.
     * @return A new {@link HasDataProvider} instance, fully configured and bound, never null
     */
    default B setDataProvider(CallbackDataProvider.CountCallback<T, F> countCallback,
                              CallbackDataProvider.FetchCallback<T, F> fetchCallback, Supplier<F> filterSupplier) {
        if (countCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null count callback.");
        } else if (fetchCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null fetch callback.");
        }
        return configure(hasDataProvider -> {
            F filter = filterSupplier != null ? filterSupplier.get() : null;
            set(ConfigurableFilter.class, filter);
            CallbackDataProvider<T, F> callbackDataProvider = DataProvider.fromFilteringCallbacks(fetchCallback, countCallback);
            if (filter == null) {
                hasDataProvider.setDataProvider(callbackDataProvider);
            } else {
                ConfigurableFilterDataProvider<T, Void, F> configurableFilterDataProvider =
                        callbackDataProvider.withConfigurableFilter();
                configurableFilterDataProvider.setFilter(filter);
                filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
                hasDataProvider.setDataProvider(configurableFilterDataProvider);
            }
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
     * @param binder The {@link ModelContainer} to bind the {@link HasDataProvider}'s {@link DataProvider} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasDataProvider}'s {@link DataProvider} to; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might be null.
     * @return this
     */
    default <ModelType> B setDataProvider(ModelContainer<ModelType> binder, Property<ModelType, T> property, Supplier<F> filterSupplier) {
        return configure(hasDataProvider -> {
            F filter = filterSupplier != null ? filterSupplier.get() : null;
            set(ConfigurableFilter.class, filter);
            InMemoryDataProviderBinding<T> binding = binder.bindHasDataProvider(hasDataProvider, property, filter);
            if (filter != null) {
                filter.addConfigurationChangedListener(() -> binding.getDataProvider().refreshAll());
            }
        }, true);
    }
}
