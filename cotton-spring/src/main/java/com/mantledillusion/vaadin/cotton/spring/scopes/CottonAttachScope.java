package com.mantledillusion.vaadin.cotton.spring.scopes;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.ExtendedClientDetails;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinSessionState;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.SpringVaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.*;
import java.util.function.Supplier;

/**
 * Spring {@link Scope} for navigation bound prototype beans.
 */
public class CottonAttachScope implements BeanFactoryPostProcessor, Scope {

    public static final String COTTON_ATTACH_SCOPE_NAME = "cotton-attach";

    class SessionBeanStore {

        private final VaadinSession session;
        private final Registration sessionDestroyRegistration;
        private final Map<String, UiBeanStore> uiBeanStores = new HashMap<>();

        protected SessionBeanStore(VaadinSession session) {
            this.session = session;
            this.session.setAttribute(SessionBeanStore.class, this);

            if (session instanceof SpringVaadinSession) {
                this.sessionDestroyRegistration = null;
                ((SpringVaadinSession) session)
                        .addDestroyListener(event -> destroy());
            } else {
                this.sessionDestroyRegistration = session.getService()
                        .addSessionDestroyListener(event -> destroy());
            }
        }

        UiBeanStore getUiBeanStore(UI ui) {
            assert session.hasLock();
            ExtendedClientDetails details = ui.getInternals()
                    .getExtendedClientDetails();
            String key = buildUiBeanStoreId(ui);
            if (details == null) {
                ui.getPage().retrieveExtendedClientDetails(
                        det -> updateBeanStoreRegistration(ui, key));
            }
            UiBeanStore beanStore = this.uiBeanStores.get(key);
            if (beanStore == null) {
                beanStore = new UiBeanStore(session, ui,
                        uiInstance -> this.uiBeanStores
                                .remove(buildUiBeanStoreId(uiInstance)));
                this.uiBeanStores.put(key, beanStore);
            }
            return beanStore;
        }

        void updateBeanStoreRegistration(UI ui, String presumedId) {
            assert session.hasLock();
            UiBeanStore beanStore = this.uiBeanStores.remove(presumedId);
            if (beanStore == null) {
                if (this.uiBeanStores.get(buildUiBeanStoreId(ui)) == null) {
                    throw new IllegalStateException(
                            "UI bean store is not found by the initial UI id via the key '"
                                    + presumedId + "' and it's not found by the key '"
                                    + buildUiBeanStoreId(ui)
                                    + "' after relocation.");
                }
            } else {
                this.uiBeanStores.put(buildUiBeanStoreId(ui), beanStore);
            }
        }

        String buildUiBeanStoreId(UI ui) {
            return Optional.ofNullable(ui.getInternals().getExtendedClientDetails())
                    .map(ecd -> "win-" + getWindowName(ui))
                    .orElseGet(() -> "uid-" + ui.getUIId());
        }

        void destroy() {
            this.session.lock();
            try {
                this.session.setAttribute(SessionBeanStore.class, null);
                this.uiBeanStores.values().forEach(UiBeanStore::destroy);
                this.uiBeanStores.clear();
            } finally {
                this.session.unlock();
                if (this.sessionDestroyRegistration != null) {
                    this.sessionDestroyRegistration.remove();
                }
            }
        }
    }

    class UiBeanStore implements BeforeEnterListener, ComponentEventListener<DetachEvent> {

        private static final Logger LOGGER = LoggerFactory.getLogger(UiBeanStore.class.getName());

        private final VaadinSession session;
        private UI ui;
        private Registration uiNavRegistration;
        private Registration uiDetachRegistration;
        private final SerializableConsumer<UI> uiDetachCallback;
        private final List<Runnable> callbacks = new ArrayList<>();

        protected UiBeanStore(VaadinSession session, UI ui, SerializableConsumer<UI> uiDetachCallback) {
            this.session = session;
            this.ui = ui;
            this.uiNavRegistration = ui.addBeforeEnterListener(this);
            this.uiDetachCallback = uiDetachCallback;
            this.uiDetachRegistration = ui.addDetachListener(this);
        }

        Object get(ObjectFactory<?> objectFactory) {
            return executeInSession(objectFactory::getObject);
        }

        void registerDestructionCallback(Runnable callback) {
            executeInSession(() -> this.callbacks.add(callback));
        }

        void destroy() {
            executeInSession(() -> {
                for (Runnable callback: this.callbacks) {
                    try {
                        callback.run();
                    } catch (Throwable throwable) {
                        LOGGER.error("BeanStore destruction callback failed", throwable);
                    }
                }

                this.callbacks.clear();

                return null;
            });
        }

        @Override
        public void beforeEnter(BeforeEnterEvent event) {
            destroy();
        }

        @Override
        public void onComponentEvent(DetachEvent event) {
            assert getVaadinSession().hasLock();
            this.uiNavRegistration.remove();
            this.uiDetachRegistration.remove();
            if (reinstatePreservedUi()) {
                this.uiNavRegistration = this.ui.addBeforeEnterListener(this);
                this.uiDetachRegistration = this.ui.addDetachListener(this);
            } else {
                destroy();
                this.uiDetachCallback.accept(this.ui);
            }
        }

        private boolean reinstatePreservedUi() {
            Optional<UI> ui = findPreservingUI(this.ui);
            if (ui.isPresent()) {
                this.ui = ui.get();
                return true;
            }
            return false;
        }

        private <T> T executeInSession(Supplier<T> supplier) {
            if (this.session.hasLock()) {
                return supplier.get();
            } else {
                this.session.lock();
                try {
                    return supplier.get();
                } finally {
                    this.session.unlock();
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(COTTON_ATTACH_SCOPE_NAME, this);
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return getBeanStore().get(objectFactory);
    }

    @Override
    public Object remove(String name) {
        throw new IllegalArgumentException("Attach scope is unable to destroy individual beans");
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        getBeanStore().registerDestructionCallback(callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return getVaadinSession().getSession().getId() + "-attach-scope";
    }

    private UiBeanStore getBeanStore() {
        final VaadinSession session = getVaadinSession();
        session.lock();
        try {
            return findSessionBeanStore(session)
                    .orElseGet(() -> new SessionBeanStore(session)
                            .getUiBeanStore(getVaadinUI()));
        } finally {
            session.unlock();
        }
    }

    private Optional<UiBeanStore> findSessionBeanStore(VaadinSession session) {
        assert session.hasLock();
        return Optional.ofNullable(session.getAttribute(SessionBeanStore.class))
                .map(store -> store.getUiBeanStore(getVaadinUI()));
    }

    private VaadinSession getVaadinSession() {
        return Optional.ofNullable(VaadinSession.getCurrent())
                .filter(session -> session.getState() == VaadinSessionState.OPEN)
                .orElseThrow(() -> new IllegalStateException("Attach scope unable to detect session for current thread"));
    }

    private UI getVaadinUI() {
        return Optional.ofNullable(UI.getCurrent())
                .orElseThrow(() -> new IllegalStateException("Attach scope unable to detect ui for current thread"));
    }

    private static Optional<UI> findPreservingUI(UI ui) {
        return Optional.ofNullable(getWindowName(ui))
                .flatMap(windowName -> ui.getSession().getUIs().stream()
                        .filter(sessionUi -> sessionUi != ui)
                        .filter(sessionUi -> windowName.equals(getWindowName(sessionUi)))
                        .findFirst());
    }

    private static String getWindowName(UI ui) {
        ExtendedClientDetails details = ui.getInternals()
                .getExtendedClientDetails();
        if (details == null) {
            return null;
        }
        return details.getWindowName();
    }
}
