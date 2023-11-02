package com.axialeaa.littledetails.particle;

import com.axialeaa.littledetails.MainEntrypoint;
import com.axialeaa.littledetails.config.Configs;
import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class FireflyParticle extends AscendingParticle implements InterpretColorList {

    protected FireflyParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.1F, -0.1F, 0.1F, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 1.0F, 80, 0.0F, true);
        Color4f color = MainEntrypoint.getRandomColorFrom(random, Configs.Colors.FIREFLY_PARTICLE_COLORS);
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
        BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
        if (!this.dead && Configs.Generic.FIREFLY_DAMAGE.getBooleanValue())
            if (this.world.getFluidState(blockPos).isIn(FluidTags.LAVA) || this.world.getBlockState(blockPos).isIn(BlockTags.FIRE) || this.world.getBlockState(blockPos).isIn(BlockTags.CAMPFIRES) && this.world.getBlockState(blockPos).get(Properties.LIT)) {
                this.world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
                this.world.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0.0, 0.0, 0.0);
                this.markDead();
            }
            else if ((this.world.getBlockState(blockPos).isOf(Blocks.PITCHER_PLANT) || this.world.getBlockState(blockPos).isOf(Blocks.PITCHER_CROP)) && this.world.getBlockState(blockPos).get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER) {
                this.world.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0, 0.0, 0.0);
                if (Configs.Sounds.PITCHER_PLANT_CHOMP_VOLUME.getDoubleValue() > 0)
                    this.world.playSound(x, y, z, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, (float)Configs.Sounds.PITCHER_PLANT_CHOMP_VOLUME.getDoubleValue(), 0.9F + random.nextFloat() * 0.2F, true);
                this.markDead();
            }
            else if (this.world.getBlockState(blockPos).getBlock() instanceof CactusBlock || this.world.getBlockState(blockPos).isFullCube(world, blockPos)) {
                this.world.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0, 0.0, 0.0);
                this.markDead();
            }
            else if (this.world.getFluidState(blockPos).isIn(FluidTags.WATER))
                this.markDead();
    }

    @Override
    protected int getBrightness(float tint) {
        return (int)(127 + Math.sin(age / 8.0) * 127);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FireflyParticle(world, x, y, z, 0.0, 0.0, 0.0, 1.0F, this.spriteProvider);
        }
    }

}
