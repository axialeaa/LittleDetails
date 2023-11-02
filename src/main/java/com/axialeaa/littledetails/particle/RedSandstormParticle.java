package com.axialeaa.littledetails.particle;

import com.axialeaa.littledetails.config.Configs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class RedSandstormParticle extends AbstractSandstormParticle implements InterpretColorList {

    protected RedSandstormParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, Configs.Colors.RED_SANDSTORM_PARTICLE_COLORS);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new RedSandstormParticle(clientWorld, d, e, f, Configs.Constants.SANDSTORM_XY_VELOCITY_CONSTANT.getDoubleValue(), -0.1, Configs.Constants.SANDSTORM_XY_VELOCITY_CONSTANT.getDoubleValue(), 1.5F, this.spriteProvider);
        }
    }

}