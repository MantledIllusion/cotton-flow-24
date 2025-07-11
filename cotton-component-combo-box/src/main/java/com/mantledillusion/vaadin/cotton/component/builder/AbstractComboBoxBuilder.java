package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasPrefixBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;

public abstract class AbstractComboBoxBuilder<C extends ComboBox<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractComboBoxBuilder<C, T, CF, B>> extends AbstractComboBoxBaseBuilder<C, ComboBox<T>, T, T, CF, B> implements
        HasPrefixBuilder<C, B>,
        HasThemeVariantBuilder<C, ComboBoxVariant, B> {

}
