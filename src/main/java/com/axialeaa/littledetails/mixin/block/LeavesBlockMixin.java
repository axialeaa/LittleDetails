package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.util.FallingLeafMode;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.axialeaa.littledetails.config.Configs.ParticleRarities.*;
import static com.axialeaa.littledetails.config.Configs.Particles.*;
import static com.axialeaa.littledetails.particle.LittleDetailsParticleTypes.*;
import static net.minecraft.block.Blocks.*;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow @Final public static BooleanProperty PERSISTENT;
    @Shadow @Final public static IntProperty DISTANCE;

    @Inject(method = "randomDisplayTick", at = @At("HEAD"))
    private void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Generic.FALLING_LEAF_PARTICLES_ENABLED.getBooleanValue() && !isFaceFullSquare(world.getBlockState(pos.down()).getCollisionShape(world, pos.down()), Direction.UP)) {
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_OAK_LEAF, FALLING_OAK_LEAF_PARTICLE_RARITY, OAK_LEAVES, FALLING_OAK_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_SPRUCE_LEAF, FALLING_SPRUCE_LEAF_PARTICLE_RARITY, SPRUCE_LEAVES, FALLING_SPRUCE_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_BIRCH_LEAF, FALLING_BIRCH_LEAF_PARTICLE_RARITY, BIRCH_LEAVES, FALLING_BIRCH_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_JUNGLE_LEAF, FALLING_JUNGLE_LEAF_PARTICLE_RARITY, JUNGLE_LEAVES, FALLING_JUNGLE_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_ACACIA_LEAF, FALLING_ACACIA_LEAF_PARTICLE_RARITY, ACACIA_LEAVES, FALLING_ACACIA_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_DARK_OAK_LEAF, FALLING_DARK_OAK_LEAF_PARTICLE_RARITY, DARK_OAK_LEAVES, FALLING_DARK_OAK_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_MANGROVE_LEAF, FALLING_MANGROVE_LEAF_PARTICLE_RARITY, MANGROVE_LEAVES, FALLING_MANGROVE_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_AZALEA_LEAF, FALLING_AZALEA_LEAF_PARTICLE_RARITY, AZALEA_LEAVES, FALLING_AZALEA_LEAF);
            spawnLeafParticle(world, pos, random, PARTICLE_FALLING_AZALEA_PETAL, FALLING_AZALEA_PETAL_PARTICLE_RARITY, FLOWERING_AZALEA_LEAVES, FALLING_AZALEA_PETAL);
        }
    }

    @Unique
    private static void spawnLeafParticle(World world, BlockPos pos, Random random, ConfigBoolean particleEnabled, ConfigInteger particleRarity, Block leavesType, DefaultParticleType particleType) {
        if (particleEnabled.getBooleanValue() && (world.getBlockState(pos).isOf(leavesType) && satisfiesSetting(world, pos)) && random.nextInt(particleRarity.getIntegerValue()) == 0)
            ParticleUtil.spawnParticle(world, pos, random, particleType);
    }

    @Unique
    private static boolean satisfiesSetting(World world, BlockPos pos) {
        FallingLeafMode leavesMode = (FallingLeafMode)Configs.Generic.FALLING_LEAF_MODE.getOptionListValue();
        boolean isPersistent = world.getBlockState(pos).get(PERSISTENT);
        return switch (leavesMode) {
            case ALL -> true;
            case NATURAL -> !isPersistent;
            case PLACED -> isPersistent;
            case DECAYING -> !isPersistent && world.getBlockState(pos).get(DISTANCE) == 7;
        };
    }

}
