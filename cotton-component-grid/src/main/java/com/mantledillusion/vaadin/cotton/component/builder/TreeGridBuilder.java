package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.treegrid.TreeGrid;


/**
 * {@link ConfigurationBuilder} for {@link TreeGrid}s.
 *
 * @param <T> The value type of the {@link TreeGrid}
 * @param <CF> The configurable filter type of the {@link TreeGrid}
 */
public class TreeGridBuilder<T, CF extends ConfigurableFilter<T>> extends AbstractTreeGridBuilder<TreeGrid<T>, T, CF, TreeGridBuilder<T, CF>> {

    private TreeGridBuilder() {}

    @Override
    protected TreeGrid<T> instantiate() {
        return new TreeGrid<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static TreeGridBuilder<Object, ConfigurableFilter<Object>> create() {
        return new TreeGridBuilder<>();
    }

    /**
     * Factory method for a new instance.
     *
     * @param <T> The value type.
     * @param elementType The class type of the element; might be null.
     * @return A new instance, never null.
     */
    public static <T> TreeGridBuilder<T, ConfigurableFilter<T>> create(Class<T> elementType) {
        return new TreeGridBuilder<>();
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
    public static <T, F extends ConfigurableFilter<T>> TreeGridBuilder<T, F> create(Class<T> elementType,
                                                                                    Class<F> filterType) {
        return new TreeGridBuilder<>();
    }
}
