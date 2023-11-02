package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ButtonBlock.class)
public class ButtonBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.REDSTONE_PARTICLES_ENABLED.getBooleanValue() && state.get(ButtonBlock.POWERED) && Configs.Particles.PARTICLE_BUTTON_DUST.getBooleanValue() && random.nextInt(Configs.ParticleRarities.BUTTON_DUST_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = state.get(ButtonBlock.FACING).getOpposite();
            Direction direction2 = WallMountedBlock.getDirection(state).getOpposite();
            double d = (double)pos.getX() + 0.5 + 0.1 * (double)direction.getOffsetX() + 0.2 * (double)direction2.getOffsetX();
            double e = (double)pos.getY() + 0.5 + 0.1 * (double)direction.getOffsetY() + 0.2 * (double)direction2.getOffsetY();
            double f = (double)pos.getZ() + 0.5 + 0.1 * (double)direction.getOffsetZ() + 0.2 * (double)direction2.getOffsetZ();
            world.addParticle(new DustParticleEffect(DustParticleEffect.RED, 1.0F), d, e, f, 0.0, 0.0, 0.0);
        }
    }

}
