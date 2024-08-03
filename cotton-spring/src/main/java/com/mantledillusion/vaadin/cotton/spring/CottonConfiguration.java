package com.mantledillusion.vaadin.cotton.spring;

import com.mantledillusion.vaadin.cotton.di.CottonPostProcessor;
import com.mantledillusion.vaadin.cotton.spring.scopes.CottonAttachScope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CottonConfiguration {

    @Bean
    public BeanFactoryPostProcessor cottonAttachScope() {
        return new CottonAttachScope();
    }

    @Bean
    public BeanPostProcessor presentablePostProcessor(ApplicationContext context) {
        return new CottonPostProcessor(context);
    }
}
