package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.data.provider.HasDataView;
import com.vaadin.flow.data.provider.InMemoryDataProvider;

/**
 * {@link ConfigurationBuilder} for {@link HasDataView} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasDataView}.
 * @param <T> The value type of the {@link HasDataView}.
 * @param <F> The filter type of the {@link DataView}.
 * @param <V> The view type of the {@link DataView}.
 * @param <B> The final implementation type of {@link HasDataViewBuilder}.
 */
public interface HasDataViewBuilder<C extends HasDataView<T, F, V>, T, F, V extends DataView<T>, B extends HasDataViewBuilder<C, T, F, V, B>> extends
		ConfigurationBuilder<C, B> {

	/**
	 * Builder method, configures provider of elements to set.
	 * 
	 * @see HasDataView#setItems(DataProvider)
	 * @param dataProvider The {@link DataProvider} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(DataProvider<T, F> dataProvider) {
		return configure(hasListDataView -> hasListDataView.setItems(dataProvider));
	}

	/**
	 * Builder method, configures provider of elements to set.
	 *
	 * @see HasDataView#setItems(InMemoryDataProvider)
	 * @param dataProvider The {@link InMemoryDataProvider} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(InMemoryDataProvider<T> dataProvider) {
		return configure(hasListDataView -> hasListDataView.setItems(dataProvider));
	}
}
