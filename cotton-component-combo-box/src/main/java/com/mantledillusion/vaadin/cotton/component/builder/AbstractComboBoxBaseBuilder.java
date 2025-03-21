package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.combobox.dataview.ComboBoxDataView;
import com.vaadin.flow.component.combobox.dataview.ComboBoxLazyDataView;
import com.vaadin.flow.component.combobox.dataview.ComboBoxListDataView;
import com.vaadin.flow.data.renderer.Renderer;

@SuppressWarnings("unused")
abstract class AbstractComboBoxBaseBuilder<C extends ComboBoxBase<C, TItem, TValue>, TItem, TValue, F extends ConfigurableFilter<TItem>, B extends AbstractComboBoxBaseBuilder<C, TItem, TValue, F, B>> extends AbstractComponentBuilder<C, B> implements
        FocusableBuilder<C, B>,
        HasDataViewBuilder<C, TItem, String, ComboBoxDataView<TItem>, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasLazyDataViewBuilder<C, TItem, String, ComboBoxLazyDataView<TItem>, B>,
        HasListDataViewBuilder<C, TItem, F, ComboBoxListDataView<TItem>, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasValidatorBuilder<C, TValue, B>,
        HasValueBuilder<C, TValue, AbstractField.ComponentValueChangeEvent<C, TValue>, B> {

    AbstractComboBoxBaseBuilder() {}

    /**
     * Builder method, configures whether users should be allowed to enter own
     * values.
     *
     * @see ComboBoxBase#setAllowCustomValue(boolean)
     * @param allowCustomValue
     *            True if custom values are allowed, false otherwise.
     * @return this
     */
    public B setAllowCustomValue(boolean allowCustomValue) {
        return configure(comboBox -> comboBox.setAllowCustomValue(allowCustomValue));
    }

    /**
     * Builder method, configures whether the {@link ComboBoxBase} should be input focussed after the page finishes loading.
     *
     * @see ComboBoxBase#setAutofocus(boolean)
     * @param autofocus True if the {@link ComboBoxBase} should be focussed, false otherwise.
     * @return this
     */
    public B setAutofocus(boolean autofocus) {
        return configure(comboBox -> comboBox.setAutofocus(autofocus));
    }

    /**
     * Builder method, configures the placeholder text that might be displayed when nothing is selected.
     *
     * @see ComboBoxBase#setPlaceholder(String)
     * @param msgId A placeholder text or message ID to translate via; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return this
     */
    public B setPlaceholder(Object msgId, Object... indexedMessageParameters) {
        return configure(comboBox -> comboBox.setPlaceholder(I18N.getTranslation(msgId, indexedMessageParameters)));
    }

    /**
     * Builder method, configures the {@link ComboBoxBase} to be marked as required.
     *
     * @see ComboBoxBase#setRequired(boolean)
     * @param required True if the {@link ComboBoxBase} should be marked as required, false otherwise.
     * @return this
     */
    public B setRequired(boolean required) {
        return configure(comboBox -> comboBox.setRequired(required));
    }

    /**
     * Builder method, configures the {@link ItemLabelGenerator} that is used to render a label of the currently selected
     * value.
     * <p>
     * if no {@link Renderer} is set to {@link #setItemRenderer(Renderer)}, the given {@link ItemLabelGenerator} will
     * also be used to render the items in the {@link ComboBoxBase}' drop down.
     * <p>
     * Renamed from the original {@link ComboBoxBase} method to mark the difference to {@link #setItemRenderer(Renderer)}.
     *
     * @see ComboBoxBase#setItemLabelGenerator(ItemLabelGenerator)
     * @param itemLabelGenerator The {@link ItemLabelGenerator} to set; might <b>not</b> be null.
     * @return this
     */
    public B setValueRenderer(ItemLabelGenerator<TItem> itemLabelGenerator) {
        return configure(comboBox -> comboBox.setItemLabelGenerator(itemLabelGenerator));
    }

    /**
     * Builder method, configures the {@link Renderer} that is used to render the items in the {@link ComboBoxBase}'
     * drop down.
     * <p>
     * Renamed from the original {@link ComboBoxBase} method to mark the difference to {@link #setValueRenderer(ItemLabelGenerator)}.
     *
     * @see ComboBoxBase#setRenderer(Renderer)
     * @param renderer The {@link Renderer} to set; might <b>not</b> be null.
     * @return this
     */
    public B setItemRenderer(Renderer<TItem> renderer) {
        return configure(comboBox -> comboBox.setRenderer(renderer));
    }

    /**
     * Builder method, configures a listener for {@link ComboBoxBase.CustomValueSetEvent}s.
     *
     * @see ComboBoxBase#addCustomValueSetListener(ComponentEventListener)
     * @param listener The listener to configure; might <b>not</b> be null.
     * @return this
     */
    public B addCustomValueSetListener(ComponentEventListener<ComboBoxBase.CustomValueSetEvent<C>> listener) {
        return configure(comboBox -> comboBox.addCustomValueSetListener(listener));
    }
}
