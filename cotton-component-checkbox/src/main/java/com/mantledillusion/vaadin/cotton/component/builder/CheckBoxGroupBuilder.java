package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupDataView;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupListDataView;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.Set;

/**
 * {@link ConfigurationBuilder} for {@link CheckboxGroup}s.
 *
 * @param <T> The value type of the {@link CheckboxGroup}
 * @param <CF> The configurable filter type of the {@link CheckboxGroup}
 */
public class CheckBoxGroupBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractCheckBoxGroupBuilder<CheckboxGroup<T>, T, CF, CheckBoxGroupBuilder<T, CF>> {

	private CheckBoxGroupBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static CheckBoxGroupBuilder<Object, ConfigurableFilter<Object>> create() {
		return new CheckBoxGroupBuilder<>();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @param <T> The element type.
	 * @param elementType The class type of the element; might be null.
	 * @return A new instance, never null.
	 */
	public static <T> CheckBoxGroupBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
		return new CheckBoxGroupBuilder<>();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @param <T> The element type.
	 * @param <F> The filter type.
	 * @param elementType The class type of the element; might be null.
	 * @param filterType The class type of the filter; might be null.
	 * @return A new instance, never null.
	 */
	public static <T, F extends ConfigurableFilter<T>> CheckBoxGroupBuilder<T, F> create(Class<T> elementType,
																						 Class<F> filterType) {
		return new CheckBoxGroupBuilder<>();
	}

	@Override
	protected CheckboxGroup<T> instantiate() {
		return new CheckboxGroup<>();
	}

}
