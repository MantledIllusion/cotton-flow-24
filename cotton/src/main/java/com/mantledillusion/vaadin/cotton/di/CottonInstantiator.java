package com.mantledillusion.vaadin.cotton.di;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.di.DefaultInstantiator;
import com.vaadin.flow.server.VaadinService;

import java.util.Optional;

public class CottonInstantiator extends DefaultInstantiator {

    public CottonInstantiator(VaadinService service) {
        super(service);
    }

    @Override
    public <C extends Component> C createComponent(Class<C> componentClass) {
        C component = ResponsiveInjector.respond(componentClass)
                .map(super::createComponent)
                .orElseGet(() -> super.createComponent(componentClass));

        Optional<Object> presentable = PresentableInjector.present(component, this::getOrCreate);

        Optional.ofNullable(UI.getCurrent())
                .ifPresent(ui -> {
                    BusInjector.subscribe(ui, component);
                    presentable.ifPresent(p -> BusInjector.subscribe(ui, presentable));
                });

        return component;
    }
}
