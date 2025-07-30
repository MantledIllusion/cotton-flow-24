package com.mantledillusion.vaadin.cotton.model;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.data.epiphy.context.Context;
import com.mantledillusion.data.epiphy.context.TraversingMode;

import java.util.function.Consumer;

public class ElementBinding<ModelType, ElementType> extends Binding<ElementType> {

    public interface ElementContainer<ElementType> {

        boolean contains(ElementType element);

        Iterable<ElementType> get();

        void add(ElementType parent, ElementType element, ElementType sibling);

        void remove(ElementType element);
    }

    private final ModelContainer<ModelType> modelContainer;
    private final Property<ModelType, ElementType> property;
    private final ElementContainer<ElementType> elementContainer;

    ElementBinding(ModelContainer<ModelType> modelContainer,
                   Auditor baseAuditor,
                   Consumer<Binding<ElementType>> registration,
                   Property<ModelType, ElementType> property,
                   ElementContainer<ElementType> elementContainer) {
        super(baseAuditor, registration);
        this.modelContainer = modelContainer;
        this.property = property;
        this.elementContainer = elementContainer;
    }

    @Override
    void valueChanged(Context context, UpdateType type, UpdateDirection direction) {
        boolean changed = false;

        if (type != UpdateType.ADD) {
            for (ElementType element : this.elementContainer.get()) {
                if (this.property.contextualize(modelContainer.getModel(), element).isEmpty()) {
                    if (direction.isUpStream) {
                        this.elementContainer.remove(element);
                    }
                    changed = true;
                }
            }
        }

        if (type != UpdateType.REMOVE) {
            ElementType subSibling = null;
            for (Context subContext : this.property.contextualize(modelContainer.getModel(), context, TraversingMode.PARENT)) {
                if (direction.isUpStream) {
                    subSibling = addElement(null, subSibling, subContext);
                }
                changed = true;
            }
        }

        if (changed) {
            onValueChanged();
        }
    }

    private ElementType addElement(ElementType parent, ElementType childSibling, Context childContext) {
        ElementType child = this.property.get(modelContainer.getModel(), childContext);
        if (child != parent) {
            if (!this.elementContainer.contains(child)) {
                this.elementContainer.add(parent, child, childSibling);
            }

            ElementType subSibling = null;
            for (Context subContext : this.property.contextualize(modelContainer.getModel(), childContext, TraversingMode.CHILD)) {
                subSibling = addElement(child, subSibling, subContext);
            }
        }
        return child;
    }

    protected void onValueChanged() {

    }
}
