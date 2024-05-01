package com.mantledillusion.vaadin.cotton.data.filter;

import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic {@link ConfigurableFilter} that just implements {@link ConfigurableFilter}'s listener logic.
 *
 * @param <V> The value type of the {@link DataProvider}.
 */
public abstract class BasicConfigurableFilter<V> implements ConfigurableFilter<V> {

    private final List<ConfigurationChangedListener> listeners = new ArrayList<>();

    @Override
    public Registration addConfigurationChangedListener(ConfigurationChangedListener listener) {
        this.listeners.add(listener);
        return () -> this.listeners.remove(listener);
    }

    /**
     * Notify all {@link ConfigurationChangedListener}s that the configuration has changed.
     */
    protected void notifyConfigurationChanged() {
        this.listeners.forEach(ConfigurationChangedListener::configurationChanged);
    }
}
