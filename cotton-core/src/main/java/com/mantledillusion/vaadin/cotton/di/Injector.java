package com.mantledillusion.vaadin.cotton.di;

public interface Injector<O> {

    <T extends O> T inject(Class<T> type);
}
