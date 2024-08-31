package com.mantledillusion.vaadin.cotton.demo.security;

import com.mantledillusion.vaadin.cotton.auth.Authorization;
import com.mantledillusion.vaadin.cotton.router.Navigator;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * https://vaadin.com/docs/latest/security/enabling-security
 */
@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver, AfterNavigationObserver {

    public static final String FORWARD_URL = "forwardUrl";

    private LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        add(new H1("Demo Application"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        Navigator.popQueryParameter(FORWARD_URL)
                // IF THERE IS A SPECIFIC FORWARD URL SET ...
                .ifPresentOrElse(url -> {
                    // ... ADD A LOGIN LISTENER ...
                    login.addLoginListener(evt -> {
                        // ... THAT LOGS THE NEW USER IN ...
                        Authorization.loginPrincipal(evt.getUsername(), evt.getPassword());
                        // ... AND THEN NAVIGATES TO THE FORWARD URL
                        UI.getCurrent().navigate(url);
                    });
                // ... AND IF THERE ISN'T ...
                }, () -> {
                    // USE THE LOGIN FORM ACTION TO LET SPRING DO THE FORWARDING
                    login.setAction("login");
                });
    }
}
