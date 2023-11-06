package com.axialeaa.littledetails.particle.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

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
        this.alpha = (-this.age / (float)this.maxAge + 1) * color.a;
    }

    public int getBrightness(float tint) {
        float f = ((float)this.age + tint) / (float)this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightness(tint);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
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
