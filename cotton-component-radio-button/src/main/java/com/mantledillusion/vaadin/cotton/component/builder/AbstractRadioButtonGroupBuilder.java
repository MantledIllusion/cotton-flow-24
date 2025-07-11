package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
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

public abstract class AbstractRadioButtonGroupBuilder<C extends RadioButtonGroup<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractRadioButtonGroupBuilder<C, T, CF, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>, 
        HasEnabledBuilder<C, B>, 
        HasLabelBuilder<C, B>, 
        HasListDataViewBuilder<C, T, CF, RadioButtonGroupListDataView<T>, B>, 
        HasStyleBuilder<C, B>, 
        HasThemeVariantBuilder<C, RadioGroupVariant, B>, 
        HasTooltipBuilder<C, B>, 
        HasValidatorBuilder<C, T, B>, 
        HasValueBuilder<C, RadioButtonGroup<T>, T, AbstractField.ComponentValueChangeEvent<RadioButtonGroup<T>, T>, B>,
        HasVoidFilterDataViewBuilder<C, T, CF, RadioButtonGroupDataView<T>, B> {
    
    /**
     * Builder method, configures the predicate to use for item enablement.
     *
     * @param itemEnabledProvider The predicate enabling items; might <b>not</b> be null.
     * @return this
     * @see RadioButtonGroup#setItemEnabledProvider(SerializablePredicate)
     */
    public B setItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        return configure(radioButtonGroup -> radioButtonGroup.setItemEnabledProvider(itemEnabledProvider));
    }

    /**
     * Builder method, configures a generator to use for item label generation.
     *
     * @param itemLabelGenerator The generator for item labels; might <b>not</b> be null.
     * @return this
     * @see RadioButtonGroupBuilder#setItemLabelGenerator(ItemLabelGenerator)
     */
    public B setItemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        return configure(checkBoxGroup -> checkBoxGroup.setItemLabelGenerator(itemLabelGenerator));
    }

    /**
     * Builder method, configures the {@link Renderer} that is used to render the items of the {@link RadioButtonGroup}.
     *
     * @param renderer The {@link Renderer} to set; might <b>not</b> be null.
     * @return this
     * @see RadioButtonGroup#setRenderer(ComponentRenderer)
     */
    public <C extends Component> B setItemRenderer(ComponentRenderer<C, T> renderer) {
        return configure(comboBox -> comboBox.setRenderer(renderer));
    }
}
