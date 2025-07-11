package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.router.*;

public abstract class AbstractRouterLinkBuilder<C extends RouterLink, B extends AbstractRouterLinkBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasComponentsBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasTextBuilder<C, B>,
        FocusableBuilder<C, RouterLink, B> {

    /**
     * Builder method, configures the location to navigate to when the link is clicked.
     *
     * @param navigationTarget The view to whose location to navigate to; might <b>not</b> be null.
     * @return this
     * @see RouterLink#setRoute(Class)
     */
    public B setRoute(Class<? extends Component> navigationTarget) {
        return configure(routerLink -> routerLink.setRoute(navigationTarget));
    }

    /**
     * Builder method, configures the location to navigate to when the link is clicked.
     *
     * @param <ParameterType>              The type of the URL parameter of the {@link Component} to navigate to.
     * @param <TargetType>              The type of the {@link Component} to navigate to.
     * @param navigationTarget The view to whose location to navigate to; might <b>not</b> be null.
     * @param routeParameter   The parameter of the view to navigate to; might be null.
     * @return this
     * @see RouterLink#setRoute(Class, Object)
     */
    public <ParameterType, TargetType extends Component & HasUrlParameter<ParameterType>> B setRoute(Class<? extends TargetType> navigationTarget, ParameterType routeParameter) {
        return configure(routerLink -> routerLink.setRoute(navigationTarget, routeParameter));
    }

    /**
     * Builder method, configures the location to navigate to when the link is clicked.
     *
     * @param navigationTarget The view to whose location to navigate to; might <b>not</b> be null.
     * @param routeParameters  The route parameters of the view to navigate to; might be null.
     * @return this
     * @see RouterLink#setRoute(Class, Object)
     */
    public B setRoute(Class<? extends Component> navigationTarget, RouteParameters routeParameters) {
        return configure(routerLink -> routerLink.setRoute(navigationTarget, routeParameters));
    }

    /**
     * Builder method, configures the query parameters of location to navigate to when the link is clicked.
     *
     * @param queryParameters The query parameters to set; might be null.
     * @return this
     * @see RouterLink#setQueryParameters(QueryParameters)
     */
    public B setQueryParameters(QueryParameters queryParameters) {
        return configure(routerLink -> routerLink.setQueryParameters(queryParameters));
    }

    /**
     * Builder method, configures the highlight action to perform when the highlight condition is met.
     *
     * @param highlightAction The highlight action to perform; might <b>not</b> be null.
     * @return this
     * @see RouterLink#setHighlightAction(HighlightAction)
     */
    public B setHighlightAction(HighlightAction<RouterLink> highlightAction) {
        return configure(routerLink -> routerLink.setHighlightAction(highlightAction));
    }

    /**
     * Builder method, configures the highlight condition determining if the highlight action should be performed after
     * an {@link AfterNavigationEvent}.
     *
     * @param highlightCondition The highlight condition to evaluate; might <b>not</b> be null.
     * @return this
     * @see RouterLink#setHighlightCondition(HighlightCondition)
     */
    public B setHighlightCondition(HighlightCondition<RouterLink> highlightCondition) {
        return configure(routerLink -> routerLink.setHighlightCondition(highlightCondition));
    }
}
