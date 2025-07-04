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
public class CheckBoxGroupBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractComponentBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>> implements
		HasVoidFilterDataViewBuilder<CheckboxGroup<T>, T, CF, CheckboxGroupDataView<T>, CheckBoxGroupBuilder<T, CF>>,
		HasComponentsBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasElementBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasEnabledBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasLabelBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasListDataViewBuilder<CheckboxGroup<T>, T, CF, CheckboxGroupListDataView<T>, CheckBoxGroupBuilder<T, CF>>,
		HasSizeBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasStyleBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasThemeVariantBuilder<CheckboxGroup<T>, CheckboxGroupVariant, CheckBoxGroupBuilder<T, CF>>,
		HasTooltipBuilder<CheckboxGroup<T>, CheckBoxGroupBuilder<T, CF>>,
		HasValueBuilder<CheckboxGroup<T>, Set<T>, AbstractField.ComponentValueChangeEvent<CheckboxGroup<T>, Set<T>>, CheckBoxGroupBuilder<T, CF>> {

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

	/**
	 * Builder method, configures the predicate to use for item enablement.
	 *
	 * @see CheckboxGroup#setItemEnabledProvider(SerializablePredicate)
	 * @param itemEnabledProvider
	 *            The predicate enabling items; might <b>not</b> be null.
	 * @return this
	 */
	public CheckBoxGroupBuilder<T, CF> setItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
		return configure(checkBoxGroup -> checkBoxGroup.setItemEnabledProvider(itemEnabledProvider));
	}

	/**
	 * Builder method, configures a generator to use for item label generation.
	 *
	 * @see CheckboxGroup#setItemLabelGenerator(ItemLabelGenerator)
	 * @param itemLabelGenerator The generator for item labels; might <b>not</b> be null.
	 * @return this
	 */
	public CheckBoxGroupBuilder<T, CF> setItemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
		return configure(checkBoxGroup -> checkBoxGroup.setItemLabelGenerator(itemLabelGenerator));
	}

	/**
	 * Builder method, configures the {@link Renderer} that is used to render the items of the {@link CheckboxGroup}.
	 *
	 * @see CheckboxGroup#setRenderer(ComponentRenderer)
	 * @param renderer The {@link Renderer} to set; might <b>not</b> be null.
	 * @return this
	 */
	public <C extends Component> CheckBoxGroupBuilder<T, CF> setItemRenderer(ComponentRenderer<C, T> renderer) {
		return configure(comboBox -> comboBox.setRenderer(renderer));
	}

	/**
	 * Builder method, configures a listener for {@link com.vaadin.flow.data.selection.MultiSelectionEvent}s.
	 * 
	 * @see CheckboxGroup#addSelectionListener(MultiSelectionListener)
	 * @param listener 
	 * 			The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public CheckBoxGroupBuilder<T, CF> addSelectionListener(MultiSelectionListener<CheckboxGroup<T>, T> listener) {
		return configure(checkBoxGroup -> checkBoxGroup.addSelectionListener(listener));
	}
}
