package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupDataView;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupListDataView;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.function.SerializablePredicate;

/**
 * {@link ConfigurationBuilder} for {@link RadioButtonGroup}s.
 *
 * @param <T>
 *           The value type of the {@link RadioButtonGroup}
 * @param <F>
 *           The filter type of the {@link RadioButtonGroup}
 */
public class RadioButtonGroupBuilder<T, F extends ConfigurableFilter<T>> extends AbstractComponentBuilder<RadioButtonGroup<T>, RadioButtonGroupBuilder<T, F>> implements
		HasDataViewBuilder<RadioButtonGroup<T>, T, Void, RadioButtonGroupDataView<T>, RadioButtonGroupBuilder<T, F>>,
		HasElementBuilder<RadioButtonGroup<T>, RadioButtonGroupBuilder<T, F>>,
		HasEnabledBuilder<RadioButtonGroup<T>, RadioButtonGroupBuilder<T, F>>,
		HasLabelBuilder<RadioButtonGroup<T>, RadioButtonGroupBuilder<T, F>>,
		HasListDataViewBuilder<RadioButtonGroup<T>, T, RadioButtonGroupListDataView<T>, RadioButtonGroupBuilder<T, F>>,
		HasStyleBuilder<RadioButtonGroup<T>, RadioButtonGroupBuilder<T, F>>,
		HasThemeVariantBuilder<RadioButtonGroup<T>, RadioGroupVariant, RadioButtonGroupBuilder<T, F>>,
		HasValidatorBuilder<RadioButtonGroup<T>, T, RadioButtonGroupBuilder<T, F>>,
		HasValueBuilder<RadioButtonGroup<T>, T, AbstractField.ComponentValueChangeEvent<RadioButtonGroup<T>, T>, RadioButtonGroupBuilder<T, F>> {

	private RadioButtonGroupBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static RadioButtonGroupBuilder<Object, ConfigurableFilter<Object>> create() {
		return new RadioButtonGroupBuilder<>();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @param <T> The value type.
	 * @param elementType The class type of the element; might be null.
	 * @return A new instance, never null.
	 */
	public static <T> RadioButtonGroupBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
		return new RadioButtonGroupBuilder<>();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @param <T> The value type.
	 * @param <F> The filter type.
	 * @param elementType The class type of the element; might be null.
	 * @param filterType The class type of the filter; might be null.
	 * @return A new instance, never null.
	 */
	public static <T, F extends ConfigurableFilter<T>> RadioButtonGroupBuilder<T, F> create(Class<T> elementType,
																							Class<F> filterType) {
		return new RadioButtonGroupBuilder<>();
	}

	@Override
	protected RadioButtonGroup<T> instantiate() {
		return new RadioButtonGroup<>();
	}

	/**
	 * Builder method, configures the predicate to use for item enablement.
	 *
	 * @see RadioButtonGroup#setItemEnabledProvider(SerializablePredicate)
	 * @param itemEnabledProvider
	 *            The predicate enabling items; might <b>not</b> be null.
	 * @return this
	 */
	public RadioButtonGroupBuilder<T, F> setItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
		return configure(radioButtonGroup -> radioButtonGroup.setItemEnabledProvider(itemEnabledProvider));
	}

	/**
	 * Builder method, configures a generator to use for item label generation.
	 *
	 * @see RadioButtonGroupBuilder#setItemLabelGenerator(ItemLabelGenerator)
	 * @param itemLabelGenerator The generator for item labels or message IDs to translate; might <b>not</b> be null.
	 * @return this
	 */
	public RadioButtonGroupBuilder<T, F> setItemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
		return configure(checkBoxGroup -> checkBoxGroup.setItemLabelGenerator(item ->
				I18N.getTranslation(itemLabelGenerator.apply(item))));
	}

	/**
	 * Builder method, configures a generator for item labels using a prefix and {@link Object#toString()} for building
	 * a translatable message ID.
	 *
	 * @see RadioButtonGroupBuilder#setItemLabelGenerator(ItemLabelGenerator)
	 * @param messageIdPrefix The message ID prefix to append an item's {@link Object#toString()} value to; might be null.
	 * @return this
	 */
	public RadioButtonGroupBuilder<T, F> setItemLabelMessageIdPrefix(String messageIdPrefix) {
		return configure(checkBoxGroup -> checkBoxGroup.setItemLabelGenerator(item ->
				I18N.getTranslation(messageIdPrefix == null || messageIdPrefix.isBlank() ? "" : messageIdPrefix)+item));
	}

	/**
	 * Builder method, configures the {@link Renderer} that is used to render the items of the {@link RadioButtonGroup}.
	 *
	 * @see RadioButtonGroup#setRenderer(ComponentRenderer)
	 * @param renderer The {@link Renderer} to set; might <b>not</b> be null.
	 * @return this
	 */
	public <C extends Component> RadioButtonGroupBuilder<T, F> setItemRenderer(ComponentRenderer<C, T> renderer) {
		return configure(comboBox -> comboBox.setRenderer(renderer));
	}
}
