package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;

/**
 * {@link ConfigurationBuilder} for {@link ComboBox}es.
 *
 * @param <T>
 *           The value type of the {@link ComboBox}
 */
@SuppressWarnings("unused")
public class ComboBoxBuilder<T, F extends ConfigurableFilter<T>> extends AbstractComboBoxBaseBuilder<ComboBox<T>, T, T, F, ComboBoxBuilder<T, F>> implements
        HasThemeVariantBuilder<ComboBox<T>, ComboBoxVariant, ComboBoxBuilder<T, F>> {

    private ComboBoxBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ComboBoxBuilder<Object, ConfigurableFilter<Object>> create() {
        return new ComboBoxBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> ComboBoxBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
        return new ComboBoxBuilder<>();
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
    public static <T, F extends ConfigurableFilter<T>> ComboBoxBuilder<T, F> create(Class<T> elementType,
                                                                                    Class<F> filterType) {
        return new ComboBoxBuilder<>();
    }

    @Override
    protected ComboBox<T> instantiate() {
        return new ComboBox<>();
    }
}
