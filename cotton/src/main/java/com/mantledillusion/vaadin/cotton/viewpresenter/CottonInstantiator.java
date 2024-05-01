package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.di.DefaultInstantiator;
import com.vaadin.flow.server.VaadinService;

public class CottonInstantiator extends DefaultInstantiator {

    public CottonInstantiator(VaadinService service) {
        super(service);
    }

    @Override
    public <T extends Component> T createComponent(Class<T> componentClass) {
        T component = super.createComponent(componentClass);
        AbstractPresenter.present(component, this::getOrCreate);
        return component;
    }
}
