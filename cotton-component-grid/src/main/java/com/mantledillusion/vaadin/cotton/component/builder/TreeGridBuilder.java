package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasHierarchicalDataProviderBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.treegrid.CollapseEvent;
import com.vaadin.flow.component.treegrid.ExpandEvent;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.ValueProvider;

/**
 * {@link ConfigurationBuilder} for {@link TreeGrid}s.
 *
 * @param <T>
 *           The value type of the {@link TreeGrid}
 * @param <F>
 *           The filter type of the {@link TreeGrid}
 */
public class TreeGridBuilder<T, F extends ConfigurableFilter<T>> extends AbstractGridBuilder<TreeGrid<T>, T, F, TreeGridBuilder<T, F>>
        implements HasHierarchicalDataProviderBuilder<TreeGrid<T>, T, F, TreeGridBuilder<T, F>> {

    private TreeGridBuilder() {}

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

    @Override
    protected TreeGrid<T> instantiate() {
        return new TreeGrid<>();
    }

    /**
     * Builder method, configures a new column.
     *
     * @see TreeGrid#addHierarchyColumn(ValueProvider)
     * @param <VC> The column's value type.
     * @param valueProvider
     *            The value provider; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <VC> GridColumnBuilder configureHierarchyColumn(ValueProvider<T, VC> valueProvider) {
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addHierarchyColumn(valueProvider));
        configure(columnBuilder);
        return columnBuilder;
    }

    /**
     * Builder method, configures a listener for {@link ExpandEvent}s.
     *
     * @see TreeGrid#addExpandListener(ComponentEventListener)
     * @param listener
     *          The listener to add; might <b>not</b> be null.
     * @return this
     */
    public TreeGridBuilder<T, F> addExpandListener(ComponentEventListener<ExpandEvent<T, TreeGrid<T>>> listener) {
        return configure(treeGrid -> treeGrid.addExpandListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link CollapseEvent}s.
     *
     * @see TreeGrid#addCollapseListener(ComponentEventListener)
     * @param listener
     *          The listener to add; might <b>not</b> be null.
     * @return this
     */
    public TreeGridBuilder<T, F> addCollapseListener(ComponentEventListener<CollapseEvent<T, TreeGrid<T>>> listener) {
        return configure(treeGrid -> treeGrid.addCollapseListener(listener));
    }
}
