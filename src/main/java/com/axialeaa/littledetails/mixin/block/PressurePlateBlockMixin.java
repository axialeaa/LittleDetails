package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PressurePlateBlock.class)
public class PressurePlateBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.REDSTONE_PARTICLES_ENABLED.getBooleanValue() && state.get(PressurePlateBlock.POWERED) && Configs.Particles.PARTICLE_PRESSURE_PLATE_DUST.getBooleanValue() && random.nextInt(Configs.ParticleRarities.PRESSURE_PLATE_DUST_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.DOWN;
            double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.4;
            double e = (double)pos.getY() + 0.2 + 0.1 * (double)direction.getOffsetY();
            double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.4;
            world.addParticle(new DustParticleEffect(DustParticleEffect.RED, 1.0F), (double)pos.getX() + d, e, (double)pos.getZ() + f, 0.0, 0.0, 0.0);
        }
    }

}
