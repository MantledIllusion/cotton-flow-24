package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.HasLazyDataView;
import com.vaadin.flow.data.provider.LazyDataView;

/**
 * {@link ConfigurationBuilder} for {@link HasLazyDataView} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasLazyDataView}.
 * @param <T> The value type of the {@link HasLazyDataView}.
 * @param <F> The filter type of the {@link LazyDataView}.
 * @param <V> The view type of the {@link LazyDataView}.
 * @param <B> The final implementation type of {@link HasLazyDataViewBuilder}.
 */
public interface HasLazyDataViewBuilder<C extends HasLazyDataView<T, F, V>, T, F, V extends LazyDataView<T>, B extends HasLazyDataViewBuilder<C, T, F, V, B>> extends
		ConfigurationBuilder<C, B> {

	/**
	 * Builder method, configures provider of elements to set.
	 *
	 * @see HasLazyDataView#setItems(BackEndDataProvider)
	 * @param dataProvider The {@link BackEndDataProvider} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(BackEndDataProvider<T, F> dataProvider) {
		return configure(hasListDataView -> hasListDataView.setItems(dataProvider));
	}

	/**
	 * Builder method, configures provider of elements to set.
	 *
	 * @see HasLazyDataView#setItems(CallbackDataProvider.FetchCallback)
	 * @param fetchCallback The {@link CallbackDataProvider.FetchCallback} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(CallbackDataProvider.FetchCallback<T, F> fetchCallback) {
		return configure(hasListDataView -> hasListDataView.setItems(fetchCallback));
	}

	/**
	 * Builder method, configures provider of elements to set.
	 *
	 * @see HasLazyDataView#setItems(CallbackDataProvider.FetchCallback)
	 * @param fetchCallback The {@link CallbackDataProvider.FetchCallback} to set; might <b>not</b> be null.
	 * @param countCallback The {@link CallbackDataProvider.CountCallback} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(CallbackDataProvider.FetchCallback<T, F> fetchCallback, CallbackDataProvider.CountCallback<T, F> countCallback) {
		return configure(hasListDataView -> hasListDataView.setItems(fetchCallback, countCallback));
	}
}
