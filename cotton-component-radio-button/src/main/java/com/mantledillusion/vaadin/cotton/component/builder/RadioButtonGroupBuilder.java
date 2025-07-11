package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;


/**
 * {@link ConfigurationBuilder} for {@link RadioButtonGroup}s.
 *
 * @param <T> The value type of the {@link RadioButtonGroup}
 * @param <CF> The configurable filter type of the {@link RadioButtonGroup}
 */
public class RadioButtonGroupBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractRadioButtonGroupBuilder<RadioButtonGroup<T>, T, CF, RadioButtonGroupBuilder<T, CF>> {

	private RadioButtonGroupBuilder() {}

	@Override
	protected RadioButtonGroup<T> instantiate() {
		return new RadioButtonGroup<>();
	}

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
}
