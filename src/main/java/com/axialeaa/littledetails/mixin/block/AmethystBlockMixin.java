package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AmethystBlock.class)
public class AmethystBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        ParticleLogic.createFullCubeEmission(world, pos, state, LittleDetailsParticleTypes.AMETHYST_TWINKLE, Configs.Particles.PARTICLE_AMETHYST_TWINKLE, Configs.ParticleRarities.AMETHYST_TWINKLE_PARTICLE_RARITY);
    }

}
