package com.mantledillusion.vaadin.cotton.demo.model;

import com.mantledillusion.data.epiphy.ModelProperty;

public class SimplePojo {

    public static final ModelProperty<SimplePojo, String> FIELD = ModelProperty.fromObject(SimplePojo::getField, SimplePojo::setField);

    private String field;

    public SimplePojo(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
