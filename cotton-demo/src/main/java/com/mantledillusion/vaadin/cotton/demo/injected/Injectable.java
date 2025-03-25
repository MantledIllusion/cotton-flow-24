package com.mantledillusion.vaadin.cotton.demo.injected;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Injectable {

    @PostConstruct
    public void construct() {
        System.out.println(System.identityHashCode(this) + " constructed!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(System.identityHashCode(this) + " destroyed!");
    }
}
