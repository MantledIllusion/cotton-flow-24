package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
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
    public static class FormItemBuilder extends AbstractConfigurationBuilder<FormLayout.FormItem, FormItemBuilder> implements
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
     * @param msgId A label text or message ID to translate; might <b>not</b> be null.
     * @param component The component; might <b>not</b> be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Object msgId, Component component, Object... indexedMessageParameters) {
        return addFormItem(msgId, component, builder -> {}, indexedMessageParameters);
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, String)
     * @param msgId A label text or message ID to translate; might <b>not</b> be null.
     * @param component The component; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link FormItemBuilder}; might <b>not</b> be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Object msgId, Component component, ConfigurationCustomizer<FormItemBuilder> customizer, Object... indexedMessageParameters) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, I18N.getTranslation(msgId, indexedMessageParameters)));
        customizer.customize(formItemBuilder);
        return configure(formItemBuilder);
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, Component)
     * @param label The label; might <b>not</b> be null.
     * @param component The component; might <b>not</b> be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Component label, Component component) {
        return addFormItem(label, component, builder -> {});
    }

    /**
     * Builder method, configures a new item.
     *
     * @see FormLayout#addFormItem(Component, Component)
     * @param label The label; might <b>not</b> be null.
     * @param component The component; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link FormItemBuilder}; might <b>not</b> be null.
     * @return this
     */
    public FormLayoutBuilder addFormItem(Component label, Component component, ConfigurationCustomizer<FormItemBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, label));
        customizer.customize(formItemBuilder);
        return configure(formItemBuilder);
    }
}
