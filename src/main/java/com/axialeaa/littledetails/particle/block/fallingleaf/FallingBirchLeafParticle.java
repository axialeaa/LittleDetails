package com.axialeaa.littledetails.particle.block.fallingleaf;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.CherryLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class FallingBirchLeafParticle extends CherryLeavesParticle {

    protected FallingBirchLeafParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider);
        this.gravityStrength = (float)Configs.Constants.FALLING_LEAF_GRAVITY_CONSTANT.getDoubleValue();
        Color4f color = ParticleLogic.getRandomColorFrom(random, Configs.Colors.FALLING_BIRCH_LEAF_PARTICLE_COLORS);
        this.red = color.r;
        this.green = color.g;
        this.blue = color.b;
        this.alpha = color.a;
        this.scale = (float)Configs.Constants.FALLING_LEAF_SCALE_CONSTANT.getDoubleValue();
        this.setBoundingBoxSpacing(this.scale, this.scale);
        this.setSpriteForAge(spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FallingBirchLeafParticle(world, x, y, z, spriteProvider);
        }
    }

}