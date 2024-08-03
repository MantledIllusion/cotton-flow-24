package com.mantledillusion.vaadin.cotton.presenter;

import com.vaadin.flow.component.Component;

/**
 * A {@link Presenter} of {@link Component}s annotated with @{@link Presented}.
 *
 * @param <V> The type of view presented by this {@link Presenter}.
 */
public interface Presenter<V extends Component>  {

    /**
     * Sets this {@link Presenter}'s view {@link Component}.
     *
     * @param view The {@link Component} this {@link Presenter} presents; might <b>not</b> be null.
     */
    void setView(V view);
}
