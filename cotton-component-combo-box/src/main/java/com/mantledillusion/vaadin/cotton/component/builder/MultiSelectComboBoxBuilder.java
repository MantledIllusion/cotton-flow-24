package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;


/**
 * {@link ConfigurationBuilder} for {@link MultiSelectComboBox}es.
 *
 * @param <T> The value type of the {@link MultiSelectComboBox}
 * @param <CF> The configurable filter type of the {@link MultiSelectComboBox}
 */
@SuppressWarnings("unused")
public class MultiSelectComboBoxBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractMultiSelectComboBoxBuilder<MultiSelectComboBox<T>, T, CF, MultiSelectComboBoxBuilder<T, CF>> {

    private MultiSelectComboBoxBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static MultiSelectComboBoxBuilder<Object, ConfigurableFilter<Object>> create() {
        return new MultiSelectComboBoxBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> MultiSelectComboBoxBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
        return new MultiSelectComboBoxBuilder<>();
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
    public static <T, F extends ConfigurableFilter<T>> MultiSelectComboBoxBuilder<T, F> create(Class<T> elementType,
                                                                                               Class<F> filterType) {
        return new MultiSelectComboBoxBuilder<>();
    }

    @Override
    protected MultiSelectComboBox<T> instantiate() {
        return new MultiSelectComboBox<>();
    }
}
