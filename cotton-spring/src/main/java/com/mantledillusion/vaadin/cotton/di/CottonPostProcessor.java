package com.mantledillusion.vaadin.cotton.di;

import com.vaadin.flow.component.Component;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class CottonPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    public CottonPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Component) {

            PresentableInjector.present((Component) bean, context.getAutowireCapableBeanFactory()::createBean);
        }
        return bean;
    }
}
