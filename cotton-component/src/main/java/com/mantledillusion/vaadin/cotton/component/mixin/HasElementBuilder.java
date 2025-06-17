package com.mantledillusion.vaadin.cotton.component.mixin;

import com.helger.css.ICSSWriteable;
import com.helger.css.property.ECSSProperty;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

import java.util.Optional;

/**
 * {@link ConfigurationBuilder} for {@link HasElement} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasElement}.
 * @param <B>
 *            The final implementation type of {@link HasElementBuilder}.
 */
public interface HasElementBuilder<C extends HasElement, B extends HasElementBuilder<C, B>> extends
        ConfigurationBuilder<C, B> {

    /**
     * Builder method, configures one or more style class names to be added.
     *
     * @see com.vaadin.flow.dom.Element#setAttribute(String, String)
     * @param title the title attribute value; might <b>not</b> be null.
     * @return this
     */
    default B setNativeTooltip(String title) {
        return configure(hasElement -> hasElement.getElement().setAttribute("title", title));
    }

    /**
     * Builder method, configures whether the {@link HasElement} is visible.
     *
     * @see com.vaadin.flow.dom.Element#setVisible(boolean)
     * @param visible True if the {@link HasElement} should be invisible, false otherwise.
     * @return this
     */
    default B setVisible(boolean visible) {
        return configure(hasElement -> hasElement.getElement().setVisible(visible));
    }

    /**
     * Builder method, configures the {@link HasElement} to be visible if there is a principal in the given roles.
     *
     * @see com.vaadin.flow.dom.Element#setVisible(boolean)
     * @param roles The roles the principal has to be in; might <b>not</b> be null.
     * @return this
     */
    default B setVisibleIfPermitted(String... roles) {
        return configure(hasElement -> hasElement.getElement().setVisible(Authorization.isPermitted(roles)));
    }

    /**
     * Builder method, configures a specific value for a CSS style.
     *
     * @see com.vaadin.flow.dom.Style#set(String, String)
     * @param name The style property name to set; might <b>not</b> be null.
     * @param value The style value to set; might be null.
     * @return this
     */
    default B setStyle(String name, String value) {
        return configure(hasElement -> hasElement.getElement().getStyle().set(name, value));
    }

    /**
     * Builder method, configures a specific value for a CSS style.
     *
     * @see com.vaadin.flow.dom.Style#set(String, String)
     * @param name The style property name to set; might <b>not</b> be null.
     * @param value The style value to set; might be null.
     * @return this
     */
    default B setStyle(String name, ICSSWriteable value) {
        return setStyle(
                name,
                Optional.ofNullable(value).map(ICSSWriteable::getAsCSSString).orElse(null)
        );
    }

    /**
     * Builder method, configures a specific value for a CSS style.
     *
     * @see com.vaadin.flow.dom.Style#set(String, String)
     * @param property The style property to set; might <b>not</b> be null.
     * @param value The style value to set; might be null.
     * @return this
     */
    default B setStyle(ECSSProperty property, String value) {
        return setStyle(
                Optional.ofNullable(property).map(ECSSProperty::getName).orElse(null),
                value
        );
    }

    /**
     * Builder method, configures a specific value for a CSS style.
     *
     * @see com.vaadin.flow.dom.Style#set(String, String)
     * @param property The style property to set; might <b>not</b> be null.
     * @param value The style value to set; might be null.
     * @return this
     */
    default B setStyle(ECSSProperty property, ICSSWriteable value) {
        return setStyle(
                Optional.ofNullable(property).map(ECSSProperty::getName).orElse(null),
                Optional.ofNullable(value).map(ICSSWriteable::getAsCSSString).orElse(null)
        );
    }
}
