package com.mantledillusion.vaadin.cotton.data.filter;

import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.shared.Registration;

import java.util.function.Supplier;

/**
 * A configurable filter for elements of a {@link DataProvider}.
 * <p>
 *
 * @param <V> The value type of the {@link DataProvider}.
 */
public interface ConfigurableFilter<V> extends SerializablePredicate<V> {

    /**
     * A listener for changes to the configuration of {@link ConfigurableFilter}s.
     */
    @FunctionalInterface
    interface ConfigurationChangedListener {

        /**
         * Is called when the configuration of a {@link ConfigurableFilter} changes.
         */
        void configurationChanged();
    }

    /**
     * Adds a {@link ConfigurationChangedListener} to this filter which is called every time the filter changes.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return A {@link Registration} that can be used to remove the listener later on, never null
     */
    Registration addConfigurationChangedListener(ConfigurationChangedListener listener);

    /**
     * Tests whether the given element matches the filter.
     * <p>
     * Note that this method is only used by {@link com.vaadin.flow.data.provider.InMemoryDataProvider}s, which is
     * why it is implemented by default, accepting all elements.
     *
     * @param v The element to test; might be null.
     * @return True if the element passes this filter, false otherwise
     */
    @Override
    default boolean test(V v) {
        return true;
    }

    static <CF extends ConfigurableFilter<T>, T> CF supplyFilter(Supplier<CF> filterSupplier) {
        if (filterSupplier == null) {
            throw new IllegalArgumentException("Cannot supply a configurable filter from a null supplier.");
        } else {
            CF filter = filterSupplier.get();
            if (filter == null) {
                throw new IllegalArgumentException("Cannot supply a configurable filter from a supplier that returns null.");
            } else {
                return filter;
            }
        }
    }
}
