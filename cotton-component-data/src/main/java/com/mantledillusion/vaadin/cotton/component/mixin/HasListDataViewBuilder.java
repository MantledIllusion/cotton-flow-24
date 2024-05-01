package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.HasListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.ListDataView;

import java.util.Collection;

/**
 * {@link ConfigurationBuilder} for {@link HasListDataView} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasListDataView}.
 * @param <T>
 *            The value type of the {@link HasListDataView}.
 * @param <V>
 *            The view type of the {@link ListDataView}.
 * @param <B>
 *            The final implementation type of {@link HasListDataViewBuilder}.
 */
public interface HasListDataViewBuilder<C extends HasListDataView<T, V>, T, V extends ListDataView<T, ?>, B extends HasListDataViewBuilder<C, T, V, B>> extends
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
}
