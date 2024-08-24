package com.mantledillusion.vaadin.cotton.di;

import com.mantledillusion.vaadin.cotton.responsive.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.shared.BrowserDetails;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

class ResponsiveInjector {

    private static class VaadinUserAgentParser implements UserAgentParser {

        private static final String HEADER_NAME_USER_AGENT = "User-Agent";

        private static class VaadinUserAgent implements UserAgent {

            private final BrowserDetails browserDetails;

            private VaadinUserAgent(BrowserDetails browserDetails) {
                this.browserDetails = browserDetails;
            }

            @Override
            public String getScreenClass() {
                if (this.browserDetails.isWindows() || this.browserDetails.isMacOSX() || this.browserDetails.isLinux()) {
                    return ScreenClass.PANEL;
                } else if (this.browserDetails.isAndroid() || this.browserDetails.isChromeOS()
                        || this.browserDetails.isIPhone() || this.browserDetails.isWindowsPhone()) {
                    return ScreenClass.DISPLAY;
                } else {
                    return ScreenClass.UNKNOWN;
                }
            }
        }

        @Override
        public Optional<UserAgent> parse(HeaderContainer headerContainer) {
            return headerContainer.getHeader(HEADER_NAME_USER_AGENT)
                    .map(BrowserDetails::new)
                    .map(VaadinUserAgent::new);
        }
    }

    private static UserAgentParser USER_AGENT_PARSER;

    @SuppressWarnings("unchecked")
    static <C extends Component> Optional<Class<C>> respond(Class<C> componentType) {
        if (componentType.isAnnotationPresent(Responsive.class)) {
            return Optional.ofNullable(VaadinRequest.getCurrent())
                    .flatMap(request -> getParser().parse(name -> Optional.ofNullable(request.getHeader(name))))
                    .filter(userAgent -> !matches(componentType, userAgent))
                    .flatMap(userAgent -> streamSurrogates(componentType)
                            .filter(surrogateType -> matches(surrogateType, userAgent))
                            .findFirst()
                            .map(surrogateType -> (Class<C>) surrogateType));
        } else {
            return Optional.empty();
        }
    }

    private static synchronized UserAgentParser getParser() {
        if (USER_AGENT_PARSER == null) {
            var serviceLoader = ServiceLoader.load(UserAgentParser.class);

            USER_AGENT_PARSER = serviceLoader.stream()
                    .reduce((p1, p2) -> {
                        var parsers = serviceLoader.stream()
                                .map(parser -> parser.getClass().getSimpleName())
                                .toList();
                        throw new IllegalStateException(String.format("Found %s implementations of %s ([%s]); only one should be available.",
                                parsers.size(), UserAgentParser.class.getSimpleName(), StringUtils.join(parsers, ", ")));
                    })
                    .map(ServiceLoader.Provider::get)
                    .orElseGet(VaadinUserAgentParser::new);
        }

        return USER_AGENT_PARSER;
    }

    private static boolean matches(Class<? extends Component> componentType, UserAgent userAgent) {
        return Optional.ofNullable(componentType.getAnnotation(Responds.class))
                    .map(Responds::value)
                    .or(() -> Optional.ofNullable(componentType.getAnnotation(Respond.class))
                            .map(ArrayUtils::toArray))
                    .stream().flatMap(Arrays::stream)
                    .allMatch(respond -> matches(userAgent, respond.to(), respond.with()))
                && Optional.ofNullable(componentType.getAnnotation(Refrains.class))
                    .map(Refrains::value)
                    .or(() -> Optional.ofNullable(componentType.getAnnotation(Refrain.class))
                            .map(ArrayUtils::toArray))
                    .stream().flatMap(Arrays::stream)
                    .noneMatch(refrain -> matches(userAgent, refrain.from(), refrain.with()));
    }

    private static boolean matches(UserAgent userAgent, Class<? extends ResponsiveClass> responsiveClass, String[] with) {
        if (ScreenClass.class.isAssignableFrom(responsiveClass)) {
            var screenClass = userAgent.getScreenClass();
            return Arrays.stream(with).anyMatch(screenClass::matches);
        } else {
            throw new IllegalArgumentException(String.format("The %s implementation %s is unknown",
                    ResponsiveClass.class.getSimpleName(), responsiveClass.getSimpleName()));
        }
    }

    private static <C extends Component> Stream<Class<? extends Component>> streamSurrogates(Class<C> componentType) {
        var responsive = componentType.getAnnotation(Responsive.class);
        return Arrays.stream(responsive.value());
    }
}
