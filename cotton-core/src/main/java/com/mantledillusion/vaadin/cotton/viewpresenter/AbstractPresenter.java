package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;

/**
 * A basic implementation of a {@link Presenter} that holds its presented component.
 *
 * @param <V> The type of view presented by this {@link Presenter}.
 */
public abstract class AbstractPresenter<V extends Component> implements Presenter<V> {

    private V view;

    /**
     * Returns this presenter's view.
     *
     * @return The view, never null
     */
    protected V getView() {
        if (this.view == null) {
            throw new IllegalStateException("The presenter has not been initialized with its view yet.");
        }

        return this.view;
    }

    @Override
    public void setView(V view) {
        this.view = view;
    }
}
