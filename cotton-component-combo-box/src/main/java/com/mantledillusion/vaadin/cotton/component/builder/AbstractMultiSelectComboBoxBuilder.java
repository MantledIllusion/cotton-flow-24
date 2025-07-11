package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBoxVariant;

import java.util.Set;

@SuppressWarnings("unused")
public abstract class AbstractMultiSelectComboBoxBuilder<C extends MultiSelectComboBox<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractMultiSelectComboBoxBuilder<C, T, CF, B>> extends AbstractComboBoxBaseBuilder<C, MultiSelectComboBox<T>, T, Set<T>, CF, B> implements
        HasThemeVariantBuilder<C, MultiSelectComboBoxVariant, B> {

}
