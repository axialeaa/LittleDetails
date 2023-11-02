package com.axialeaa.littledetails.config;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.util.FallingLeafMode;
import com.axialeaa.littledetails.util.FireflySpawnMode;
import com.axialeaa.littledetails.util.SandstormWeatherMode;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.Color4f;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class Configs implements IConfigHandler {

    private static final String CONFIG_FILE_NAME = MainEntrypoint.MOD_ID + ".json";

    public static class Generic {

        public static final ConfigBoolean           FALLING_LEAF_BIOME_TINT                 = new ConfigBoolean         ("fallingLeafBiomeTint",                true,                                               "Falling fallingleaf particles use the foliage colormap instead\nof the colors specified in #Colors.\n§6Note: The alphas from #Colors always apply, and this\n§6setting does nothing for spruce, birch or azalea leaves!");
        public static final ConfigBoolean           WEIGHTED_PRESSURE_PLATE_POWER_COLORS    = new ConfigBoolean         ("weightedPressurePlatePowerColors",    true,                                               "Weighted pressure plate particle colors change based on\ntheir outputted signal strength.\nWhen this setting is disabled, they will always use\nthe default dust particle color!");
        public static final ConfigBoolean           FIREFLY_DAMAGE                          = new ConfigBoolean         ("fireflyDamage",                       true,                                               "Fireflies can be killed by fire, water, cacti, pitcher\nplants and suffocation.");

        public static final ConfigBooleanHotkeyed   FALLING_LEAF_PARTICLES_ENABLED          = new ConfigBooleanHotkeyed ("fallingLeafParticlesEnabled",         true, "Y,L", KeybindSettings.DEFAULT,   "Master toggle for all falling fallingleaf particles in this mod.", "Falling Leaf Particles");
        public static final ConfigBooleanHotkeyed   REDSTONE_PARTICLES_ENABLED              = new ConfigBooleanHotkeyed ("redstoneParticlesEnabled",            true, "Y,R", KeybindSettings.DEFAULT,   "Master toggle for all redstone-related particles in this mod.", "Custom Redstone Particles");

        public static final ConfigHotkey            OPEN_CONFIG_GUI                         = new ConfigHotkey          ("openConfigGui",                       "Y,C",                                          "Open the in-game config GUI.");

        public static final ConfigOptionList        FALLING_LEAF_MODE                       = new ConfigOptionList      ("fallingLeafMode",                     FallingLeafMode.ALL,                                            "Which leaves the falling fallingleaf particles can generate under.");
        public static final ConfigOptionList        SANDSTORM_WEATHER_MODE                  = new ConfigOptionList      ("sandstormWeatherMode",                SandstormWeatherMode.THUNDER,                                   "Which weather conditions sandstorms should abide by when forming.");
        public static final ConfigOptionList        FIREFLY_SPAWN_MODE                      = new ConfigOptionList      ("fireflySpawnMode",                    FireflySpawnMode.NIGHT_ABOVE_WATER,                             "Which world conditions fireflies should abide by when spawning.");

        public static final ConfigDouble            EXPLOSION_FLASH_POWER_THRESHOLD         = new ConfigDouble          ("explosionFlashPowerThreshold",        2.0, 0.0, 32.0,                     "The minimum explosion strength required to produce flash particles.\n§6Note: 1.0 is ghast fireballs and wither skulls, 3.0 is creepers,\n§64.0 is TNT, 5.0 is beds/respawn anchors and 6.0 is\n§6end crystals and charged creepers!");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            FALLING_LEAF_BIOME_TINT,
            WEIGHTED_PRESSURE_PLATE_POWER_COLORS,
            FIREFLY_DAMAGE,

            FALLING_LEAF_PARTICLES_ENABLED,
            REDSTONE_PARTICLES_ENABLED,

            OPEN_CONFIG_GUI,

            FALLING_LEAF_MODE,
            SANDSTORM_WEATHER_MODE,
            FIREFLY_SPAWN_MODE,

            EXPLOSION_FLASH_POWER_THRESHOLD
        );

        public static final List<IHotkey> HOTKEY_LIST = ImmutableList.of(
            OPEN_CONFIG_GUI,
            FALLING_LEAF_PARTICLES_ENABLED,
            REDSTONE_PARTICLES_ENABLED
        );
    }

    public static class Particles {
        // BLOCK PARTICLES
        // basic emissions
        public static final ConfigBoolean   PARTICLE_AMETHYST_TWINKLE       = new ConfigBoolean("particleAmethystTwinkle",      false, "Enables amethyst blocks twinkling.");
        public static final ConfigBoolean   PARTICLE_GLOWSTONE_GLINT        = new ConfigBoolean("particleGlowstoneGlint",       false, "Enables glowstone glinting.");
        public static final ConfigBoolean   PARTICLE_SEA_LANTERN_SHIMMER    = new ConfigBoolean("particleSeaLanternShimmer",    false, "Enables sea lanterns shimmering.");
        public static final ConfigBoolean   PARTICLE_GLOW_BERRY_SHINE       = new ConfigBoolean("particleGlowBerryShine",       false, "Enables glow berries shining.");
        public static final ConfigBoolean   PARTICLE_JACK_O_LANTERN_FLAME   = new ConfigBoolean("particleJackOLanternFlame",    false, "Enables jack o' lanterns emitting flame particles like furnaces.");
        // falling leaves
        public static final ConfigBoolean   PARTICLE_FALLING_OAK_LEAF       = new ConfigBoolean("particleFallingOakLeaf",       false, "Enables falling oak leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_SPRUCE_LEAF    = new ConfigBoolean("particleFallingSpruceLeaf",    false, "Enables falling spruce leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_BIRCH_LEAF     = new ConfigBoolean("particleFallingBirchLeaf",     false, "Enables falling birch leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_JUNGLE_LEAF    = new ConfigBoolean("particleFallingJungleLeaf",    false, "Enables falling jungle leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_ACACIA_LEAF    = new ConfigBoolean("particleFallingAcaciaLeaf",    false, "Enables falling acacia leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_DARK_OAK_LEAF  = new ConfigBoolean("particleFallingDarkOakLeaf",   false, "Enables falling dark oak leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_MANGROVE_LEAF  = new ConfigBoolean("particleFallingMangroveLeaf",  false, "Enables falling mangrove leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_AZALEA_LEAF    = new ConfigBoolean("particleFallingAzaleaLeaf",    false, "Enables falling azalea leaves.");
        public static final ConfigBoolean   PARTICLE_FALLING_AZALEA_PETAL   = new ConfigBoolean("particleFallingAzaleaPetal",   false, "Enables petals falling from flowering azalea leaves.");
        // redstone particles
        public static final ConfigBoolean   PARTICLE_REDSTONE_BLOCK_DUST    = new ConfigBoolean("particleRedstoneBlockDust",    false, "Enables redstone blocks emitting dust particles.");
        public static final ConfigBoolean   PARTICLE_BUTTON_DUST            = new ConfigBoolean("particleButtonDust",           false, "Enables buttons emitting dust particles when pressed.");
        public static final ConfigBoolean   PARTICLE_PRESSURE_PLATE_DUST    = new ConfigBoolean("particlePressurePlateDust",    false, "Enables pressure plates emitting dust particles when\nstepped on.");
        public static final ConfigBoolean   PARTICLE_TRIPWIRE_DUST          = new ConfigBoolean("particleTripwireDust",         false, "Enables tripwire hooks emitting dust particles when\npowered.");

        public static final ConfigBoolean   PARTICLE_WORLD_SPAWN_CENTER     = new ConfigBoolean("particleWorldSpawnCenter",     false, "Enables portal particles generating at the center of\nthe spawnRadius.\n§6Note: When /gamerule spawnRadius is 0, this particle\n§6will indicate the exact spawn location of players!");
        // ENVIRONMENT PARTICLES
        // sandstorms
        public static final ConfigBoolean   PARTICLE_SANDSTORM              = new ConfigBoolean("particleSandstorm",            false, "Enables sandstorms forming in deserts during\nthunderstorms.");
        public static final ConfigBoolean   PARTICLE_RED_SANDSTORM          = new ConfigBoolean("particleRedSandstorm",         false, "Enables red sandstorms forming in badlands during\nthunderstorms.");
        // fireflies
        public static final ConfigBoolean   PARTICLE_FIREFLY                = new ConfigBoolean("particleFirefly",              false, "Enables fireflies spawning in swamps.");
        // REACTION PARTICLES
        public static final ConfigBoolean   PARTICLE_BUBBLE_POP             = new ConfigBoolean("particleBubblePop",            false, "Enables bubbles popping when they reach the surface.");
        public static final ConfigBoolean   PARTICLE_RAIN_RIPPLE            = new ConfigBoolean("particleRainRipple",           false, "Enables rain producing circular ripples when hitting water.");
        public static final ConfigBoolean   PARTICLE_EXPLOSION_FLASH        = new ConfigBoolean("particleExplosionFlash",       false, "Enables large explosions flashing.\n§6Warning: lots of explosions at once may be hard to look at!\n§6Take care if you're photosensitive! <3");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            PARTICLE_AMETHYST_TWINKLE,
            PARTICLE_GLOWSTONE_GLINT,
            PARTICLE_SEA_LANTERN_SHIMMER,
            PARTICLE_GLOW_BERRY_SHINE,
            PARTICLE_JACK_O_LANTERN_FLAME,

            PARTICLE_FALLING_OAK_LEAF,
            PARTICLE_FALLING_SPRUCE_LEAF,
            PARTICLE_FALLING_BIRCH_LEAF,
            PARTICLE_FALLING_JUNGLE_LEAF,
            PARTICLE_FALLING_ACACIA_LEAF,
            PARTICLE_FALLING_DARK_OAK_LEAF,
            PARTICLE_FALLING_MANGROVE_LEAF,
            PARTICLE_FALLING_AZALEA_LEAF,
            PARTICLE_FALLING_AZALEA_PETAL,

            PARTICLE_REDSTONE_BLOCK_DUST,
            PARTICLE_BUTTON_DUST,
            PARTICLE_PRESSURE_PLATE_DUST,
            PARTICLE_TRIPWIRE_DUST,

            PARTICLE_WORLD_SPAWN_CENTER,

            PARTICLE_SANDSTORM,
            PARTICLE_RED_SANDSTORM,

            PARTICLE_FIREFLY,

            PARTICLE_BUBBLE_POP,
            PARTICLE_RAIN_RIPPLE,
            PARTICLE_EXPLOSION_FLASH
        );
    }

    public static class ParticleRarities {

        public static final ConfigInteger   AMETHYST_TWINKLE_PARTICLE_RARITY        = new ConfigInteger("amethystTwinkleParticleRarity",        40, 1, 512,     "The chance per gametick of an amethyst block twinkling in 1/x.\nDefault: 20");
        public static final ConfigInteger   GLOWSTONE_GLINT_PARTICLE_RARITY         = new ConfigInteger("glowstoneGlintParticleRarity",         12, 1, 512,     "The chance per gametick of a glowstone block glinting in 1/x.\nDefault: 12");
        public static final ConfigInteger   SEA_LANTERN_SHIMMER_PARTICLE_RARITY     = new ConfigInteger("seaLanternShimmerParticleRarity",      12, 1, 512,     "The chance per gametick of a sea lantern block shimmering in 1/x.\nDefault: 10");
        public static final ConfigInteger   GLOW_BERRY_SHINE_PARTICLE_RARITY        = new ConfigInteger("glowBerryShineParticleRarity",         8, 1, 512,      "The chance per gametick of a glow berry twinkling in 1/x.\nDefault: 8");
        public static final ConfigInteger   JACK_O_LANTERN_FLAME_PARTICLE_RARITY    = new ConfigInteger("jackOLanternFlameParticleRarity",      5, 1, 512,      "The chance per gametick of a jack o' lantern block\nemitting a flame in 1/x.\nDefault: 8");

        public static final ConfigInteger   FALLING_OAK_LEAF_PARTICLE_RARITY        = new ConfigInteger("fallingOakLeafParticleRarity",         200, 1, 512,    "The chance per gametick of an oak fallingleaf falling from\noak leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_SPRUCE_LEAF_PARTICLE_RARITY     = new ConfigInteger("fallingSpruceLeafParticleRarity",      200, 1, 512,    "The chance per gametick of a spruce fallingleaf falling from\nspruce leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_BIRCH_LEAF_PARTICLE_RARITY      = new ConfigInteger("fallingBirchLeafParticleRarity",       200, 1, 512,    "The chance per gametick of a birch fallingleaf falling from\nbirch leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_JUNGLE_LEAF_PARTICLE_RARITY     = new ConfigInteger("fallingJungleLeafParticleRarity",      200, 1, 512,    "The chance per gametick of a jungle fallingleaf falling from\njungle leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_ACACIA_LEAF_PARTICLE_RARITY     = new ConfigInteger("fallingAcaciaLeafParticleRarity",      200, 1, 512,    "The chance per gametick of an acacia fallingleaf falling from\nacacia leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_DARK_OAK_LEAF_PARTICLE_RARITY   = new ConfigInteger("fallingDarkOakLeafParticleRarity",     200, 1, 512,    "The chance per gametick of a dark oak fallingleaf falling from\ndark oak leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_MANGROVE_LEAF_PARTICLE_RARITY   = new ConfigInteger("fallingMangroveLeafParticleRarity",    200, 1, 512,    "The chance per gametick of a mangrove fallingleaf falling from\nmangrove leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_AZALEA_LEAF_PARTICLE_RARITY     = new ConfigInteger("fallingAzaleaLeafParticleRarity",      200, 1, 512,    "The chance per gametick of an azalea fallingleaf falling from\nazalea or flowering azalea leaves in 1/x.\nDefault: 200");
        public static final ConfigInteger   FALLING_AZALEA_PETAL_PARTICLE_RARITY    = new ConfigInteger("fallingAzaleaPetalParticleRarity",     300, 1, 512,    "The chance per gametick of a petal fallingleaf falling from\nflowering azalea leaves in 1/x.\nDefault: 300");

        public static final ConfigInteger   REDSTONE_BLOCK_DUST_PARTICLE_RARITY     = new ConfigInteger("redstoneBlockDustParticleRarity",      4, 1, 512,      "The chance per gametick of a redstone block emitting\ndust particles in 1/x.\nDefault: 4");
        public static final ConfigInteger   BUTTON_DUST_PARTICLE_RARITY             = new ConfigInteger("buttonDustParticleRarity",             4, 1, 512,      "The chance per gametick of a powered button emitting\ndust particles in 1/x.\nDefault: 4");
        public static final ConfigInteger   PRESSURE_PLATE_DUST_PARTICLE_RARITY     = new ConfigInteger("pressurePlateDustParticleRarity",      4, 1, 512,      "The chance per gametick of a pressed pressure plate\nemitting dust particles in 1/x.\nDefault: 4");
        public static final ConfigInteger   TRIPWIRE_DUST_PARTICLE_RARITY           = new ConfigInteger("tripwireDustParticleRarity",           4, 1, 512,      "The chance per gametick of a powered tripwire hook\nemitting dust particles in 1/x.\nDefault: 4");

        public static final ConfigInteger   WORLD_SPAWN_CENTER_PARTICLE_RARITY      = new ConfigInteger("worldSpawnCenterParticleRarity",       8, 1, 512,      "The chance per gametick of a particle forming at the\ncenter of the world spawn radius in 1/x.\nDefault: 8");

        public static final ConfigInteger   SANDSTORM_PARTICLE_RARITY               = new ConfigInteger("sandstormParticleRarity",              20, 1, 512,     "The chance per gametick of a sandstorm particle appearing\nfor every valid block position in 1/x.\nDefault: 20");
        public static final ConfigInteger   RED_SANDSTORM_PARTICLE_RARITY           = new ConfigInteger("redSandstormParticleRarity",           20, 1, 512,     "The chance per gametick of a red sandstorm particle appearing\nfor every valid block position in 1/x.\nDefault: 20");

        public static final ConfigInteger   FIREFLY_PARTICLE_RARITY                 = new ConfigInteger("fireflyParticleRarity",                500, 1, 2048,   "The chance per gametick of a firefly spawning for every\nvalid block position in 1/x.\nDefault: 500");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            AMETHYST_TWINKLE_PARTICLE_RARITY,
            GLOWSTONE_GLINT_PARTICLE_RARITY,
            SEA_LANTERN_SHIMMER_PARTICLE_RARITY,
            GLOW_BERRY_SHINE_PARTICLE_RARITY,
            JACK_O_LANTERN_FLAME_PARTICLE_RARITY,

            FALLING_OAK_LEAF_PARTICLE_RARITY,
            FALLING_SPRUCE_LEAF_PARTICLE_RARITY,
            FALLING_BIRCH_LEAF_PARTICLE_RARITY,
            FALLING_JUNGLE_LEAF_PARTICLE_RARITY,
            FALLING_ACACIA_LEAF_PARTICLE_RARITY,
            FALLING_DARK_OAK_LEAF_PARTICLE_RARITY,
            FALLING_MANGROVE_LEAF_PARTICLE_RARITY,
            FALLING_AZALEA_LEAF_PARTICLE_RARITY,
            FALLING_AZALEA_PETAL_PARTICLE_RARITY,

            REDSTONE_BLOCK_DUST_PARTICLE_RARITY,
            BUTTON_DUST_PARTICLE_RARITY,
            PRESSURE_PLATE_DUST_PARTICLE_RARITY,
            TRIPWIRE_DUST_PARTICLE_RARITY,

            WORLD_SPAWN_CENTER_PARTICLE_RARITY,

            SANDSTORM_PARTICLE_RARITY,
            RED_SANDSTORM_PARTICLE_RARITY,

            FIREFLY_PARTICLE_RARITY
        );
    }

    public static class Constants {

        public static final ConfigDouble    FALLING_LEAF_GRAVITY_CONSTANT   = new ConfigDouble  ("fallingLeafGravityConstant",  3E-3, 0, 1, "The gravityStrength constant used for the falling\nfallingleaf particles.\nDefault: 0.003");
        public static final ConfigDouble    FALLING_PETAL_GRAVITY_CONSTANT  = new ConfigDouble  ("fallingPetalGravityConstant", 5E-4, 0, 1, "The gravityStrength constant used for the falling\nazalea petal particles.\nDefault: 0.0005");
        public static final ConfigDouble    SANDSTORM_XY_VELOCITY_CONSTANT  = new ConfigDouble  ("sandstormXYVelocityConstant", 0.4, 0, 1,  "The horizontal velocity constant used for the\nsandstorm particles.\nDefault: 0.4");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            FALLING_LEAF_GRAVITY_CONSTANT,
            FALLING_PETAL_GRAVITY_CONSTANT,
            SANDSTORM_XY_VELOCITY_CONSTANT
        );
    }

    public static class Biomes {

        public static final ConfigStringList     SANDSTORM_PARTICLE_BIOMES      = new ConfigStringList  ("sandstormParticleBiomes",     ImmutableList.of("minecraft:desert"),                                                               "The list of biomes IDs in which sandstorm particles\ncan generate during thunderstorms.\n§6Note: the particles can not generate based on empty or\n§6invalid specifications!");
        public static final ConfigStringList     RED_SANDSTORM_PARTICLE_BIOMES  = new ConfigStringList  ("redSandstormParticleBiomes",  ImmutableList.of("minecraft:badlands", "minecraft:eroded_badlands", "minecraft:wooded_badlands"),   "The list of biomes IDs in which red sandstorm particles\ncan generate during thunderstorms.\n§6Note: the particles can not generate based on empty or\n§6invalid specifications!");

        public static final ConfigStringList     FIREFLY_PARTICLE_BIOMES      = new ConfigStringList  ("fireflyParticleBiomes",         ImmutableList.of("minecraft:swamp", "minecraft:mangrove_swamp"),                                    "The list of biomes IDs in which fireflies can generate.\n§6Note: the particles can not generate based on empty or\n§6invalid specifications!");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            SANDSTORM_PARTICLE_BIOMES,
            RED_SANDSTORM_PARTICLE_BIOMES,

            FIREFLY_PARTICLE_BIOMES
        );
    }

    public static class Colors {

        public static final ConfigColorList     AMETHYST_TWINKLE_PARTICLE_COLORS        = new ConfigColorList   ("amethystTwinkleParticleColors",       ImmutableList.of(Color4f.fromColor(0xFFFECBE6)),    "The list of colors from which the amethyst twinkle\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     GLOWSTONE_GLINT_PARTICLE_COLORS         = new ConfigColorList   ("glowstoneGlintParticleColors",        ImmutableList.of(Color4f.fromColor(0xFFFFF0DA)),    "The list of colors from which the glowstone glint\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     SEA_LANTERN_SHIMMER_PARTICLE_COLORS     = new ConfigColorList   ("seaLanternShimmerParticleColors",     ImmutableList.of(Color4f.fromColor(0xFFD0E3DB)),    "The list of colors from which the sea lantern shimmer\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     GLOW_BERRY_SHINE_PARTICLE_COLORS        = new ConfigColorList   ("glowBerryShineParticleColors",        ImmutableList.of(Color4f.fromColor(0xFFF7E26B)),    "The list of colors from which the glow berry shine\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");

        public static final ConfigColorList     FALLING_OAK_LEAF_PARTICLE_COLORS        = new ConfigColorList   ("fallingOakLeafParticleColors",        ImmutableList.of(Color4f.fromColor(0xFF59AE30)),    "The list of colors from which the falling oak fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_SPRUCE_LEAF_PARTICLE_COLORS     = new ConfigColorList   ("fallingSpruceLeafParticleColors",     ImmutableList.of(Color4f.fromColor(0xFF619961)),    "The list of colors from which the falling spruce fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_BIRCH_LEAF_PARTICLE_COLORS      = new ConfigColorList   ("fallingBirchLeafParticleColors",      ImmutableList.of(Color4f.fromColor(0xFF80A755)),    "The list of colors from which the falling birch fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_JUNGLE_LEAF_PARTICLE_COLORS     = new ConfigColorList   ("fallingJungleLeafParticleColors",     ImmutableList.of(Color4f.fromColor(0xFF30BB0B)),    "The list of colors from which the falling jungle fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_ACACIA_LEAF_PARTICLE_COLORS     = new ConfigColorList   ("fallingAcaciaLeafParticleColors",     ImmutableList.of(Color4f.fromColor(0xFFAEA42A)),    "The list of colors from which the falling acacia fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_DARK_OAK_LEAF_PARTICLE_COLORS   = new ConfigColorList   ("fallingDarkOakLeafParticleColors",    ImmutableList.of(Color4f.fromColor(0xFF59AE30)),    "The list of colors from which the falling spruce fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     FALLING_MANGROVE_LEAF_PARTICLE_COLORS   = new ConfigColorList   ("fallingMangroveLeafParticleColors",   ImmutableList.of(Color4f.fromColor(0xFF8DB127)),    "The list of colors from which the falling mangrove fallingleaf\nparticles will pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");

        public static final ConfigColorList     SANDSTORM_PARTICLE_COLORS               = new ConfigColorList   ("sandstormParticleColors",             ImmutableList.of(Color4f.fromColor(0xFFE3DBB0)),    "The list of colors from which the sandstorm particles will\npick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");
        public static final ConfigColorList     RED_SANDSTORM_PARTICLE_COLORS           = new ConfigColorList   ("redSandstormParticleColors",          ImmutableList.of(Color4f.fromColor(0xFFCB6E24)),    "The list of colors from which the red sandstorm particles\nwill pick randomly when created.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");

        public static final ConfigColorList     FIREFLY_PARTICLE_COLORS                 = new ConfigColorList   ("fireflyParticleColors",               ImmutableList.of(Color4f.fromColor(0xFFD0FF00)),    "The list of colors from which fireflies will pick\nrandomly when spawned.\n§6Note: If the list is empty, the particle will be\n§6completely invisible (#00000000)!");

        public static final ConfigInteger       FALLING_AZALEA_LEAF_PARTICLE_ALPHA      = new ConfigInteger     ("fallingAzaleaLeafParticleAlpha",      255, 0, 255,             "The transparency of the falling azalea fallingleaf particles.\n§6Note: This doesn't have a color option because azalea leaves\n§6don't tint!");
        public static final ConfigInteger       FALLING_AZALEA_PETAL_PARTICLE_ALPHA     = new ConfigInteger     ("fallingAzaleaPetalParticleAlpha",     255, 0, 255,             "The transparency of the falling azalea petal particles.\n§6Note: This doesn't have a color option because azalea leaves\n§6don't tint!");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            AMETHYST_TWINKLE_PARTICLE_COLORS,
            GLOWSTONE_GLINT_PARTICLE_COLORS,
            SEA_LANTERN_SHIMMER_PARTICLE_COLORS,
            GLOW_BERRY_SHINE_PARTICLE_COLORS,

            FALLING_OAK_LEAF_PARTICLE_COLORS,
            FALLING_SPRUCE_LEAF_PARTICLE_COLORS,
            FALLING_BIRCH_LEAF_PARTICLE_COLORS,
            FALLING_JUNGLE_LEAF_PARTICLE_COLORS,
            FALLING_ACACIA_LEAF_PARTICLE_COLORS,
            FALLING_DARK_OAK_LEAF_PARTICLE_COLORS,
            FALLING_MANGROVE_LEAF_PARTICLE_COLORS,

            SANDSTORM_PARTICLE_COLORS,
            RED_SANDSTORM_PARTICLE_COLORS,

            FIREFLY_PARTICLE_COLORS,

            FALLING_AZALEA_LEAF_PARTICLE_ALPHA,
            FALLING_AZALEA_PETAL_PARTICLE_ALPHA
        );
    }

    public static class Sounds {

        public static final ConfigDouble BUBBLE_POP_VOLUME          = new ConfigDouble("bubblePopVolume",           0.3,    0.0, 2.0,  "The volume at which bubbles pop. 0 disables this entirely.\n§6Note: You need to enable particleBubblePop for this to work!\nDefault: 0.3");
        public static final ConfigDouble PITCHER_PLANT_CHOMP_VOLUME = new ConfigDouble("pitcherPlantChompVolume",   0.1,    0.0, 2.0,  "The volume at which pitcher plants eat fireflies.\n0 disables this entirely.\n§6Note: You need to enable fireflyDamage for this to work!\nDefault: 0.1");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            BUBBLE_POP_VOLUME,
            PITCHER_PLANT_CHOMP_VOLUME
        );
    }


    public static void loadFromFile() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);
        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "Generic", Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "Particles", Particles.OPTIONS);
                ConfigUtils.readConfigBase(root, "Particle Rarities", ParticleRarities.OPTIONS);
                ConfigUtils.readConfigBase(root, "Constants", Constants.OPTIONS);
                ConfigUtils.readConfigBase(root, "Biomes", Biomes.OPTIONS);
                ConfigUtils.readConfigBase(root, "Colors", Colors.OPTIONS);
                ConfigUtils.readConfigBase(root, "Sounds", Sounds.OPTIONS);
                // add more categories here
            }
        }
    }

    public static void saveToFile() {
        File dir = FileUtils.getConfigDirectory();
        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();
            ConfigUtils.writeConfigBase(root, "Generic", Generic.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Particles", Particles.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Particle Rarities", ParticleRarities.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Constants", Constants.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Biomes", Biomes.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Colors", Colors.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Sounds", Sounds.OPTIONS);
            // add more categories here
            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override public void load() { loadFromFile(); }
    @Override public void save() { saveToFile(); }
}