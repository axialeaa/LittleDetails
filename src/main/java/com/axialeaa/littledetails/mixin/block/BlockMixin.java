package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
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
        if (state.isOf(Blocks.GLOWSTONE))
            ParticleLogic.createFullCubeEmission(world, pos, state, LittleDetailsParticleTypes.GLOWSTONE_GLINT, Configs.Particles.PARTICLE_GLOWSTONE_GLINT, Configs.ParticleRarities.GLOWSTONE_GLINT_PARTICLE_RARITY);
        else if (state.isOf(Blocks.SEA_LANTERN))
            ParticleLogic.createFullCubeEmission(world, pos, state, LittleDetailsParticleTypes.SEA_LANTERN_SHIMMER, Configs.Particles.PARTICLE_SEA_LANTERN_SHIMMER, Configs.ParticleRarities.SEA_LANTERN_SHIMMER_PARTICLE_RARITY);

        else if (Configs.Particles.PARTICLE_JACK_O_LANTERN_FLAME.getBooleanValue() && state.isOf(Blocks.JACK_O_LANTERN) && random.nextInt(Configs.ParticleRarities.JACK_O_LANTERN_FLAME_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = state.get(CarvedPumpkinBlock.FACING);
            Direction.Axis axis = direction.getAxis();
            double x = (double)pos.getX() + 0.5 + (axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : random.nextDouble() - 0.5);
            double y = random.nextDouble() + pos.getY();
            double z = (double)pos.getZ() + 0.5 + (axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : random.nextDouble() - 0.5);
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
        }
    }

}
