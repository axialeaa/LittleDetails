package com.axialeaa.littledetails.particle.reaction;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SnoreParticle extends SpriteBillboardParticle {

    protected SnoreParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.ascending = true;
        this.scale *= 0.5F;
        this.velocityY = velocityY;
        Color4f color = ParticleLogic.getRandomColorFrom(random, Configs.Colors.SNORE_PARTICLE_COLORS);
        this.red = color.r;
        this.green = color.g;
        this.blue = color.b;
        this.alpha = color.a;
        this.maxAge = 40;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return alpha < 1 ? ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT : ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Override
    public void tick() {
        super.tick();
        double oscillate = Math.sin(age * 0.1) * 0.006;
        this.velocityX = oscillate;
        this.velocityZ = oscillate;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SnoreParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }

}
