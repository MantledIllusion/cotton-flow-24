package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.data.filter.ConfigurableFilter;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.dataview.GridDataView;
import com.vaadin.flow.component.grid.dataview.GridLazyDataView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.grid.dnd.GridDragEndEvent;
import com.vaadin.flow.component.grid.dnd.GridDragStartEvent;
import com.vaadin.flow.component.grid.dnd.GridDropEvent;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.event.SortEvent;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.ValueProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
abstract class AbstractGridBuilder<C extends Grid<T>, T, CF extends ConfigurableFilter<T>, B extends AbstractGridBuilder<C, T, CF, B>> extends AbstractComponentBuilder<C, B> implements
        HasDataGeneratorBuilder<C, T, B>,
        HasVoidFilterDataViewBuilder<C, T, CF, GridDataView<T>, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLazyDataViewBuilder<C, T, Void, GridLazyDataView<T>, B>,
        HasListDataViewBuilder<C, T, CF, GridListDataView<T>, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeBuilder<C, B> {

    /**
     * {@link ConfigurationBuilder} for {@link Grid.Column}s.
     */
    public class GridColumnBuilder extends AbstractConfigurationBuilder<Grid.Column<T>, GridColumnBuilder> implements
            Configurer<C>,
            HasElementBuilder<Grid.Column<T>, GridColumnBuilder> {

        private final Function<C, Grid.Column<T>> columnSupplier;

        GridColumnBuilder(Function<C, Grid.Column<T>> columnSupplier) {
            super(AbstractGridBuilder.this);
            this.columnSupplier = columnSupplier;
        }

        @Override
        public void configure(C component) {
            apply(this.columnSupplier.apply(component));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s key.
         *
         * @see Grid.Column#setKey(String)
         * @param key The key; might <b>not</b> be null.
         * @return this
         */
        public GridColumnBuilder setKey(String key) {
            return configure(column -> column.setKey(key));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s alignment.
         *
         * @see Grid.Column#setTextAlign(ColumnTextAlign)
         * @param textAlign The alignment; might be null.
         * @return this
         */
        public GridColumnBuilder setTextAlign(ColumnTextAlign textAlign) {
            return configure(column -> column.setTextAlign(textAlign));
        }

        /**
         * Builder method, configures a {@link HasValue} as header to the column whose value is automatically bound
         * to the {@link Grid}'s filter.
         *
         * @param <H> The {@link HasValue}'s {@link Component} type.
         * @param <TC> The {@link HasValue}'s value type.
         * @param hasValue The @link HasValue}; might <b>not</b> be null.
         * @param filterChangeConsumer A {@link BiConsumer} to adopt a change to the {@link HasValue}'s value into the
         *                             {@link Grid}'s filter; might <b>not</b> be null.
         * @return this
         */
        public <H extends Component & HasValue<?, TC>, TC> GridColumnBuilder setFilter(H hasValue,
                                                                                       BiConsumer<CF, TC> filterChangeConsumer) {
            return setFilter((Supplier<H>) () -> hasValue, filterChangeConsumer);
        }

        /**
         * Builder method, configures a {@link HasValue} as header to the column whose value is automatically bound
         * to the {@link Grid}'s filter.
         *
         * @param <H> The {@link HasValue}'s {@link Component} type.
         * @param <TC> The {@link HasValue}'s value type.
         * @param hasValueSupplier A supplier for {@link HasValue}s; might <b>not</b> be null.
         * @param filterChangeConsumer A {@link BiConsumer} to adopt a change to the {@link HasValue}'s value into the
         *                             {@link Grid}'s filter; might <b>not</b> be null.
         * @return this
         */
        @SuppressWarnings("unchecked")
        public <H extends Component & HasValue<?, TC>, TC> GridColumnBuilder setFilter(Supplier<H> hasValueSupplier,
                                                                                       BiConsumer<CF, TC> filterChangeConsumer) {
            return configure(column -> {
                if (!contains(ConfigurableFilter.class)) {
                    throw new IllegalStateException("Cannot configure a filter column without a data provider " +
                            "with filter being configured.");
                }
                CF filter = (CF) get(ConfigurableFilter.class);
                H hasValue = hasValueSupplier.get();
                hasValue.addValueChangeListener(event -> {
                    filterChangeConsumer.accept(filter, event.getValue());
                });
                column.setHeader(hasValue);
            });
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s header.
         *
         * @see Grid.Column#setHeader(String)
         * @param msgId A header text or message ID to translate; might be null.
         * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
         * @return this
         */
        public GridColumnBuilder setHeader(Object msgId, Object... indexedMessageParameters) {
            return configure(column -> column.setHeader(I18N.getTranslation(msgId, indexedMessageParameters)));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s header.
         *
         * @see Grid.Column#setHeader(Component)
         * @param component The header component; might be null.
         * @return this
         */
        public GridColumnBuilder setHeader(Component component) {
            return configure(column -> column.setHeader(component));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s footer.
         *
         * @see Grid.Column#setFooter(String)
         * @param msgId A header text or message ID to translate; might be null.
         * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
         * @return this
         */
        public GridColumnBuilder setFooter(Object msgId, Object... indexedMessageParameters) {
            return configure(column -> column.setFooter(I18N.getTranslation(msgId, indexedMessageParameters)));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s footer.
         *
         * @see Grid.Column#setFooter(Component)
         * @param component The footer component; might be null.
         * @return this
         */
        public GridColumnBuilder setFooter(Component component) {
            return configure(column -> column.setFooter(component));
        }

        /**
         * Builder method, configures the {@link Grid.Column} to be visible.
         *
         * @see Grid.Column#setVisible(boolean)
         * @param visible True if the column should be visible, false otherwise.
         * @return this
         */
        public GridColumnBuilder setVisible(boolean visible) {
            return configure(column -> column.setVisible(visible));
        }

        /**
         * Builder method, configures the {@link Grid.Column} to be frozen.
         *
         * @see Grid.Column#setFrozen(boolean)
         * @param frozen True if the column should be frozen, false otherwise.
         * @return this
         */
        public GridColumnBuilder setFrozen(boolean frozen) {
            return configure(column -> column.setFrozen(frozen));
        }

        /**
         * Builder method, configures the {@link Grid.Column} to be resizable.
         *
         * @see Grid.Column#setResizable(boolean)
         * @param resizable True if the column should be resizable, false otherwise.
         * @return this
         */
        public GridColumnBuilder setResizable(boolean resizable) {
            return configure(column -> column.setResizable(resizable));
        }

        /**
         * Builder method, configures the {@link Grid.Column} to grow flexibly.
         *
         * @see Grid.Column#setFlexGrow(int)
         * @param flexGrow The flex grow ratio.
         * @return this
         */
        public GridColumnBuilder setFlexGrow(int flexGrow) {
            return configure(column -> column.setFlexGrow(flexGrow));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s width to be set undefined.
         *
         * @see Grid.Column#setWidth(String)
         * @return this
         */
        public GridColumnBuilder setWidthUndefined() {
            return configure(hasSize -> hasSize.setWidth(null));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s width to be set to an exact pixel value.
         *
         * @see Grid.Column#setWidth(String)
         * @param px
         *            The pixel width to set
         * @return this
         */
        public GridColumnBuilder setExactWidth(int px) {
            return configure(hasSize -> hasSize.setWidth(px + CSS_PX));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s width to be set to a percental share of its parent's width.
         *
         * @see Grid.Column#setWidth(String)
         * @param pct
         *            The percental width to set
         * @return this
         */
        public GridColumnBuilder setPercentalWidth(int pct) {
            return configure(hasSize -> hasSize.setWidth(pct + CSS_PCT));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s width to be set to 100% percental share of its parent's width.
         *
         * @see Grid.Column#setWidth(String)
         * @return this
         */
        public GridColumnBuilder setWidthFull() {
            return configure(hasSize -> hasSize.setWidth(100 + CSS_PCT));
        }

        /**
         * Builder method, configures the {@link Grid.Column}'s width to be adapted automatically to the content in the
         * column the moment the {@link Grid} is loaded the first time.
         *
         * @see Grid.Column#setAutoWidth(boolean)
         * @param autoWidth
         *            True if the column should be adapted, false otherwise.
         * @return this
         */
        public GridColumnBuilder setAutoWidth(boolean autoWidth) {
            return configure(hasSize -> hasSize.setAutoWidth(autoWidth));
        }

        /**
         * Builder method, configures a CSS class name generator for the {@link Grid.Column}.
         *
         * @deprecated use {@link #setPartNameGenerator(SerializableFunction)} instead
         * @see Grid.Column#setClassNameGenerator(SerializableFunction)
         * @param classNameGenerator The generator; might <b>not</b> be null.
         * @return this
         */
        @Deprecated
        public GridColumnBuilder setClassNameGenerator(SerializableFunction<T, String> classNameGenerator) {
            return configure(column -> column.setClassNameGenerator(classNameGenerator));
        }

        /**
         * Builder method, configures a CSS part name generator for the {@link Grid.Column}.
         *
         * @see Grid.Column#setPartNameGenerator(SerializableFunction)
         * @param partNameGenerator The generator; might <b>not</b> be null.
         * @return this
         */
        public GridColumnBuilder setPartNameGenerator(SerializableFunction<T, String> partNameGenerator) {
            return configure(column -> column.setPartNameGenerator(partNameGenerator));
        }

        /**
         * Builder method, configures an element comparator for the {@link Grid.Column}.
         *
         * @see Grid.Column#setComparator(Comparator)
         * @param comparator The comparator; might <b>not</b> be null.
         * @return this
         */
        public GridColumnBuilder setComparator(Comparator<T> comparator) {
            return configure(column -> column.setComparator(comparator));
        }

        /**
         * Builder method, configures an element comparator for the {@link Grid.Column}.
         *
         * @see Grid.Column#setComparator(ValueProvider)
         * @param <K> The value type of the column.
         * @param keyExtractor The comparator; might <b>not</b> be null.
         * @return this
         */
        public <K extends Comparable<? super K>> GridColumnBuilder setComparator(ValueProvider<T, K> keyExtractor) {
            return configure(column -> column.setComparator(keyExtractor));
        }

        /**
         * Builder method, configures the {@link Grid.Column} to be sortable.
         *
         * @see Grid.Column#setSortable(boolean)
         * @param sortable True if the column should be sortable, false otherwise.
         * @return this
         */
        public GridColumnBuilder setSortable(boolean sortable) {
            return configure(column -> column.setSortable(sortable));
        }

        /**
         * Builder method, configures the properties to sort the {@link Grid.Column} with.
         *
         * @see Grid.Column#setSortProperty(String[])
         * @param properties The properties to sort the column by; might be null.
         * @return this
         */
        public GridColumnBuilder setSortProperty(String... properties) {
            return configure(column -> column.setSortProperty(properties));
        }

        /**
         * Builder method, configures the provider to sort the {@link Grid.Column} with.
         *
         * @see Grid.Column#setSortOrderProvider(SortOrderProvider)
         * @param provider The provider to sort the column by; might be null.
         * @return this
         */
        public GridColumnBuilder setSortOrderProvider(SortOrderProvider provider) {
            return configure(column -> column.setSortOrderProvider(provider));
        }

        /**
         * Builder method, configures the initial {@link SortDirection} of the column.
         *
         * @param direction The direction to sort the column by; might be null.
         * @return this
         */
        @SuppressWarnings("unchecked")
        public GridColumnBuilder setInitialSortDirection(SortDirection direction) {
            return configure(column -> ((C) column.getGrid()).sort(Collections.singletonList(new GridSortOrder<>(column, direction))));
        }

        /**
         * Builder method, configures an editor component for the {@link Grid.Column}.
         *
         * @see Grid.Column#setEditorComponent(Component)
         * @param component The component; might be null.
         * @return this
         */
        public GridColumnBuilder setEditorComponent(Component component) {
            return configure(column -> column.setEditorComponent(component));
        }

        /**
         * Builder method, configures a callback for generating editor components for the {@link Grid.Column}.
         *
         * @see Grid.Column#setEditorComponent(SerializableFunction)
         * @param componentCallback The callback; might be null.
         * @return this
         */
        public GridColumnBuilder setEditorComponent(SerializableFunction<T, ? extends Component> componentCallback) {
            return configure(column -> column.setEditorComponent(componentCallback));
        }
    }

    AbstractGridBuilder() {}

    /**
     * Builder method, causes the items to be identified by the result of their equals/hashCode method.
     * <p>
     * Vaadin default, should only be used if items remain unchanged or use default equals/hashCode {@link Object}.
     *
     * @return this
     */
    public B identifyItemsByEqualsHashCode() {
        return configure(grid -> grid.getDataCommunicator().getKeyMapper().setIdentifierGetter(Object::hashCode));
    }

    /**
     * Builder method, causes the items to be identified by {@link System#identityHashCode(Object)}
     * <p>
     * Can be useful when items are changed and have their equals/hashCode methods calculate their result with the
     * changing properties, causing the {@link Grid} to loose track of these items when changed.
     *
     * @return this
     */
    public B identifyItemsByIdentity() {
        return configure(grid -> grid.getDataCommunicator().getKeyMapper().setIdentifierGetter(System::identityHashCode));
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(String)
     * @param propertyName The name of the property; might <b>not</b> be null.
     * @return this
     */
    public B addColumn(String propertyName) {
        return addColumn(propertyName, builder -> {});
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(String)
     * @param propertyName The name of the property; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B addColumn(String propertyName, ConfigurationCustomizer<GridColumnBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addColumn(propertyName));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(Renderer)
     * @param renderer The renderer; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public B addColumn(Renderer<T> renderer) {
        return addColumn(renderer, builder -> {});
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(Renderer)
     * @param renderer The renderer; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public B addColumn(Renderer<T> renderer, ConfigurationCustomizer<GridColumnBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addColumn(renderer));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(ValueProvider)
     * @param valueProvider The value provider; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public B addColumn(ValueProvider<T, ?> valueProvider) {
        return addColumn(valueProvider, builder -> {});
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(ValueProvider)
     * @param valueProvider The value provider; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public B addColumn(ValueProvider<T, ?> valueProvider, ConfigurationCustomizer<GridColumnBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addColumn(valueProvider));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(ValueProvider, String...)
     * @param <TC> The value type of the column.
     * @param valueProvider The value provider; might <b>not</b> be null.
     * @param sortingProperties The properties to sort by, might be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC extends Comparable<? super TC>> B addColumn(ValueProvider<T, TC> valueProvider, String... sortingProperties) {
        return addColumn(valueProvider, builder -> {}, sortingProperties);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addColumn(ValueProvider, String...)
     * @param <TC> The value type of the column.
     * @param valueProvider The value provider; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @param sortingProperties The properties to sort by, might be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC extends Comparable<? super TC>> B addColumn(ValueProvider<T, TC> valueProvider, ConfigurationCustomizer<GridColumnBuilder> customizer, String... sortingProperties) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addColumn(valueProvider, sortingProperties));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addComponentColumn(ValueProvider)
     * @param <TC> The value type of the column.
     * @param componentProvider The component provider; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC extends Component> B addComponentColumn(ValueProvider<T, TC> componentProvider) {
        return addComponentColumn(componentProvider, builder -> {});
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addComponentColumn(ValueProvider)
     * @param <TC> The value type of the column.
     * @param componentProvider The component provider; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC extends Component> B addComponentColumn(ValueProvider<T, TC> componentProvider, ConfigurationCustomizer<GridColumnBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addComponentColumn(componentProvider));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addComponentColumn(ValueProvider)
     * @param <TC> The value type of the column.
     * @param <H> The component type of the column.
     * @param componentSupplier The component supplier; might <b>not</b> be null.
     * @param getter The getter to retrieve the column's values with; might <b>not</b> be null.
     * @param setter The setter to write the column's values back with; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC, H extends Component & HasValue<?, TC>> B addComponentColumn(ValueProvider<T, H> componentSupplier, ValueProvider<T, TC> getter, Setter<T, TC> setter) {
        return addComponentColumn(componentSupplier, getter, setter, builder -> {});
    }

    /**
     * Builder method, configures a new column.
     *
     * @see Grid#addComponentColumn(ValueProvider)
     * @param <TC> The value type of the column.
     * @param <H> The component type of the column.
     * @param componentSupplier The component supplier; might <b>not</b> be null.
     * @param getter The getter to retrieve the column's values with; might <b>not</b> be null.
     * @param setter The setter to write the column's values back with; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link GridColumnBuilder}; might <b>not</b> be null.
     * @return A new {@link GridColumnBuilder}, never null
     */
    public <TC, H extends Component & HasValue<?, TC>> B addComponentColumn(ValueProvider<T, H> componentSupplier, ValueProvider<T, TC> getter, Setter<T, TC> setter, ConfigurationCustomizer<GridColumnBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        GridColumnBuilder columnBuilder = new GridColumnBuilder(grid -> grid.addColumn(new ComponentRenderer<>(element -> {
            H component = componentSupplier.apply(element);
            Binder<T> binder = new Binder<>();
            binder.bind(component, getter, setter);
            binder.setBean(element);
            component.addValueChangeListener(e -> binder.readBean(element));
            return component;
        })));
        customizer.customize(columnBuilder);
        return configure(columnBuilder);
    }

    /**
     * Builder method, configures a listener for {@link ItemClickEvent}s.
     *
     * @see Grid#addItemClickListener(ComponentEventListener)
     * @param listener
     *          The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addItemClickListener(ComponentEventListener<ItemClickEvent<T>> listener) {
        return configure(grid -> grid.addItemClickListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link ItemDoubleClickEvent}s.
     *
     * @see Grid#addItemDoubleClickListener(ComponentEventListener)
     * @param listener
     *          The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addItemDoubleClickListener(ComponentEventListener<ItemDoubleClickEvent<T>> listener) {
        return configure(grid -> grid.addItemDoubleClickListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link com.vaadin.flow.data.selection.SelectionEvent}s.
     *
     * @see Grid#addSelectionListener(SelectionListener)
     * @param listener
     *          The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addSelectionListener(SelectionListener<Grid<T>, T> listener) {
        return configure(grid -> grid.addSelectionListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link SortEvent}s.
     *
     * @see Grid#addSortListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addSortListener(ComponentEventListener<SortEvent<Grid<T>, GridSortOrder<T>>> listener) {
        return configure(grid -> grid.addSortListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link ColumnReorderEvent}s.
     *
     * @see Grid#addColumnResizeListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addColumnReorderListener(ComponentEventListener<ColumnReorderEvent<T>> listener) {
        return configure(grid -> grid.addColumnReorderListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link ColumnResizeEvent}s.
     *
     * @see Grid#addColumnResizeListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addColumnResizeListener(ComponentEventListener<ColumnResizeEvent<T>> listener) {
        return configure(grid -> grid.addColumnResizeListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link GridDragStartEvent}s.
     *
     * @see Grid#addDragStartListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addDragStartListener(ComponentEventListener<GridDragStartEvent<T>> listener) {
        return configure(grid -> grid.addDragStartListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link GridDragEndEvent}s.
     *
     * @see Grid#addDragEndListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addDragEndListener(ComponentEventListener<GridDragEndEvent<T>> listener) {
        return configure(grid -> grid.addDragEndListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link GridDropEvent}s.
     *
     * @see Grid#addDropListener(ComponentEventListener)
     * @param listener
     * 			The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addDropListener(ComponentEventListener<GridDropEvent<T>> listener) {
        return configure(grid -> grid.addDropListener(listener));
    }
}
