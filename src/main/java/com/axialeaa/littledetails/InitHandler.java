package com.axialeaa.littledetails;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.event.InputHandler;
import com.axialeaa.littledetails.event.KeybindCallbacks;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;

public class InitHandler implements IInitializationHandler {

    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance().registerConfigHandler(MainEntrypoint.MOD_ID, new Configs());

        InputHandler handler = new InputHandler();
        InputEventHandler.getKeybindManager().registerKeybindProvider(handler);
        InputEventHandler.getInputManager().registerKeyboardInputHandler(handler);
        InputEventHandler.getInputManager().registerMouseInputHandler(handler);

        TickHandler.getInstance().registerClientTickHandler(KeybindCallbacks.getInstance());

        KeybindCallbacks.getInstance().setCallbacks();
    }

}