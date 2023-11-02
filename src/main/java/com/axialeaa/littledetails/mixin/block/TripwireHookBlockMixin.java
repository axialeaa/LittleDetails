package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.block.TripwireHookBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TripwireHookBlock.class)
public class TripwireHookBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.REDSTONE_PARTICLES_ENABLED.getBooleanValue() && state.get(TripwireHookBlock.POWERED) && Configs.Particles.PARTICLE_TRIPWIRE_DUST.getBooleanValue() && random.nextInt(Configs.ParticleRarities.TRIPWIRE_DUST_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = state.get(TripwireHookBlock.FACING).getOpposite();
            double d = (double)pos.getX() + 0.5 + 0.1 * (double)direction.getOffsetX();
            double e = (double)pos.getY() + 0.5 + 0.1 * (double)direction.getOffsetY();
            double f = (double)pos.getZ() + 0.5 + 0.1 * (double)direction.getOffsetZ();
            world.addParticle(new DustParticleEffect(DustParticleEffect.RED, 1.0F), d, e, f, 0.0, 0.0, 0.0);        }
    }

}
