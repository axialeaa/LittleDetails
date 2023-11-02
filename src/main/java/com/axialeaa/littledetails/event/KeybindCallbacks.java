package com.axialeaa.littledetails.event;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.gui.GuiConfigs;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.interfaces.IClientTickHandler;
import net.minecraft.client.MinecraftClient;

public class KeybindCallbacks implements IHotkeyCallback, IClientTickHandler {

    private static final KeybindCallbacks INSTANCE = new KeybindCallbacks();

    public static KeybindCallbacks getInstance()
    {
        return INSTANCE;
    }

    private KeybindCallbacks() {}

    @Override
    public boolean onKeyAction(KeyAction action, IKeybind key)
    {
        return this.onKeyActionImpl(key);
    }

    public void setCallbacks()
    {
        Configs.Generic.OPEN_CONFIG_GUI.getKeybind().setCallback(this);
    }

    private boolean onKeyActionImpl(IKeybind key) {
        if (key == Configs.Generic.OPEN_CONFIG_GUI.getKeybind()) {
            GuiBase.openGui(new GuiConfigs());
            return true;
        }
        return false;
    }

    @Override
    public void onClientTick(MinecraftClient mc) {}

}