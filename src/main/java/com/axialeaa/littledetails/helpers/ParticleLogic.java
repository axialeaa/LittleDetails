package com.axialeaa.littledetails.helpers;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class ParticleLogic {

    /**
     * Creates a particle based on the bounds of a full cube.
     * @param world the world this method is called in
     * @param pos the position this method is called from
     * @param state the blockstate of this block
     * @param particleType the particle that should be created
     * @param particleEnabled the output of particle effect toggle
     * @param particleRarity the output of the particle rarity slider
     */
    public static void createFullCubeEmission(World world, BlockPos pos, BlockState state, DefaultParticleType particleType, ConfigBoolean particleEnabled, ConfigInteger particleRarity) {
        Random random = world.getRandom();
        if (particleEnabled.getBooleanValue() && random.nextInt(particleRarity.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double x = (double)pos.getX() + (direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.52);
                double y = (double)pos.getY() + (direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.52);
                double z = (double)pos.getZ() + (direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.52);
                world.addParticle(particleType, x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

    /**
     * Creates a bubble pop particle.
     * @param world the world this method is called in
     * @param random used to variate the pitch of the sound
     * @param x the x position this method is called from
     * @param y the y position this method is called from
     * @param z the z position this method is called from
     */
    public static void createPop(World world, Random random, double x, double y, double z) {
        if (Configs.Particles.PARTICLE_BUBBLE_POP.getBooleanValue()) {
            world.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0, 0.0, 0.0);
            if (Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue() > 0)
                world.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, (float)Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue(), 0.9F + random.nextFloat() * 0.15F, true);
        }
    }

    /**
     * Creates a snore particle.
     * @param world the world this method is called in
     * @param pos the position this method is called from
     * @param velocityY the vertical velocity of the snore particle. This is used for bats that need a negative number because they hang upside-down
     * @param condition the boolean value to look for when parsing. Usually used to check for sleeping
     */
    public static void createHonkMimi(World world, Vec3d pos, double velocityY, boolean condition) {
        Random random = world.getRandom();
        if (Configs.Particles.PARTICLE_SNORE.getBooleanValue() && condition && random.nextInt(Configs.ParticleRarities.SNORE_PARTICLE_RARITY.getIntegerValue()) == 0)
            world.addParticle(LittleDetailsParticleTypes.SNORE, pos.x, pos.y, pos.z, 0.0, velocityY, 0.0);
    }

    /**
     * Creates an explosion flash particle.
     * @param world the world this method is called in
     * @param x the x position this method is called from
     * @param y the y position this method is called from
     * @param z the z position this method is called from
     * @param power the blast radius of the explosion
     * @param condition the boolean value to look for when parsing. Usually used to check for whether explosion particles are enabled for that instance
     */
    public static void createExplosionFlash(World world, double x, double y, double z, float power, boolean condition) {
        if (Configs.Particles.PARTICLE_EXPLOSION_FLASH.getBooleanValue() && condition && power >= Configs.Generic.EXPLOSION_FLASH_POWER_THRESHOLD.getDoubleValue())
            world.addParticle(ParticleTypes.FLASH, x, y, z, 0.0, 0.0, 0.0);
    }

    /**
     * Get the size of the color list. if this is greater than zero, get the entry index matching the random integer between 0 inclusive and the length of the list exclusive, otherwise return the default malilib color4f value.
     * @param random used to pick a random index
     * @param colorList the color list to pick from
     * @return a random entry from the list if the length of configOption is more than zero, otherwise #00000000.
     */
    public static Color4f getRandomColorFrom(Random random, ConfigColorList colorList) {
        List<Color4f> colors = colorList.getColors();
        return colors.size() > 0 ? colors.get(random.nextInt(colors.size())) : Color4f.ZERO;
    }

}
