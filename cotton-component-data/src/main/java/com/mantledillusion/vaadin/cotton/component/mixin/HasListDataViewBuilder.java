package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.model.InMemoryDataProviderBinding;
import com.mantledillusion.vaadin.cotton.model.ModelContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.*;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * {@link ConfigurationBuilder} for {@link HasListDataView} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasListDataView}.
 * @param <T> The value type of the {@link HasListDataView}.
 * @param <CF> The configurable filter type for bound @{@link DataProvider}s.
 * @param <V>  The view type of the {@link ListDataView}.
 * @param <B> The final implementation type of {@link HasListDataViewBuilder}.
 */
public interface HasListDataViewBuilder<C extends HasListDataView<T, V>, T, CF extends ConfigurableFilter<T>, V extends ListDataView<T, ?>, B extends HasListDataViewBuilder<C, T, CF, V, B>> extends
		ConfigurationBuilder<C, B> {

	/**
	 * Builder method, configures a {@link Collection} of elements to set.
	 * 
	 * @see HasListDataView#setItems(Collection)
	 * @param elements
	 *            The element {@link Collection} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(Collection<T> elements) {
		return configure(hasListDataView -> hasListDataView.setItems(elements));
	}

	/**
	 * Builder method, configures an array of elements to set.
	 * 
	 * @see HasListDataView#setItems(Object...)
	 * @param elements
	 *            The element array to set.
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	default B setItems(T... elements) {
		return configure(hasListDataView -> hasListDataView.setItems(elements));
	}

	/**
	 * Builder method, configures provider of elements to set.
	 * 
	 * @see HasListDataView#setItems(ListDataProvider)
	 * @param dataProvider The {@link ListDataProvider} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(ListDataProvider<T> dataProvider) {
		return configure(hasListDataView -> hasListDataView.setItems(dataProvider));
	}

	/**
	 * Builder method, configures the given {@link DataProvider}.
	 *
	 * @see HasListDataView#setItems(ListDataProvider)
	 * @param dataProvider The {@link ListDataProvider} to set; might <b>not</b> be null.
	 * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(ListDataProvider<T> dataProvider, CF filter) {
		return setItems(dataProvider, () -> filter);
	}

	/**
	 * Builder method, configures the given {@link DataProvider}.
	 *
	 * @see HasListDataView#setItems(ListDataProvider)
	 * @param dataProvider The {@link ListDataProvider} to set; might <b>not</b> be null.
	 * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(ListDataProvider<T> dataProvider, Supplier<CF> filterSupplier) {
		return configure(hasListDataView -> {
			CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
			set(ConfigurableFilter.class, filter);
			dataProvider.setFilter(filter);
			filter.addConfigurationChangedListener(dataProvider::refreshAll);
			hasListDataView.setItems(dataProvider);
		}, true);
	}

	/**
	 * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
	 *
	 * @param <ModelType> The type of the model to whose property to bind.
	 * @param binder The {@link ModelContainer} to bind the {@link HasListDataView}'s items with; might <b>not</b> be null.
	 * @param property The {@link Property} to bind the {@link HasListDataView}'s items to; might <b>not</b> be null.
	 * @return this
	 */
	default <ModelType> B setItems(ModelContainer<ModelType> binder, Property<ModelType, T> property) {
		return configure(hasListDataView -> binder.bindHasListDataView(hasListDataView, property));
	}

	/**
	 * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
	 *
	 * @param <ModelType> The type of the model to whose property to bind.
	 * @param binder The {@link ModelContainer} to bind the {@link HasListDataView}'s items with; might <b>not</b> be null.
	 * @param property The {@link Property} to bind the {@link HasListDataView}'s items to; might <b>not</b> be null.
	 * @param filter The {@link ConfigurableFilter} to use; might <b>not</b> be null.
	 * @return this
	 */
	default <ModelType> B setItems(ModelContainer<ModelType> binder, Property<ModelType, T> property, CF filter) {
		return setItems(binder, property, () -> filter);
	}

	/**
	 * Builder method, configures an {@link InMemoryDataProvider} bound to the given {@link Property} using the given {@link ModelContainer}.
	 *
	 * @param <ModelType> The type of the model to whose property to bind.
	 * @param binder The {@link ModelContainer} to bind the {@link HasListDataView}'s items with; might <b>not</b> be null.
	 * @param property The {@link Property} to bind the {@link HasListDataView}'s items to; might <b>not</b> be null.
	 * @param filterSupplier A {@link Supplier} of {@link ConfigurableFilter}s to use; might <b>not</b> be null.
	 * @return this
	 */
	default <ModelType> B setItems(ModelContainer<ModelType> binder, Property<ModelType, T> property, Supplier<CF> filterSupplier) {
		return configure(hasListDataView -> {
			CF filter = ConfigurableFilter.supplyFilter(filterSupplier);
			set(ConfigurableFilter.class, filter);
			InMemoryDataProviderBinding<ModelType, T> binding = binder.bindHasListDataView(hasListDataView, property, filter);
			filter.addConfigurationChangedListener(() -> binding.getDataProvider().refreshAll());
		}, true);
	}
}
