package com.axialeaa.littledetails.particle.block.fallingleaf;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.particle.CherryLeavesParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractFallingLeafParticle extends CherryLeavesParticle {

    public Color4f biomeColor = Color4f.fromColor(world.getColor(BlockPos.ofFloored(x, y, z), BiomeColors.FOLIAGE_COLOR));

    protected AbstractFallingLeafParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteProvider, ConfigColorList colorList) {
        super(clientWorld, x, y, z, spriteProvider);
        this.gravityStrength = (float)Configs.Constants.FALLING_LEAF_GRAVITY_CONSTANT.getDoubleValue();
        Color4f color = ParticleLogic.getRandomColorFrom(random, colorList);
        this.red = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.r : color.r;
        this.green = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.g : color.g;
        this.blue = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.b : color.b;
        this.alpha = color.a;
        this.scale = (float)Configs.Constants.FALLING_LEAF_SCALE_CONSTANT.getDoubleValue();
        this.setBoundingBoxSpacing(this.scale, this.scale);
    }

    @Override
    public ParticleTextureSheet getType() {
        return alpha < 1 ? ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT : ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

}
