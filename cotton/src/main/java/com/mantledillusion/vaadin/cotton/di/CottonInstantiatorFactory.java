package com.mantledillusion.vaadin.cotton.di;

import com.vaadin.flow.di.Instantiator;
import com.vaadin.flow.di.InstantiatorFactory;
import com.vaadin.flow.server.VaadinService;

public class CottonInstantiatorFactory implements InstantiatorFactory {

    @Override
    public Instantiator createInstantitor(VaadinService service) {
        return new CottonInstantiator(service);
    }
}
