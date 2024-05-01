package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.util.function.Function;

/**
 * {@link ConfigurationBuilder} for {@link FormLayout}s.
 */
public class FormLayoutBuilder extends AbstractComponentBuilder<FormLayout, FormLayoutBuilder> implements
        HasElementBuilder<FormLayout, FormLayoutBuilder>,
        HasEnabledBuilder<FormLayout, FormLayoutBuilder>,
        HasSizeBuilder<FormLayout, FormLayoutBuilder>,
        HasStyleBuilder<FormLayout, FormLayoutBuilder>,
        HasComponentsBuilder<FormLayout, FormLayoutBuilder> {

    private FormLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static FormLayoutBuilder create() {
        return new FormLayoutBuilder();
    }

    @Override
    protected FormLayout instantiate() {
        return new FormLayout();
    }

    /**
     * {@link ConfigurationBuilder} for {@link FormLayout.FormItem}s.
     */
    public class FormItemBuilder extends AbstractConfigurationBuilder<FormLayout.FormItem, FormItemBuilder> implements
            HasElementBuilder<FormLayout.FormItem, FormItemBuilder>,
            HasStyleBuilder<FormLayout.FormItem, FormItemBuilder>,
            HasComponentsBuilder<FormLayout.FormItem, FormItemBuilder>,
            ClickNotifierBuilder<FormLayout.FormItem, FormItemBuilder>,
            Configurer<FormLayout> {

        private final Function<FormLayout, FormLayout.FormItem> formItemSupplier;

        FormItemBuilder(Function<FormLayout, FormLayout.FormItem> formItemSupplier) {
            this.formItemSupplier = formItemSupplier;
        }

        @Override
        public void configure(FormLayout component) {
            apply(this.formItemSupplier.apply(component));
        }

        /**
         * Adds the currently configured item to the {@link FormLayout} being build by the returned {@link FormLayoutBuilder}.
         *
         * @return The {@link FormLayoutBuilder} that started this {@link FormItemBuilder}, never null
         */
        public FormLayoutBuilder add() {
            return FormLayoutBuilder.this;
        }
    }

    /**
     * Builder method, configures the form's column behaviour.
     *
     * @see FormLayout#setResponsiveSteps(FormLayout.ResponsiveStep...)
     * @param responsiveStep
     *            The steps to exist; might <b>not</b> be null.
     * @return this
     */
    public FormLayoutBuilder setResponsiveSteps(FormLayout.ResponsiveStep... responsiveStep) {
        return configure(form -> form.setResponsiveSteps(responsiveStep));
    }

    /**
     * Builder method, adds a new item.
     *
     * @see FormLayout#addFormItem(Component, String)
     * @param component The component; might <b>not</b> be null.
     * @param msgId A label text or message ID to translate; might <b>not</b> be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Component component, Object msgId, Object... indexedMessageParameters) {
        return configureFormItem(component, msgId, indexedMessageParameters).add();
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, String)
     * @param component The component; might <b>not</b> be null.
     * @param msgId A label text or message ID to translate; might <b>not</b> be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return A new {@link FormItemBuilder}, never null
     */
    public FormItemBuilder configureFormItem(Component component, Object msgId, Object... indexedMessageParameters) {
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, I18N.getTranslation(msgId, indexedMessageParameters)));
        configure(formItemBuilder);
        return formItemBuilder;
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, Component)
     * @param component The component; might <b>not</b> be null.
     * @param label The label; might <b>not</b> be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Component component, Component label) {
        return configureFormItem(component, label).add();
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, Component)
     * @param component The component; might <b>not</b> be null.
     * @param label The label; might <b>not</b> be null.
     * @return A new {@link FormItemBuilder}, never null
     */
    public FormItemBuilder configureFormItem(Component component, Component label) {
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, label));
        configure(formItemBuilder);
        return formItemBuilder;
    }
}
