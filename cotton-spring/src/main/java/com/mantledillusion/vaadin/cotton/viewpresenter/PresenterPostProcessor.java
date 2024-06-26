package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class PresenterPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    public PresenterPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Component) {
            AbstractPresenter.present((Component) bean, context.getAutowireCapableBeanFactory()::createBean);
        }
        return bean;
    }
}
