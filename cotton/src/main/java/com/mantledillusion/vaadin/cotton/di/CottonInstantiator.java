package com.mantledillusion.vaadin.cotton.di;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.di.DefaultInstantiator;
import com.vaadin.flow.server.VaadinService;

public class CottonInstantiator extends DefaultInstantiator {

    public CottonInstantiator(VaadinService service) {
        super(service);
    }

    @Override
    public <C extends Component> C createComponent(Class<C> componentClass) {
        C component = ResponsiveInjector.respond(componentClass)
                .map(super::createComponent)
                .orElseGet(() -> super.createComponent(componentClass));

        PresentableInjector.present(component, this::getOrCreate);

        return component;
    }
}
