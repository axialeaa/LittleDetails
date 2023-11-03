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

    public static void createFullCubeEmission(World world, BlockPos pos, BlockState state, DefaultParticleType particleType, ConfigBoolean particleEnabled, ConfigInteger particleRarity) {
        Random random = world.getRandom();
        if (particleEnabled.getBooleanValue() && random.nextInt(particleRarity.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double randX = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.52;
                double randY = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.52;
                double randZ = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.52;
                world.addParticle(particleType, (double)pos.getX() + randX, (double)pos.getY() + randY, (double)pos.getZ() + randZ, 0.0, 0.0, 0.0);
            }
        }
    }

    public static void createPop(World world, Random random, double x, double y, double z) {
        if (Configs.Particles.PARTICLE_BUBBLE_POP.getBooleanValue()) {
            world.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0, 0.0, 0.0);
            if (Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue() > 0)
                world.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, (float)Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue(), 0.9F + random.nextFloat() * 0.15F, true);
        }
    }

    public static void createHonkMimi(World world, Vec3d pos, double velocityY, boolean condition) {
        Random random = world.getRandom();
        if (Configs.Particles.PARTICLE_SNORE.getBooleanValue() && condition && random.nextInt(Configs.ParticleRarities.SNORE_PARTICLE_RARITY.getIntegerValue()) == 0)
            // if the option is enabled, the sleeping condition returns true and a random number between 0 inc. and the rarity config input exc. is 0,
            //      create a snore particle here with the y velocity specified in the method call
            world.addParticle(LittleDetailsParticleTypes.SNORE, pos.x, pos.y, pos.z, 0.0, velocityY, 0.0);
    }

    public static void createExplosionFlash(World world, double x, double y, double z, float power, boolean condition) {
        if (Configs.Particles.PARTICLE_EXPLOSION_FLASH.getBooleanValue() && condition && power >= Configs.Generic.EXPLOSION_FLASH_POWER_THRESHOLD.getDoubleValue())
            world.addParticle(ParticleTypes.FLASH, x, y, z, 0.0, 0.0, 0.0);
    }

    public static Color4f getRandomColorFrom(Random random, ConfigColorList configOption) {
        List<Color4f> colorList = configOption.getColors();
        return colorList.size() > 0 ? colorList.get(random.nextInt(colorList.size())) : Color4f.ZERO;
        // if the length of the color list is more than zero, pick a random entry from the list otherwise pick #00000000
        // in more verbose terminology, get the size of the color list. if this is greater than zero, get the entry index matching the random integer between
        //      0 inc. and the length of the list exc., otherwise return the default malilib color4f value
    }

}
