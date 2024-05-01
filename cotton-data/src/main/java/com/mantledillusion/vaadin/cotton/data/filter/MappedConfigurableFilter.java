package com.mantledillusion.vaadin.cotton.data.filter;

import com.vaadin.flow.data.provider.DataProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * A basic {@link ConfigurableFilter} that allows saving and retrieving a filter configuration using a {@link Map}.
 *
 * @param <V> The value type of the {@link DataProvider}.
 */
public class MappedConfigurableFilter<V> extends BasicConfigurableFilter<V> {

    private final Map<String, Object> filter = new HashMap<>();

    /**
     * Returns if the value of the given key is set.
     *
     * @param key The key; might be null.
     * @return True if the value is set, false otherwise
     */
    public boolean contains(String key) {
        return this.filter.containsKey(key);
    }

    /**
     * Returns the value to the given key.
     *
     * @param <KV> The key's value type.
     * @param key The key whose value to retrieve; might be null.
     * @return The value, might be null if the key is not set or set to null
     */
    @SuppressWarnings("unchecked")
    public <KV> KV get(String key) {
        return (KV) this.filter.get(key);
    }

    /**
     * Sets the value to the given key.
     *
     * @param <KV>   The key's value type.
     * @param key   The key whose value to set; might be null.
     * @param value The value to set; might be null.
     */
    public <KV> void set(String key, KV value) {
        this.filter.put(key, value);
        notifyConfigurationChanged();
    }
}
