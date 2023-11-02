package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "randomDisplayTick", at = @At("HEAD"))
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Particles.PARTICLE_GLOWSTONE_GLINT.getBooleanValue() && state.isOf(Blocks.GLOWSTONE) && random.nextInt(Configs.ParticleRarities.GLOWSTONE_GLINT_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.52;
                double e = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.52;
                double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.52;
                world.addParticle(LittleDetailsParticleTypes.GLOWSTONE_GLINT, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + f, 0.0, 0.0, 0.0);
            }
        }
        else if (Configs.Particles.PARTICLE_SEA_LANTERN_SHIMMER.getBooleanValue() && state.isOf(Blocks.SEA_LANTERN) && random.nextInt(Configs.ParticleRarities.SEA_LANTERN_SHIMMER_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (direction != Direction.UP && !state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.52;
                double e = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.52;
                double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.52;
                world.addParticle(LittleDetailsParticleTypes.SEA_LANTERN_SHIMMER, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + f, 0.0, 0.0, 0.0);
            }
        }
        else if (Configs.Particles.PARTICLE_JACK_O_LANTERN_FLAME.getBooleanValue() && state.isOf(Blocks.JACK_O_LANTERN) && random.nextInt(Configs.ParticleRarities.JACK_O_LANTERN_FLAME_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = state.get(CarvedPumpkinBlock.FACING);
            Direction.Axis axis = direction.getAxis();
            double d = (double)pos.getX() + 0.5 + (axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : random.nextDouble() - 0.5);
            double e = random.nextDouble() + pos.getY();
            double f = (double)pos.getZ() + 0.5 + (axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : random.nextDouble() - 0.5);
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.FLAME, d, e, f, 0.0, 0.0, 0.0);
        }
    }

}
