package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;


public abstract class AbstractLoginBuilder<C extends AbstractLogin, B extends AbstractLoginBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasStyleBuilder<C, B> {

    public class LoginI18NBuilder extends AbstractConfigurationBuilder<LoginI18n, LoginI18NBuilder> implements
            Configurer<C> {

        public class LoginI18NHeaderBuilder extends AbstractConfigurationBuilder<LoginI18n.Header, LoginI18NHeaderBuilder> implements
                Configurer<LoginI18n> {

            LoginI18NHeaderBuilder() {
                super(LoginI18NBuilder.this);
            }

            @Override
            public void configure(LoginI18n component) {
                LoginI18n.Header header = new LoginI18n.Header();
                component.setHeader(header);
                apply(header);
            }

            /**
             * Builder method, sets the title.
             *
             * @see LoginI18n.Header#setTitle(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NHeaderBuilder setTitle(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setTitle(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the description.
             *
             * @see LoginI18n.Header#setDescription(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NHeaderBuilder setDescription(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setDescription(I18N.getTranslation(msgId, indexedMessageParameters)));
            }
        }

        public class LoginI18NFormBuilder extends AbstractConfigurationBuilder<LoginI18n.Form, LoginI18NFormBuilder> implements
                Configurer<LoginI18n> {

            LoginI18NFormBuilder() {
                super(LoginI18NBuilder.this);
            }

            @Override
            public void configure(LoginI18n component) {
                LoginI18n.Form form = new LoginI18n.Form();
                component.setForm(form);
                apply(form);
            }

            /**
             * Builder method, sets the title.
             *
             * @see LoginI18n.Form#setTitle(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NFormBuilder setTitle(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setTitle(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the username label.
             *
             * @see LoginI18n.Form#setUsername(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NFormBuilder setUsername(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setUsername(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the password label.
             *
             * @see LoginI18n.Form#setPassword(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NFormBuilder setPassword(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setPassword(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the forgot password label.
             *
             * @see LoginI18n.Form#setForgotPassword(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NFormBuilder setForgotPassword(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setForgotPassword(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the submit label.
             *
             * @see LoginI18n.Form#setSubmit(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NFormBuilder setSubmit(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setSubmit(I18N.getTranslation(msgId, indexedMessageParameters)));
            }
        }

        public class LoginI18NErrorMessageBuilder extends AbstractConfigurationBuilder<LoginI18n.ErrorMessage, LoginI18NErrorMessageBuilder> implements
                Configurer<LoginI18n> {

            LoginI18NErrorMessageBuilder() {
                super(LoginI18NBuilder.this);
            }

            @Override
            public void configure(LoginI18n component) {
                LoginI18n.ErrorMessage errorMessage = new LoginI18n.ErrorMessage();
                component.setErrorMessage(errorMessage);
                apply(errorMessage);
            }

            /**
             * Builder method, sets the title.
             *
             * @see LoginI18n.ErrorMessage#setTitle(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NErrorMessageBuilder setTitle(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setTitle(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the title.
             *
             * @see LoginI18n.ErrorMessage#setMessage(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NErrorMessageBuilder setMessage(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setMessage(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the username label.
             *
             * @see LoginI18n.ErrorMessage#setUsername(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NErrorMessageBuilder setUsername(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setUsername(I18N.getTranslation(msgId, indexedMessageParameters)));
            }

            /**
             * Builder method, sets the password label.
             *
             * @see LoginI18n.ErrorMessage#setPassword(String)
             * @param msgId The initial text, or a message id to localize; might be null.
             * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
             * @return this
             */
            public LoginI18NErrorMessageBuilder setPassword(String msgId, Object... indexedMessageParameters) {
                return configure(i18n -> i18n.setPassword(I18N.getTranslation(msgId, indexedMessageParameters)));
            }
        }

        LoginI18NBuilder() {
            super(AbstractLoginBuilder.this);
        }

        @Override
        public void configure(C component) {
            LoginI18n i18n = new LoginI18n();
            apply(i18n);
            component.setI18n(i18n);
        }

        /**
         * Builder method, sets the header.
         *
         * @see LoginI18n#setHeader(LoginI18n.Header)
         * @param header The header to set; might be null
         * @return this
         */
        public LoginI18NBuilder setHeader(LoginI18n.Header header) {
            return configure(i18n -> i18n.setHeader(header));
        }

        /**
         * Builder method, sets the header.
         *
         * @see LoginI18n#setHeader(LoginI18n.Header)
         * @param customizer A {@link ConfigurationCustomizer} for the {@link LoginI18NHeaderBuilder}; might <b>not</b> be null.
         * @return this
         */
        public LoginI18NBuilder setHeader(ConfigurationCustomizer<LoginI18NHeaderBuilder> customizer) {
            LoginI18NHeaderBuilder loginI18NHeaderBuilder = new LoginI18NHeaderBuilder();
            customizer.customize(loginI18NHeaderBuilder);
            return configure(loginI18NHeaderBuilder);
        }

        /**
         * Builder method, sets the form.
         *
         * @see LoginI18n#setForm(LoginI18n.Form)
         * @param form The form to set; might be null
         * @return this
         */
        public LoginI18NBuilder setForm(LoginI18n.Form form) {
            return configure(i18n -> i18n.setForm(form));
        }

        /**
         * Builder method, sets the form.
         *
         * @see LoginI18n#setForm(LoginI18n.Form)
         * @param customizer A {@link ConfigurationCustomizer} for the {@link LoginI18NFormBuilder}; might <b>not</b> be null.
         * @return this
         */
        public LoginI18NBuilder setForm(ConfigurationCustomizer<LoginI18NFormBuilder> customizer) {
            LoginI18NFormBuilder loginI18NFormBuilder = new LoginI18NFormBuilder();
            customizer.customize(loginI18NFormBuilder);
            return configure(loginI18NFormBuilder);
        }

        /**
         * Builder method, sets the form.
         *
         * @see LoginI18n#setErrorMessage(LoginI18n.ErrorMessage) (LoginI18n.ErrorMessage)
         * @param form The form to set; might be null
         * @return this
         */
        public LoginI18NBuilder setErrorMessage(LoginI18n.ErrorMessage form) {
            return configure(i18n -> i18n.setErrorMessage(form));
        }

        /**
         * Builder method, sets the form.
         *
         * @see LoginI18n#setErrorMessage(LoginI18n.ErrorMessage)
         * @param customizer A {@link ConfigurationCustomizer} for the {@link LoginI18NErrorMessageBuilder}; might <b>not</b> be null.
         * @return this
         */
        public LoginI18NBuilder setErrorMessage(ConfigurationCustomizer<LoginI18NErrorMessageBuilder> customizer) {
            LoginI18NErrorMessageBuilder loginI18NErrorMessageBuilder = new LoginI18NErrorMessageBuilder();
            customizer.customize(loginI18NErrorMessageBuilder);
            return configure(loginI18NErrorMessageBuilder);
        }

        /**
         * Builder method, sets the additional information.
         *
         * @see LoginI18n#setAdditionalInformation(String)
         * @param msgId The initial text, or a message id to localize; might be null.
         * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
         * @return this
         */
        public LoginI18NBuilder setAdditionalInformation(String msgId, Object... indexedMessageParameters) {
            return configure(i18n -> i18n.setAdditionalInformation(I18N.getTranslation(msgId, indexedMessageParameters)));
        }
    }

    AbstractLoginBuilder() {}

    /**
     * Builder method, sets the path where to send the form-data when a form is submitted.
     * <p>
     * Once action is defined a {@link AbstractLogin.LoginEvent} is not fired anymore.
     *
     * @see AbstractLogin#setAction(String)
     * @param action The URL path suffix; might <b>not</b> be null
     * @return this
     */
    public B setAction(String action) {
        return configure(login -> login.setAction(action));
    }

    /**
     * Builder method, sets whether to show or hide the error message.
     *
     * @see AbstractLogin#setError(boolean)
     * @param error Whether to show the error message.
     * @return this
     */
    public B setError(boolean error) {
        return configure(login -> login.setError(error));
    }

    /**
     * Builder method, sets whether to show or hide the forgot password button.
     * <p>
     * By default, the button is visible.
     *
     * @param forgotPasswordButtonVisible Whether to show the forgot password button.
     * @return this
     */
    public B setForgotPasswordButtonVisible(boolean forgotPasswordButtonVisible) {
        return configure(login -> login.setForgotPasswordButtonVisible(forgotPasswordButtonVisible));
    }

    /**
     * Builder method, sets the internationalized messages to be used by this instance.
     *
     * @see AbstractLogin#setI18n(LoginI18n)
     * @param i18n The {@link LoginI18n} to set; might be null.
     * @return this
     */
    public B setI18n(LoginI18n i18n) {
        return configure(login -> login.setI18n(i18n));
    }

    /**
     * Builder method, configures the internationalized messages to be used by this instance.
     *
     * @see AbstractLogin#setI18n(LoginI18n)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link LoginI18NBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B setI18N(ConfigurationCustomizer<LoginI18NBuilder> customizer) {
        LoginI18NBuilder loginI18NBuilder = new LoginI18NBuilder();
        customizer.customize(loginI18NBuilder);
        return configure(loginI18NBuilder);
    }

    /**
     * Builder method, adds a listener for {@link AbstractLogin.LoginEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addLoginListener(ComponentEventListener<AbstractLogin.LoginEvent> listener) {
        return configure(login -> login.addLoginListener(listener));
    }

    /**
     * Builder method, adds a listener for {@link AbstractLogin.ForgotPasswordEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addForgotPasswordListener(ComponentEventListener<AbstractLogin.ForgotPasswordEvent> listener) {
        return configure(login -> login.addForgotPasswordListener(listener));
    }
}
