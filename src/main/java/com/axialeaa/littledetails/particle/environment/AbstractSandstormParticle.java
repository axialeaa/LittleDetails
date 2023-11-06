package com.axialeaa.littledetails.particle.environment;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public abstract class AbstractSandstormParticle extends AscendingParticle {

    protected AbstractSandstormParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider, ConfigColorList colorList) {
        super(world, x, y, z, 0.5F, -0.1F, 0.5F, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 1.0F, 20, 0.1F, true);
        this.setSpriteForAge(spriteProvider);
        Color4f color = ParticleLogic.getRandomColorFrom(random, colorList);
        this.red = color.r;
        this.green = color.g;
        this.blue = color.b;
        this.alpha = color.a;
    }

    @Override
    public ParticleTextureSheet getType() {
        return alpha < 1 ? ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT : ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.onGround || this.velocityX == 0.0 || this.velocityZ == 0.0)
            this.markDead();
    }
}