package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBoxVariant;

import java.util.Set;

/**
 * {@link ConfigurationBuilder} for {@link MultiSelectComboBox}es.
 *
 * @param <T>
 *           The value type of the {@link MultiSelectComboBox}
 */
@SuppressWarnings("unused")
public class MultiSelectComboBoxBuilder<T> extends AbstractComboBoxBaseBuilder<MultiSelectComboBox<T>, T, Set<T>, MultiSelectComboBoxBuilder<T>> implements
        HasThemeVariantBuilder<MultiSelectComboBox<T>, MultiSelectComboBoxVariant, MultiSelectComboBoxBuilder<T>> {

    private MultiSelectComboBoxBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static MultiSelectComboBoxBuilder<Object> create() {
        return new MultiSelectComboBoxBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> MultiSelectComboBoxBuilder<T> create(Class<T> elementType) {
        return new MultiSelectComboBoxBuilder<>();
    }

    @Override
    protected MultiSelectComboBox<T> instantiate() {
        return new MultiSelectComboBox<>();
    }
}
