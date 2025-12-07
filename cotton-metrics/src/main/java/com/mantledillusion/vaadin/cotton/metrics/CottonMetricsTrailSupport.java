package com.mantledillusion.vaadin.cotton.metrics;

import com.mantledillusion.metrics.trail.MetricsTrail;
import com.mantledillusion.metrics.trail.MetricsTrailSupport;
import com.vaadin.flow.server.*;
import org.apache.commons.lang3.BooleanUtils;

public class CottonMetricsTrailSupport implements VaadinServiceInitListener, SessionDestroyListener, VaadinRequestInterceptor {

    private static final String METRICS_TRAIL = "_metricsTrail";
    private static final String FOREIGN_TRAIL = "_isForeignMetricsTrail";

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addVaadinRequestInterceptor(this);
        event.getSource().addSessionDestroyListener(this);
    }

    @Override
    public void requestStart(VaadinRequest request, VaadinResponse response) {
        WrappedSession wrappedSession = request.getWrappedSession();

        if (wrappedSession.getAttribute(METRICS_TRAIL) == null) {
            if (MetricsTrailSupport.has()) {
                wrappedSession.setAttribute(FOREIGN_TRAIL, Boolean.TRUE);
            } else {
                MetricsTrailSupport.begin();
                wrappedSession.setAttribute(FOREIGN_TRAIL, Boolean.FALSE);
            }
            wrappedSession.setAttribute(METRICS_TRAIL, MetricsTrailSupport.get());
        } else if (!MetricsTrailSupport.has()) {
            MetricsTrailSupport.bind((MetricsTrail) wrappedSession.getAttribute(METRICS_TRAIL));
        }
    }

    @Override
    public void handleException(VaadinRequest request, VaadinResponse response, VaadinSession vaadinSession, Exception t) {
        // NOOP
    }

    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
        VaadinSession session = event.getSession();
        session.lock();
        if (BooleanUtils.isFalse((Boolean) session.getAttribute(FOREIGN_TRAIL))) {
            MetricsTrailSupport.end(session.getAttribute(MetricsTrail.class));
        }
        session.unlock();
    }

    @Override
    public void requestEnd(VaadinRequest request, VaadinResponse response, VaadinSession session) {
        if (MetricsTrailSupport.has()) {
            WrappedSession wrappedSession = request.getWrappedSession();
            if (BooleanUtils.isFalse((Boolean) wrappedSession.getAttribute(FOREIGN_TRAIL))) {
                MetricsTrailSupport.release();
            }
        }
    }
}
