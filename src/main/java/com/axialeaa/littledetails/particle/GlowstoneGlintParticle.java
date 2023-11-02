package com.axialeaa.littledetails.particle;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.config.Configs;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GlowstoneGlintParticle extends SpriteBillboardParticle implements InterpretColorList {

    protected GlowstoneGlintParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale *= 0.65F;
        Color4f color = MainEntrypoint.getRandomColorFrom(random, Configs.Colors.GLOWSTONE_GLINT_PARTICLE_COLORS);
        this.red = color.r;
        this.green = color.g;
        this.blue = color.b;
        this.alpha = color.a;
        this.collidesWithWorld = false;
        this.maxAge = this.random.nextInt(20) + 15;
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
            return new GlowstoneGlintParticle(world, x, y, z, 0.0, 0.0, 0.0, this.spriteProvider);
        }
    }

}
