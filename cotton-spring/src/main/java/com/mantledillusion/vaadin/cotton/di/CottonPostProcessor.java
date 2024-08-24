package com.mantledillusion.vaadin.cotton.di;

import com.vaadin.flow.component.Component;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class CottonPostProcessor implements InstantiationAwareBeanPostProcessor {

    private final ApplicationContext context;

    public CottonPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (Component.class.isAssignableFrom(beanClass)) {
            return ResponsiveInjector.respond(beanClass.asSubclass(Component.class))
                    .map(surrogateType -> context.getAutowireCapableBeanFactory().createBean(surrogateType))
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Component) {
            PresentableInjector.present((Component) bean, context.getAutowireCapableBeanFactory()::createBean);
        }
        return bean;
    }
}
