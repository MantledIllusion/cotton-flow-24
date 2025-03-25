package com.mantledillusion.vaadin.cotton.demo.injected;

import com.mantledillusion.vaadin.cotton.spring.scopes.CottonAttachScope;
import com.vaadin.flow.spring.scopes.VaadinRouteScope;
import com.vaadin.flow.spring.scopes.VaadinSessionScope;
import com.vaadin.flow.spring.scopes.VaadinUIScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class InjectionConfiguration {

    public static final String SESSION = "sessionInjectable";
    public static final String UI = "uiInjectable";
    public static final String ROUTE = "routeInjectable";
    public static final String ATTACH = "attachInjectable";

    @Bean(SESSION)
    @Scope(VaadinSessionScope.VAADIN_SESSION_SCOPE_NAME)
    public Injectable sessionInjectable() {
        return new Injectable();
    }

    @Bean(UI)
    @Scope(VaadinUIScope.VAADIN_UI_SCOPE_NAME)
    public Injectable uiInjectable() {
        return new Injectable();
    }

    @Bean(ROUTE)
    @Scope(VaadinRouteScope.VAADIN_ROUTE_SCOPE_NAME)
    public Injectable routeInjectable() {
        return new Injectable();
    }

    @Bean(ATTACH)
    @Scope(CottonAttachScope.COTTON_ATTACH_SCOPE_NAME)
    public Injectable attachInjectable() {
        return new Injectable();
    }
}
