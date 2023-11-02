package com.axialeaa.littledetails.helpers;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class ParticleLogic {

    public static void createPop(World world, BlockPos pos, Random random) {
        if (Configs.Particles.PARTICLE_BUBBLE_POP.getBooleanValue()) {
            world.addParticle(ParticleTypes.BUBBLE_POP, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
            if (Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue() > 0)
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, (float)Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue(), 0.9F + random.nextFloat() * 0.15F, true);
        }
    }

    public static void createHonkMimi(boolean condition, World world, Vec3d pos, double velocityY) {
        Random random = world.getRandom();
        if (Configs.Particles.PARTICLE_SNORE.getBooleanValue() && condition && random.nextInt(Configs.ParticleRarities.SNORE_PARTICLE_RARITY.getIntegerValue()) == 0)
            world.addParticle(LittleDetailsParticleTypes.SNORE, pos.x, pos.y, pos.z, 0.0, velocityY, 0.0);
    }

    public static Color4f getRandomColorFrom(Random random, ConfigColorList configOption) {
        List<Color4f> colorList = configOption.getColors();
        return colorList.size() > 0 ? colorList.get(random.nextInt(colorList.size())) : Color4f.ZERO;
        // If the length of the color list is more than zero, pick a random entry from the list otherwise pick #00000000.
        // In more verbose terminology, get the size of the color list. If this is greater than zero, get the entry index matching the random integer between
        //      zero inclusive and the length of the list exclusive, otherwise return the default Color4f value.
    }

}
