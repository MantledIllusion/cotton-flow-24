package com.mantledillusion.vaadin.cotton.responsive;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import nl.basjes.parse.useragent.yauaa.shaded.org.apache.commons.lang3.tuple.ImmutablePair;
import nl.basjes.parse.useragent.yauaa.shaded.org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class YauaaUserAgentParser implements UserAgentParser {

    private static final String[] HEADER_NAMES = new String[] {
            "User-Agent",
            "Sec-Ch-Ua",
            "Sec-Ch-Ua-Arch",
            "Sec-Ch-Ua-Full-Version-List",
            "Sec-Ch-Ua-Mobile",
            "Sec-Ch-Ua-Model",
            "Sec-Ch-Ua-Platform",
            "Sec-Ch-Ua-Platform-Version",
            "Sec-Ch-Ua-Wow64"
    };

    private static final String FIELD_DEVICE_CLASS = "DeviceClass";

    private final UserAgentAnalyzer analyzer = UserAgentAnalyzer.newBuilder()
            //.withField(FIELD_DEVICE_CLASS)
            .build();

    @Override
    public Optional<UserAgent> parse(HeaderContainer headerContainer) {
        var headers = Arrays.stream(HEADER_NAMES)
                .flatMap(name -> headerContainer.getHeader(name)
                        .map(value -> ImmutablePair.of(name, value))
                        .stream())
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        var parsedUserAgent = this.analyzer.parse(headers);

        if (parsedUserAgent.getConfidence(FIELD_DEVICE_CLASS) < 0) {
            return Optional.empty();
        } else {
            var deviceClass = parsedUserAgent.get(FIELD_DEVICE_CLASS);
            return Optional.of(new YauaaUserAgent(deviceClass));
        }
    }
}
