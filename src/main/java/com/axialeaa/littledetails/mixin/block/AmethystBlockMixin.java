package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AmethystBlock.class)
public class AmethystBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Particles.PARTICLE_AMETHYST_TWINKLE.getBooleanValue() && random.nextInt(Configs.ParticleRarities.AMETHYST_TWINKLE_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.52;
                double e = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.52;
                double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.52;
                world.addParticle(LittleDetailsParticleTypes.AMETHYST_TWINKLE, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + f, 0.0, 0.0, 0.0);
            }
        }
    }

}
