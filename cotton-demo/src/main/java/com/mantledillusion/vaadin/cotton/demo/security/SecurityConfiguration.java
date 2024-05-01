package com.mantledillusion.vaadin.cotton.demo.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * https://vaadin.com/docs/latest/security/enabling-security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends VaadinWebSecurity {

    public static class Roles {
        public static final String ADMIN = "ADMIN";
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/public/**"))
                .permitAll());

        super.configure(http);

        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsManager demoUserManager() {
        // DEMO PURPOSES ONLY, DO NOT USE IN PRODUCTION!
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}user")
                        .build(),
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles(Roles.ADMIN)
                        .build()
        );
    }
}
