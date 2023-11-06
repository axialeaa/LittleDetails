package com.axialeaa.littledetails.particle.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class AmethystTwinkleParticle extends SpriteBillboardParticle {

    private final Color4f color = ParticleLogic.getRandomColorFrom(random, Configs.Colors.AMETHYST_TWINKLE_PARTICLE_COLORS);

    protected AmethystTwinkleParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.scale *= 0.75F;
        this.red = color.r;
        this.green = color.g;
        this.blue = color.b;
        this.alpha = color.a;
        this.collidesWithWorld = false;
        this.maxAge = this.random.nextInt(10) + 20;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = color.a * (-this.age / (float)this.maxAge + 1);
    }

    @Override
    public int getBrightness(float tint) {
        return (int)(this.alpha * (160 + random.nextInt(10)));
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new AmethystTwinkleParticle(world, x, y, z, this.spriteProvider);
        }
    }

}
