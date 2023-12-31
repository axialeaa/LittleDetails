package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedstoneBlock.class)
public class RedstoneBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.REDSTONE_PARTICLES_ENABLED.getBooleanValue() && Configs.Particles.PARTICLE_REDSTONE_BLOCK_DUST.getBooleanValue() && random.nextInt(Configs.ParticleRarities.REDSTONE_BLOCK_DUST_PARTICLE_RARITY.getIntegerValue()) == 0)
            for (Direction direction : Direction.values()) {
                BlockPos blockPos = pos.offset(direction);
                if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                    Direction.Axis axis = direction.getAxis();
                    double x = (double)pos.getX() + (axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getOffsetX() : (double)random.nextFloat());
                    double y = (double)pos.getY() + (axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getOffsetY() : (double)random.nextFloat());
                    double z = (double)pos.getZ() + (axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getOffsetZ() : (double)random.nextFloat());
                    world.addParticle(DustParticleEffect.DEFAULT, x, y, z, 0.0, 0.0, 0.0);
                }
            }
    }

}
