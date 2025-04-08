package com.mantledillusion.vaadin.cotton.event;

/**
 * An SPI for a factory that creates {@link Bus}es.
 */
public interface BusFactory {

    /**
     * Factory method, creates a new {@link Bus} instance to be used by a single UI.
     *
     * @return The created {@link Bus}, never null.
     */
    Bus create();
}
