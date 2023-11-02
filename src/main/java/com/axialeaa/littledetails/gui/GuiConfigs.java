package com.axialeaa.littledetails.gui;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.config.Configs;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.util.StringUtils;

import java.util.List;
import java.util.Objects;

public class GuiConfigs extends GuiConfigsBase {
    private static ConfigGuiTab tab = ConfigGuiTab.GENERIC;

    public GuiConfigs()
    {
        super(10, 50, MainEntrypoint.MOD_ID, null, "littledetails.gui.title.configs");
    }

    @Override
    public void initGui() {
        super.initGui();
        this.clearOptions();
        int x = 10;
        int y = 26;
        for (ConfigGuiTab tab : ConfigGuiTab.VALUES)
            x += this.createButton(x, y, tab);
    }

    private int createButton(int x, int y, ConfigGuiTab tab) {
        ButtonGeneric button = new ButtonGeneric(x, y, -1, 20, tab.getDisplayName());
        button.setEnabled(GuiConfigs.tab != tab);
        this.addButton(button, new ButtonListener(tab, this));
        return button.getWidth() + 2;
    }

    @Override
    protected int getConfigWidth() {
        ConfigGuiTab tab = GuiConfigs.tab;
        return switch (tab) {
            case PARTICLES -> 260;
            case COLORS -> 100;
            default -> 200;
        };
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        List<? extends IConfigBase> configs;
        ConfigGuiTab tab = GuiConfigs.tab;
        configs = switch (tab) {
            case GENERIC -> Configs.Generic.OPTIONS;
            case PARTICLES -> Configs.Particles.OPTIONS;
            case PARTICLE_RARITIES -> Configs.ParticleRarities.OPTIONS;
            case BIOMES -> Configs.Biomes.OPTIONS;
            case CONSTANTS -> Configs.Constants.OPTIONS;
            case COLORS -> Configs.Colors.OPTIONS;
            case SOUNDS -> Configs.Sounds.OPTIONS;
            // add more categories here
        };
        return ConfigOptionWrapper.createFor(configs);
    }

    private record ButtonListener(ConfigGuiTab tab, GuiConfigs parent) implements IButtonActionListener {

        @Override
        public void actionPerformedWithButton(ButtonBase button, int mouseButton) {
            GuiConfigs.tab = this.tab;
            this.parent.reCreateListWidget(); // apply the new config width
            Objects.requireNonNull(this.parent.getListWidget()).resetScrollbarPosition();
            this.parent.initGui();
        }

    }

    public enum ConfigGuiTab {

        GENERIC ("littledetails.gui.button.config_gui.generic"),
        PARTICLES ("littledetails.gui.button.config_gui.particles"),
        PARTICLE_RARITIES ("littledetails.gui.button.config_gui.particle_rarities"),
        CONSTANTS ("littledetails.gui.button.config_gui.constants"),
        BIOMES ("littledetails.gui.button.config_gui.biomes"),
        COLORS ("littledetails.gui.button.config_gui.colors"),
        SOUNDS ("littledetails.gui.button.config_gui.sounds");
        // add more categories here

        private final String translationKey;

        public static final ImmutableList<ConfigGuiTab> VALUES = ImmutableList.copyOf(values());

        ConfigGuiTab(String translationKey)
        {
            this.translationKey = translationKey;
        }

        public String getDisplayName()
        {
            return StringUtils.translate(this.translationKey);
        }

    }

}