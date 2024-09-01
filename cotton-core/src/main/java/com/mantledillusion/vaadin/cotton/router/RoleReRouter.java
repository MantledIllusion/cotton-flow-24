package com.mantledillusion.vaadin.cotton.router;

import com.mantledillusion.vaadin.cotton.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.NavigationState;
import com.vaadin.flow.router.RouteAccessDeniedError;

import java.util.Arrays;

/**
 * Specialized {@link BeforeEnterListener} that handles {@link RouteAccessDeniedError}s by looking at the navigation
 * target's @{@link Reroute} annotation and automatically calling either {@link BeforeEnterEvent#rerouteTo(Class)}
 * or {@link BeforeEnterEvent#forwardTo(Class)}.
 * <p>
 * Without a {@link RoleReRouter} added to the {@link com.vaadin.flow.server.VaadinRequest}'s
 * {@link com.vaadin.flow.component.UI}, all @{@link Reroute} annotations are ignored.
 */
public class RoleReRouter implements BeforeEnterListener {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // DETERMINE WHETHER
        if (event.getNavigationTarget().equals(RouteAccessDeniedError.class)) {
            event.getSource().resolveNavigationTarget(event.getLocation())
                    .map(NavigationState::getNavigationTarget)
                    .filter(target -> target.isAnnotationPresent(Reroute.class))
                    .map(target -> target.getAnnotation(Reroute.class))
                    .ifPresent(reroute -> reroute(event, reroute));
        }
    }

    private void reroute(BeforeEnterEvent event, Reroute reroute) {
        Arrays.stream(reroute.value())
                .filter(Authorization::isPermitted)
                .findFirst()
                .ifPresent(route -> reroute(event, route, reroute.changeUrl()));
    }

    private void reroute(BeforeEnterEvent event, Class<? extends Component> route, boolean changeUrl) {
        if (changeUrl) {
            event.forwardTo(route);
        } else {
            event.rerouteTo(route);
        }
    }
}
