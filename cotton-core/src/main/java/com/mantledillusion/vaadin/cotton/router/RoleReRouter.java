package com.mantledillusion.vaadin.cotton.router;

import com.mantledillusion.vaadin.cotton.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.TypeVariable;
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

    @SuppressWarnings("rawtypes")
    private static final TypeVariable<Class<HasErrorParameter>> HAS_ERROR_PARAMETER_EXCEPTION_TYPE_VARIABLE = HasErrorParameter.class.getTypeParameters()[0];

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // DETERMINE WHETHER
        if (isHasErrorParameterAccessDenied(event.getNavigationTarget())) {
            event.getSource().resolveNavigationTarget(event.getLocation())
                    .map(NavigationState::getNavigationTarget)
                    .filter(target -> target.isAnnotationPresent(Reroute.class))
                    .map(target -> target.getAnnotation(Reroute.class))
                    .ifPresent(reroute -> reroute(event, reroute));
        }
    }

    private boolean isHasErrorParameterAccessDenied(Class<?> navigationTarget) {
        // CHECK WHETHER THE NAVIGATION TARGET IMPLEMENTS HasErrorParameter ...
        if (TypeUtils.isAssignable(navigationTarget, HasErrorParameter.class)) {
            // ... AND IF THE IMPLEMENTED GENERIC PARAMETERS ...
            var hasErrorParameterTypeArguments = TypeUtils.getTypeArguments(navigationTarget, HasErrorParameter.class);
            // ... FOR HasErrorParameter's EXCEPTION TYPE VARIABLE ...
            var hasErrorParameterExceptionType = hasErrorParameterTypeArguments.get(HAS_ERROR_PARAMETER_EXCEPTION_TYPE_VARIABLE);
            // ... IS ASSIGNABLE TO AccessDeniedException
            return TypeUtils.isAssignable(hasErrorParameterExceptionType, AccessDeniedException.class);
        } else {
            return false;
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
