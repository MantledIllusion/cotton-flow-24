package com.mantledillusion.vaadin.cotton.demo.model;

import com.mantledillusion.data.epiphy.ModelProperty;
import com.mantledillusion.data.epiphy.ModelPropertyList;

import java.util.List;

public class ComplexPojo {

    public static final ModelProperty<ComplexPojo, SimplePojo> OBJECT = ModelProperty.fromObject(ComplexPojo::getObject);
    public static final ModelPropertyList<ComplexPojo, SimplePojo> ELEMENTS = ModelPropertyList.fromObject(ComplexPojo::getElements);
    public static final ModelProperty<ComplexPojo, SimplePojo> ELEMENT = ELEMENTS.append(ModelProperty.fromList());

    private final SimplePojo object;
    private final List<SimplePojo> elements;

    public ComplexPojo(SimplePojo object, List<SimplePojo> elements) {
        this.object = object;
        this.elements = elements;
    }

    public SimplePojo getObject() {
        return object;
    }

    public List<SimplePojo> getElements() {
        return elements;
    }
}
