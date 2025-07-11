package com.mantledillusion.vaadin.cotton.component.builder;

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

public abstract class AbstractCheckBoxGroupBuilder<C extends CheckboxGroup<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractCheckBoxGroupBuilder<C, T, CF, B>> extends AbstractComponentBuilder<C, B> implements
        HasVoidFilterDataViewBuilder<C, T, CF, CheckboxGroupDataView<T>, B>,
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasListDataViewBuilder<C, T, CF, CheckboxGroupListDataView<T>, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, CheckboxGroupVariant, B>,
        HasTooltipBuilder<C, B>,
        HasValueBuilder<C, CheckboxGroup<T>, Set<T>, AbstractField.ComponentValueChangeEvent<CheckboxGroup<T>, Set<T>>, B> {

    /**
     * Builder method, configures the predicate to use for item enablement.
     *
     * @param itemEnabledProvider The predicate enabling items; might <b>not</b> be null.
     * @return this
     * @see CheckboxGroup#setItemEnabledProvider(SerializablePredicate)
     */
    public B setItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        return configure(checkBoxGroup -> checkBoxGroup.setItemEnabledProvider(itemEnabledProvider));
    }

    /**
     * Builder method, configures a generator to use for item label generation.
     *
     * @param itemLabelGenerator The generator for item labels; might <b>not</b> be null.
     * @return this
     * @see CheckboxGroup#setItemLabelGenerator(ItemLabelGenerator)
     */
    public B setItemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        return configure(checkBoxGroup -> checkBoxGroup.setItemLabelGenerator(itemLabelGenerator));
    }

    /**
     * Builder method, configures the {@link Renderer} that is used to render the items of the {@link CheckboxGroup}.
     *
     * @param renderer The {@link Renderer} to set; might <b>not</b> be null.
     * @return this
     * @see CheckboxGroup#setRenderer(ComponentRenderer)
     */
    public <C extends Component> B setItemRenderer(ComponentRenderer<C, T> renderer) {
        return configure(comboBox -> comboBox.setRenderer(renderer));
    }

    /**
     * Builder method, configures a listener for {@link com.vaadin.flow.data.selection.MultiSelectionEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     * @see CheckboxGroup#addSelectionListener(MultiSelectionListener)
     */
    public B addSelectionListener(MultiSelectionListener<CheckboxGroup<T>, T> listener) {
        return configure(checkBoxGroup -> checkBoxGroup.addSelectionListener(listener));
    }
}
