package com.axialeaa.littledetails.event;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.config.Configs;
import fi.dy.masa.malilib.hotkeys.*;

public class InputHandler implements IKeybindProvider, IKeyboardInputHandler, IMouseInputHandler {

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (IHotkey hotkey : Configs.Generic.HOTKEY_LIST)
            manager.addKeybindToMap(hotkey.getKeybind());
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        manager.addHotkeysForCategory(MainEntrypoint.MOD_NAME, "littledetails.hotkeys.category.generic_hotkeys", Configs.Generic.HOTKEY_LIST);
    }

}