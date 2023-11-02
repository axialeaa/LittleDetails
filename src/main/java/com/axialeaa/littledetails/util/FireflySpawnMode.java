package com.axialeaa.littledetails.util;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum FireflySpawnMode implements IConfigOptionListEntry {
    ALWAYS              ("always",              "littledetails.label.fireflyspawnmode.always"),
    NIGHT               ("night",               "littledetails.label.fireflyspawnmode.night"),
    ABOVE_WATER         ("above_water",         "littledetails.label.fireflyspawnmode.above_water"),
    NIGHT_ABOVE_WATER   ("night_above_water",   "littledetails.label.fireflyspawnmode.night_above_water");

    private final String configString;
    private final String unlockName;

    FireflySpawnMode(String configString, String unlockName) {
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
    public FireflySpawnMode fromString(String name) {
        return fromStringStatic(name);
    }

    public static FireflySpawnMode fromStringStatic(String name) {
        for (FireflySpawnMode alignment : FireflySpawnMode.values())
            if (alignment.configString.equalsIgnoreCase(name))
                return alignment;

        return FireflySpawnMode.NIGHT_ABOVE_WATER;
    }

}