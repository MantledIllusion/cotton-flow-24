package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.util.function.Function;

/**
 * {@link ConfigurationBuilder} for subtypes of {@link FormLayout}.
 */
public abstract class AbstractFormLayoutBuilder<C extends FormLayout, B extends AbstractFormLayoutBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasComponentsBuilder<C, B> {

    public AbstractFormLayoutBuilder() {}

    /**
     * {@link ConfigurationBuilder} for {@link FormLayout.FormItem}s.
     */
    public class FormItemBuilder extends AbstractConfigurationBuilder<FormLayout.FormItem, FormItemBuilder> implements
            Configurer<C>,
            ClickNotifierBuilder<FormLayout.FormItem, FormLayout.FormItem, FormItemBuilder>,
            HasElementBuilder<FormLayout.FormItem, FormItemBuilder>,
            HasComponentsBuilder<FormLayout.FormItem, FormItemBuilder>,
            HasStyleBuilder<FormLayout.FormItem, FormItemBuilder> {

        private final Function<C, FormLayout.FormItem> formItemSupplier;

        private FormItemBuilder(Function<C, FormLayout.FormItem> formItemSupplier) {
            this.formItemSupplier = formItemSupplier;
        }

        @Override
        public void configure(C component) {
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
    public B setResponsiveSteps(FormLayout.ResponsiveStep... responsiveStep) {
        return configure(form -> form.setResponsiveSteps(responsiveStep));
    }

    /**
     * Builder method, adds a new item.
     *
     * @see FormLayout#addFormItem(Component, String)
     * @param label The label to set; might be null.
     * @param component The component; might <b>not</b> be null.
     * @return this
     */
    public B addFormItem(String label, Component component) {
        return addFormItem(label, component, builder -> {});
    }

    /**
     * Builder method, configures a new item.
     *
     *
     *  @see FormLayout#addFormItem(Component, String)
     * @param label The label to use; might be null.
     * @param component The component; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link FormItemBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B addFormItem(String label, Component component, ConfigurationCustomizer<FormItemBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, label));
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
    public B addFormItem(Component label, Component component) {
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
    public B addFormItem(Component label, Component component, ConfigurationCustomizer<FormItemBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        FormItemBuilder formItemBuilder = new FormItemBuilder(form -> form.addFormItem(component, label));
        customizer.customize(formItemBuilder);
        return configure(formItemBuilder);
    }
}
