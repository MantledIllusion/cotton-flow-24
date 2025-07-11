package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasHierarchicalDataProviderBuilder;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.treegrid.CollapseEvent;
import com.vaadin.flow.component.treegrid.ExpandEvent;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.ValueProvider;

public abstract class AbstractTreeGridBuilder<C extends TreeGrid<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractTreeGridBuilder<C, T, CF, B>> extends AbstractGridBuilder<C, T, CF, B> implements
        HasHierarchicalDataProviderBuilder<C, T, CF, B> {

    /**
     * Builder method, configures a new column.
     *
     * @see TreeGrid#addHierarchyColumn(ValueProvider)
     * @param <VC>          The column's value type.
     * @param valueProvider The value provider; might <b>not</b> be null.
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
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addExpandListener(ComponentEventListener<ExpandEvent<T, TreeGrid<T>>> listener) {
        return configure(treeGrid -> treeGrid.addExpandListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link CollapseEvent}s.
     *
     * @see TreeGrid#addCollapseListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addCollapseListener(ComponentEventListener<CollapseEvent<T, TreeGrid<T>>> listener) {
        return configure(treeGrid -> treeGrid.addCollapseListener(listener));
    }
}
