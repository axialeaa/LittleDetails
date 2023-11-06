package com.axialeaa.littledetails.particle.block.fallingleaf;

import com.axialeaa.littledetails.config.Configs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class FallingAzaleaPetalParticle extends CherryLeavesParticle {

    protected FallingAzaleaPetalParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider);
        this.gravityStrength = (float)Configs.Constants.FALLING_PETAL_GRAVITY_CONSTANT.getDoubleValue();
        this.setSpriteForAge(spriteProvider);
        this.alpha = Configs.Colors.FALLING_AZALEA_PETAL_PARTICLE_ALPHA.getIntegerValue() / 255.0F;
        this.scale = (float)Configs.Constants.FALLING_LEAF_SCALE_CONSTANT.getDoubleValue();
        this.setBoundingBoxSpacing(this.scale, this.scale);
    }

    @Override
    public ParticleTextureSheet getType() {
        return alpha < 1 ? ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT : ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FallingAzaleaPetalParticle(world, x, y, z, spriteProvider);
        }
    }

}