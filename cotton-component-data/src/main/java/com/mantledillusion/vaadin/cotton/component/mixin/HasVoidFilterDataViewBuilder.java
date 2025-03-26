package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.data.provider.*;

import java.util.function.Supplier;

public interface HasVoidFilterDataViewBuilder<C extends HasDataView<T, Void, V>, T, CF extends ConfigurableFilter<T>, V extends DataView<T>, B extends HasVoidFilterDataViewBuilder<C, T, CF, V, B>> extends
        HasDataViewBuilder<C, T, Void, V, B> {

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
     * @return this
     */
    default B setItems(DataProvider<T, CF> dataProvider, CF filter) {
        return setItems(dataProvider, () -> filter);
    }

    /**
     * Builder method, configures the given {@link DataProvider}.
     *
     * @param dataProvider The {@link DataProvider} to configure; might <b>not</b> be null.
     * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
     * @return this
     */
    default B setItems(DataProvider<T, CF> dataProvider, Supplier<CF> filterSupplier) {
        return configure(hasDataView -> {
            CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
            set(ConfigurableFilter.class, filter);
            ConfigurableFilterDataProvider<T, Void, CF> configurableFilterDataProvider =
                    dataProvider.withConfigurableFilter();
            configurableFilterDataProvider.setFilter(filter);
            filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
            hasDataView.setItems(configurableFilterDataProvider);
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
    default B setItems(CallbackDataProvider.CountCallback<T, Void> countCallback,
                       CallbackDataProvider.FetchCallback<T, Void> fetchCallback) {
        return configure(hasDataProvider -> hasDataProvider.setItems(DataProvider.
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
    default B setItems(CallbackDataProvider.CountCallback<T, CF> countCallback,
                       CallbackDataProvider.FetchCallback<T, CF> fetchCallback, CF filter) {
        return setItems(countCallback, fetchCallback, () -> filter);
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
    default B setItems(CallbackDataProvider.CountCallback<T, CF> countCallback,
                       CallbackDataProvider.FetchCallback<T, CF> fetchCallback, Supplier<CF> filterSupplier) {
        if (countCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null count callback.");
        } else if (fetchCallback == null) {
            throw new IllegalArgumentException("Cannot configure a data provider from a null fetch callback.");
        }
        return configure(hasDataView -> {
            CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
            set(ConfigurableFilter.class, filter);
            CallbackDataProvider<T, CF> callbackDataProvider = DataProvider.fromFilteringCallbacks(fetchCallback, countCallback);
            ConfigurableFilterDataProvider<T, Void, CF> configurableFilterDataProvider =
                    callbackDataProvider.withConfigurableFilter();
            configurableFilterDataProvider.setFilter(filter);
            filter.addConfigurationChangedListener(configurableFilterDataProvider::refreshAll);
            hasDataView.setItems(configurableFilterDataProvider);
        }, true);
    }
}
