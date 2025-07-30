package com.mantledillusion.vaadin.cotton.model;

import com.mantledillusion.data.epiphy.Property;
import com.vaadin.flow.data.provider.InMemoryDataProvider;

import java.util.function.Consumer;

/**
 * A specific type of {@link Binding} to a {@link InMemoryDataProvider}.
 *
 * @param <ElementType> The element type of the {@link InMemoryDataProvider}.
 */
public class InMemoryDataProviderBinding<ModelType, ElementType> extends ElementBinding<ModelType, ElementType> {

    private final InMemoryDataProvider<ElementType> dataProvider;

    InMemoryDataProviderBinding(ModelContainer<ModelType> modelContainer,
                                Auditor baseAuditor,
                                Consumer<Binding<ElementType>> registration,
                                Property<ModelType, ElementType> property,
                                InMemoryDataProvider<ElementType> dataProvider,
                                ElementContainer<ElementType> elementContainer) {
        super(modelContainer, baseAuditor, registration, property, elementContainer);
        this.dataProvider = dataProvider;
    }

    /**
     * Returns the bound {@link InMemoryDataProvider}.
     *
     * @return The {@link InMemoryDataProvider}, never null
     */
    public InMemoryDataProvider<ElementType> getDataProvider() {
        return dataProvider;
    }

    @Override
    protected void onValueChanged() {
        this.dataProvider.refreshAll();
    }
}
