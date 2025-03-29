package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.model.Converter;
import com.mantledillusion.vaadin.cotton.model.ModelContainer;
import com.mantledillusion.vaadin.cotton.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;

/**
 * {@link ConfigurationBuilder} for {@link HasValue} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasValue}.
 * @param <V>
 *            The value type of the {@link HasValue}.
 * @param <B>
 *            The final implementation type of {@link HasValueBuilder}.
 */
public interface HasValueBuilder<C extends HasValue<E, V>, V, E extends HasValue.ValueChangeEvent<V>, B extends HasValueBuilder<C, V, E, B>>
        extends ConfigurationBuilder<C, B> {

    /**
     * Builder method, configures whether the {@link Component} is read-only, so its value cannot be changed.
     *
     * @see HasValue#setReadOnly(boolean)
     * @param readOnly True if the {@link Component} has to be read-only, false otherwise.
     * @return this
     */
    default B setReadOnly(boolean readOnly) {
        return configure(hasValue -> hasValue.setReadOnly(readOnly));
    }

    /**
     * Builder method, configures the {@link Component} to be read only unless there is a principal in the given roles.
     *
     * @see HasValue#setReadOnly(boolean)
     * @param roles The roles the principal has to be in; might <b>not</b> be null.
     * @return this
     */
    default B setReadOnlyUnlessPermitted(String... roles) {
        return configure(hasValue -> hasValue.setReadOnly(!Authorization.isPermitted(roles)));
    }

    /**
     * Builder method, configures whether the required indicator should be visible.
     *
     * @see HasValue#setRequiredIndicatorVisible(boolean)
     * @param requiredIndicatorVisible True if the indicator has to be visible, false otherwise.
     * @return this
     */
    default B setRequiredIndicator(boolean requiredIndicatorVisible) {
        return configure(hasValue -> hasValue.setRequiredIndicatorVisible(requiredIndicatorVisible));
    }

    /**
     * Builder method, configures the initial value of the component after building.
     *
     * @see HasValue#setValue(Object)
     * @param value The initial value; might be null.
     * @return this
     */
    default B setValue(V value) {
        return configure(hasValue -> hasValue.setValue(value));
    }

    /**
     * Uses the given {@link ModelContainer} to bind the {@link HasValue} to the given {@link Property}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param container The {@link ModelContainer} to bind the {@link HasValue} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasValue} to; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setValue(ModelContainer<ModelType> container,
                                   Property<ModelType, V> property) {
        return setValue(container, property, builder -> {});
    }

    /**
     * Uses the given {@link ModelContainer} to bind the {@link HasValue} to the given {@link Property}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param container The {@link ModelContainer} to bind the {@link HasValue} with; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasValue} to; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link BindingBuilder}; might <b>not</b> be null.
     * @return this
     */
    default <ModelType> B setValue(ModelContainer<ModelType> container,
                                   Property<ModelType, V> property,
                                   ConfigurationCustomizer<BindingBuilder<C, V, B>> customizer) {
        if (container == null) {
            throw new IllegalArgumentException("Cannot bind using a null container.");
        } else if (property == null) {
            throw new IllegalArgumentException("Cannot bind using a null property.");
        } else if (customizer == null) {
            throw new IllegalArgumentException("Cannot bind using a null customizer.");
        }
        return configure(new BindingBuilder<>(customizer, c -> container.bindHasValue(c, property)));
    }

    /**
     * Uses the given {@link ModelContainer} to bind the {@link HasValue} to the given {@link Property}.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param <PropertyValueType> The type of the properties' value to convert from/to.
     * @param container The {@link ModelContainer} to bind the {@link HasValue} with; might <b>not</b> be null.
     * @param converter The {@link Converter} to use to convert between the value type of the {@link HasValue} and
     *                  the {@link Property}; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasValue} to; might <b>not</b> be null.
     * @return this
     */
    default <ModelType, PropertyValueType> B setValue(ModelContainer<ModelType> container,
                                                      Converter<V, PropertyValueType> converter,
                                                      Property<ModelType, PropertyValueType> property) {
        return setValue(container, converter, property, builder -> {});
    }

    /**
     * Uses the given {@link ModelContainer} to bind the {@link HasValue} to the given {@link Property} and starts a
     * new {@link BindingBuilder} to configure the binding.
     *
     * @param <ModelType> The type of the model to whose property to bind.
     * @param <PropertyValueType> The type of the properties' value to convert from/to.
     * @param container The {@link ModelContainer} to bind the {@link HasValue} with; might <b>not</b> be null.
     * @param converter The {@link Converter} to use to convert between the value type of the {@link HasValue} and
     *                  the {@link Property}; might <b>not</b> be null.
     * @param property The {@link Property} to bind the {@link HasValue} to; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link BindingBuilder}; might <b>not</b> be null.
     * @return this
     */
    default <ModelType, PropertyValueType> B setValue(ModelContainer<ModelType> container,
                                                      Converter<V, PropertyValueType> converter,
                                                      Property<ModelType, PropertyValueType> property,
                                                      ConfigurationCustomizer<BindingBuilder<C, V, B>> customizer) {
        if (container == null) {
            throw new IllegalArgumentException("Cannot bind using a null container.");
        } else if (converter == null) {
            throw new IllegalArgumentException("Cannot bind using a null converter.");
        } else if (property == null) {
            throw new IllegalArgumentException("Cannot bind using a null property.");
        } else if (customizer == null) {
            throw new IllegalArgumentException("Cannot bind using a null customizer.");
        }
        return configure(new BindingBuilder<>(customizer, c -> container.bindHasValue(c, converter, property)));
    }

    /**
     * Builder method, configures a value change listener.
     *
     * @see HasValue#addValueChangeListener(HasValue.ValueChangeListener)
     * @param listener The listener to add; might <b>not</b> be null
     * @return this
     */
    default B addValueChangeListener(HasValue.ValueChangeListener<? super E> listener) {
        return configure(hasValue -> hasValue.addValueChangeListener(listener));
    }
}
