package com.axialeaa.littledetails.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.axialeaa.littledetails.MainEntrypoint.MOD_ID;

public class LittleDetailsParticleTypes {

    public static final DefaultParticleType AMETHYST_TWINKLE = FabricParticleTypes.simple();
    public static final DefaultParticleType GLOWSTONE_GLINT = FabricParticleTypes.simple();
    public static final DefaultParticleType SEA_LANTERN_SHIMMER = FabricParticleTypes.simple();
    public static final DefaultParticleType GLOW_BERRY_SHINE = FabricParticleTypes.simple();
    public static final DefaultParticleType RAIN_RIPPLE = FabricParticleTypes.simple();

    public static final DefaultParticleType FIREFLY = FabricParticleTypes.simple();
    public static final DefaultParticleType SANDSTORM = FabricParticleTypes.simple();
    public static final DefaultParticleType RED_SANDSTORM = FabricParticleTypes.simple();

    public static final DefaultParticleType FALLING_OAK_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_SPRUCE_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_BIRCH_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_JUNGLE_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_ACACIA_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_DARK_OAK_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_MANGROVE_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_AZALEA_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_AZALEA_PETAL = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "amethyst_twinkle"), AMETHYST_TWINKLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "glowstone_glint"), GLOWSTONE_GLINT);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sea_lantern_shimmer"), SEA_LANTERN_SHIMMER);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "glow_berry_shine"), GLOW_BERRY_SHINE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "rain_ripple"), RAIN_RIPPLE);

        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "firefly"), FIREFLY);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sandstorm"), SANDSTORM);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "red_sandstorm"), RED_SANDSTORM);

        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_oak_leaf"), FALLING_OAK_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_spruce_leaf"), FALLING_SPRUCE_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_birch_leaf"), FALLING_BIRCH_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_jungle_leaf"), FALLING_JUNGLE_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_acacia_leaf"), FALLING_ACACIA_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_dark_oak_leaf"), FALLING_DARK_OAK_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_mangrove_leaf"), FALLING_MANGROVE_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_azalea_leaf"), FALLING_AZALEA_LEAF);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "falling_azalea_petal"), FALLING_AZALEA_PETAL);
    }

}
