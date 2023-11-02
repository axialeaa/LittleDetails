package com.axialeaa.littledetails.particle;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.config.Configs;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.client.particle.CherryLeavesParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.client.color.world.BiomeColors.FOLIAGE_COLOR;

public abstract class AbstractFallingLeafParticle extends CherryLeavesParticle {

    BlockPos blockPos = new BlockPos((int)this.x, (int)this.y, (int)this.z);
    public Color4f biomeColor = Color4f.fromColor(this.world.getColor(blockPos, FOLIAGE_COLOR));

    protected AbstractFallingLeafParticle(ClientWorld clientWorld, double d, double e, double f, SpriteProvider spriteProvider, ConfigColorList colorList) {
        super(clientWorld, d, e, f, spriteProvider);
        this.gravityStrength = (float)Configs.Constants.FALLING_LEAF_GRAVITY_CONSTANT.getDoubleValue();
        Color4f color = MainEntrypoint.getRandomColorFrom(random, colorList);
        this.red = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.r : color.r;
        this.green = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.g : color.g;
        this.blue = Configs.Generic.FALLING_LEAF_BIOME_TINT.getBooleanValue() ? biomeColor.b : color.b;
        this.alpha = color.a;
        this.scale = 0.25F;
        this.setBoundingBoxSpacing(this.scale, this.scale);
    }

    @Override
    public ParticleTextureSheet getType() {
        return alpha < 1 ? ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT : ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

}
