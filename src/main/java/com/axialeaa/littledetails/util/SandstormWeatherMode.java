package com.axialeaa.littledetails.util;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum SandstormWeatherMode implements IConfigOptionListEntry
{
    ALL             ("all",             "littledetails.label.sandstormweathermode.all"),
    RAIN            ("rain",            "littledetails.label.sandstormweathermode.rain"),
    RAIN_THUNDER    ("rain_thunder",    "littledetails.label.sandstormweathermode.rain_thunder"),
    THUNDER         ("thunder",         "littledetails.label.sandstormweathermode.thunder");

    private final String configString;
    private final String unlockName;

    SandstormWeatherMode(String configString, String unlockName) {
        this.configString = configString;
        this.unlockName = unlockName;
    }

    @Override
    public String getStringValue() {
        return this.configString;
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate(this.unlockName);
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {
        int id = this.ordinal();
        if (forward) {
            if (++id >= values().length)
                id = 0;
        }
        else {
            if (--id < 0)
                id = values().length - 1;
        }
        return values()[id % values().length];
    }

    @Override
    public SandstormWeatherMode fromString(String name) {
        return fromStringStatic(name);
    }

    public static SandstormWeatherMode fromStringStatic(String name) {
        for (SandstormWeatherMode alignment : SandstormWeatherMode.values())
            if (alignment.configString.equalsIgnoreCase(name))
                return alignment;

        return SandstormWeatherMode.THUNDER;
    }

}