package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;


/**
 * {@link ConfigurationBuilder} for {@link Icon}s.
 */
@SuppressWarnings("unused")
public class IconBuilder extends AbstractIconBuilder<Icon, IconBuilder> {

    private IconBuilder() {
        setIcon(VaadinIcon.VAADIN_H);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static IconBuilder create() {
        return new IconBuilder();
    }

    @Override
    protected Icon instantiate() {
        return new Icon(this.collection, this.icon);
    }

}
