package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.login.LoginForm;

/**
 * {@link ConfigurationBuilder} for {@link LoginForm}s.
 */
public class LoginFormBuilder extends AbstractLoginBuilder<LoginForm, LoginFormBuilder> {

    @Override
    protected LoginForm instantiate() {
        return new LoginForm();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static LoginFormBuilder create() {
       return new LoginFormBuilder();
    }
}
