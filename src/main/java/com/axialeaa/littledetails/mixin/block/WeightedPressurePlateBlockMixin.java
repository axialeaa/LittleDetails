package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.WeightedPressurePlateBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PressurePlateBlock.class)
public class WeightedPressurePlateBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.REDSTONE_PARTICLES_ENABLED.getBooleanValue() && Configs.Particles.PARTICLE_PRESSURE_PLATE_DUST.getBooleanValue() && random.nextInt(Configs.ParticleRarities.PRESSURE_PLATE_DUST_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.DOWN;
            int powerLevel = state.get(WeightedPressurePlateBlock.POWER);
            if (powerLevel != 0) {
                double x = (double)pos.getX() + (direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.4);
                double y = (double)pos.getY() + 0.2 + 0.1 * (double)direction.getOffsetY();
                double z = (double)pos.getY() + (direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.4);
                world.addParticle(new DustParticleEffect(Configs.Generic.WEIGHTED_PRESSURE_PLATE_POWER_COLORS.getBooleanValue() ? RedstoneWireBlock.COLORS[powerLevel].toVector3f() : DustParticleEffect.RED, 1.0F), x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

}
