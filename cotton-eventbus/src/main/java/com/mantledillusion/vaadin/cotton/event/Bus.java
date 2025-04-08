package com.mantledillusion.vaadin.cotton.event;

/**
 * An interface for buses offering subscriptions to dispatched events.
 */
public interface Bus {

    /**
     * Registers the given subscriber's @{@link Subscribe} annotated methods to this {@link Bus}.
     *
     * @param subscriber The instance to subscribe; might <b>not</b> be null.
     */
    void subscribe(Object subscriber);

    /**
     * Dispatches the given event to this {@link Bus}es subscribers.
     *
     * @param event The event to dispatch; might <b>not</b> be null.
     */
    void dispatch(Object event);
}