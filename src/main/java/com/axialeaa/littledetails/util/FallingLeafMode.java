package com.axialeaa.littledetails.util;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum FallingLeafMode implements IConfigOptionListEntry {

    ALL         ("all",         "littledetails.label.fallingleavesmode.all"),
    NATURAL     ("natural",     "littledetails.label.fallingleavesmode.natural"),
    PLACED      ("placed",      "littledetails.label.fallingleavesmode.placed"),
    DECAYING    ("decaying",    "littledetails.label.fallingleavesmode.decaying");

    private final String configString;
    private final String unlockName;

    FallingLeafMode(String configString, String unlockName) {
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
    public FallingLeafMode fromString(String name) {
        return fromStringStatic(name);
    }

    public static FallingLeafMode fromStringStatic(String name) {
        for (FallingLeafMode alignment : FallingLeafMode.values())
            if (alignment.configString.equalsIgnoreCase(name))
                return alignment;

        return FallingLeafMode.DECAYING;
    }

}