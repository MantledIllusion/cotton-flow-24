package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.ClickNotifierBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasTooltipBuilder;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.Locale;

public abstract class AbstractIconBuilder<C extends Icon, B extends AbstractIconBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, Icon, B>,
        HasElementBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasTooltipBuilder<C, B> {

    protected String collection;
    protected String icon;

    /**
     * Builder method, configures the {@link Icon}'s source as an image available over the web.
     *
     * @param vaadinIcon The {@link VaadinIcon} to use; might <b>not</b> be null.
     * @return this
     * @see Icon#Icon(VaadinIcon)
     */
    public B setIcon(VaadinIcon vaadinIcon) {
        if (vaadinIcon == null) {
            throw new IllegalArgumentException("Cannot create icon for a null vaadin icon");
        }
        return setIcon("vaadin", vaadinIcon.name().toLowerCase(Locale.ENGLISH).replace('_', '-'));
    }

    /**
     * Builder method, configures the {@link Icon}'s source as an image available over the web.
     *
     * @param collection The collection of the image; might <b>not</b> be null.
     * @param icon       The name of the image; might <b>not</b> be null.
     * @return this
     * @see Icon#Icon(String, String)
     */
    @SuppressWarnings("unchecked")
    public B setIcon(String collection, String icon) {
        if (collection == null) {
            throw new IllegalArgumentException("Cannot create icon for a null collection");
        } else if (icon == null) {
            throw new IllegalArgumentException("Cannot create icon for a null icon");
        }
        this.collection = collection;
        this.icon = icon;
        return (B) this;
    }
}
