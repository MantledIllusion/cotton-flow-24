package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasItems;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * {@link ConfigurationBuilder} for {@link HasItems} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasItems}.
 * @param <T> The value type of the {@link HasItems}.
 * @param <B> The final implementation type of {@link HasItemsBuilder}.
 */
public interface HasItemsBuilder<C extends HasItems<T>, T, B extends HasItemsBuilder<C, T, B>> extends
		ConfigurationBuilder<C, B> {

	/**
	 * Builder method, configures a {@link Collection} of elements to set.
	 * 
	 * @see HasItems#setItems(Collection)
	 * @param elements The element {@link Collection} to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(Collection<T> elements) {
		return configure(hasItems -> hasItems.setItems(elements));
	}

	/**
	 * Builder method, configures an array of elements to set.
	 * 
	 * @see HasItems#setItems(Object...)
	 * @param elements The element array to set.
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	default B setItems(T... elements) {
		return configure(hasItems -> hasItems.setItems(elements));
	}

	/**
	 * Builder method, configures a S of elements to set.
	 * 
	 * @see HasItems#setItems(Stream)
	 * @param elementStream The element stream to set; might <b>not</b> be null.
	 * @return this
	 */
	default B setItems(Stream<T> elementStream) {
		if (elementStream == null) {
			throw new IllegalArgumentException("Cannot set a null element stream");
		}
		return configure(hasItems -> hasItems.setItems(elementStream));
	}
}
