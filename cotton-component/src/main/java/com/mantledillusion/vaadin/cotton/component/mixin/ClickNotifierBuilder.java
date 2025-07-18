package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.*;

/**
 * {@link ConfigurationBuilder} for {@link ClickNotifier} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link ClickNotifier}.
 * @param <B> The final implementation type of {@link ClickNotifierBuilder}.
 */
public interface ClickNotifierBuilder<C extends CN, CN extends Component & ClickNotifier<CN>, B extends ClickNotifierBuilder<C, CN, B>> extends
        ConfigurationBuilder<C, B> {

    /**
     * Builder method, configures a {@link ComponentEventListener} for the {@link Component}'s {@link ClickEvent}s.
     *
     * @see ClickNotifier#addClickListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    default B addClickListener(ComponentEventListener<ClickEvent<CN>> listener) {
        return configure(clickable -> clickable.addClickListener(listener));
    }

    /**
     * Builder method, configures a {@link ComponentEventListener} for the {@link Component}'s {@link ClickEvent}s.
     *
     * @see ClickNotifier#addDoubleClickListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    default B addDoubleClickListener(ComponentEventListener<ClickEvent<CN>> listener) {
        return configure(clickable -> clickable.addDoubleClickListener(listener));
    }

    /**
     * Builder method, configures a {@link ComponentEventListener} for the {@link Component}'s {@link ClickEvent}s.
     *
     * @see ClickNotifier#addSingleClickListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    default B addSingleClickListener(ComponentEventListener<ClickEvent<CN>> listener) {
        return configure(clickable -> clickable.addSingleClickListener(listener));
    }

    /**
     * Builder method, configures a shortcut for a click on the {@link Component}.
     *
     * @see ClickNotifier#addClickShortcut(Key, KeyModifier...)
     * @param key The {@link Key} to add the shortcut for; might <b>not</b> be null.
     * @param keyModifiers The additional {@link KeyModifier}s; might <b>not</b> be null.
     * @return this
     */
    default B addClickShortcut(Key key, KeyModifier... keyModifiers) {
        return configure(clickable -> clickable.addClickShortcut(key, keyModifiers));
    }
}
