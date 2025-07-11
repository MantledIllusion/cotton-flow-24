package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.grid.Grid;

/**
 * {@link ConfigurationBuilder} for {@link Grid}s.
 *
 * @param <T> The value type of the {@link Grid}
 * @param <CF> The configurable filter type of the {@link Grid}
 */
public class GridBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractGridBuilder<Grid<T>, T, CF, GridBuilder<T, CF>> {

    private GridBuilder() {}

    @Override
    protected Grid<T> instantiate() {
        return new Grid<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static GridBuilder<Object, ConfigurableFilter<Object>> create() {
        return new GridBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> GridBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
        return new GridBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param <F> The filter type.
     * @param elementType The class type of the element; might be null.
     * @param filterType The class type of the filter; might be null.
     * @return A new instance, never null.
     */
    public static <T, F extends ConfigurableFilter<T>> GridBuilder<T, F> create(Class<T> elementType, Class<F> filterType) {
        return new GridBuilder<>();
    }
}
