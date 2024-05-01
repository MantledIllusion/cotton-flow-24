package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;

/**
 * {@link ConfigurationBuilder} for {@link ComboBox}es.
 *
 * @param <T>
 *           The value type of the {@link ComboBox}
 */
@SuppressWarnings("unused")
public class ComboBoxBuilder<T> extends AbstractComboBoxBaseBuilder<ComboBox<T>, T, T, ComboBoxBuilder<T>> implements
        HasThemeVariantBuilder<ComboBox<T>, ComboBoxVariant, ComboBoxBuilder<T>> {

    private ComboBoxBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ComboBoxBuilder<Object> create() {
        return new ComboBoxBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> ComboBoxBuilder<T> create(Class<T> elementType) {
        return new ComboBoxBuilder<>();
    }

    @Override
    protected ComboBox<T> instantiate() {
        return new ComboBox<>();
    }
}
