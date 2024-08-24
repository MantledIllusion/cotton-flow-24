package com.mantledillusion.vaadin.cotton.responsive;

import nl.basjes.parse.useragent.AgentField;
import nl.basjes.parse.useragent.classify.DeviceClass;

import java.util.Map;
import java.util.Optional;

public class YauaaUserAgent implements UserAgent {

    private static final Map<String, String> SCREEN_CLASSES = Map.ofEntries(
            Map.entry(DeviceClass.DESKTOP.getValue(), ScreenClass.PANEL),
            Map.entry(DeviceClass.ANONYMIZED.getValue(), ScreenClass.UNKNOWN),
            Map.entry(DeviceClass.MOBILE.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.TABLET.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.PHONE.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.WATCH.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.AUGMENTED_REALITY.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.VIRTUAL_REALITY.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.E_READER.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.SET_TOP_BOX.getValue(), ScreenClass.PANEL),
            Map.entry(DeviceClass.TV.getValue(), ScreenClass.PANEL),
            Map.entry(DeviceClass.GAME_CONSOLE.getValue(), ScreenClass.PANEL),
            Map.entry(DeviceClass.HANDHELD_GAME_CONSOLE.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.HOME_APPLIANCE.getValue(), ScreenClass.PANEL),
            Map.entry(DeviceClass.VOICE.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.SMART_DISPLAY.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.CAR.getValue(), ScreenClass.DISPLAY),
            Map.entry(DeviceClass.CLOUD.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.ROBOT.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.ROBOT_MOBILE.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.ROBOT_IMITATOR.getValue(), ScreenClass.VIRTUAL),
            Map.entry(DeviceClass.HACKER.getValue(), ScreenClass.UNKNOWN),
            Map.entry(DeviceClass.UNKNOWN.getValue(), ScreenClass.UNKNOWN),
            Map.entry(DeviceClass.UNCLASSIFIED.getValue(), ScreenClass.UNKNOWN)
    );

    private final AgentField deviceClass;

    public YauaaUserAgent(AgentField deviceClass) {
        this.deviceClass = deviceClass;
    }

    @Override
    public String getScreenClass() {
        return Optional.ofNullable(this.deviceClass.getValue())
                .map(SCREEN_CLASSES::get)
                .orElse(ScreenClass.UNKNOWN);
    }
}
