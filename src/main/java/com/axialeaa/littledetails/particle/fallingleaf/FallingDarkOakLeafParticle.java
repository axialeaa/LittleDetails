package com.axialeaa.littledetails.particle.fallingleaf;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.AbstractFallingLeafParticle;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

import java.util.List;

@Environment(EnvType.CLIENT)
public class FallingDarkOakLeafParticle extends AbstractFallingLeafParticle {

    protected FallingDarkOakLeafParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, Configs.Colors.FALLING_DARK_OAK_LEAF_PARTICLE_COLORS);
        this.setSpriteForAge(spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FallingDarkOakLeafParticle(world, x, y, z, spriteProvider);
        }
    }

}