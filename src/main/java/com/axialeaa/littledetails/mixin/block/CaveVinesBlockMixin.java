package com.axialeaa.littledetails.mixin.block;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.CaveVinesBodyBlock;
import net.minecraft.block.CaveVinesHeadBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({CaveVinesHeadBlock.class, CaveVinesBodyBlock.class})
public class CaveVinesBlockMixin extends BlockMixin {

    @Override
    public void injectedRandomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Configs.Particles.PARTICLE_GLOW_BERRY_SHINE.getBooleanValue() && (state.get(CaveVinesBodyBlock.BERRIES) || state.get(CaveVinesHeadBlock.BERRIES))&& random.nextInt(Configs.ParticleRarities.GLOW_BERRY_SHINE_PARTICLE_RARITY.getIntegerValue()) == 0) {
            Direction direction = Direction.random(random);
            if (direction != Direction.UP && direction != Direction.DOWN) {
                double x = (double)pos.getX() + 0.5 + (direction.getOffsetX() == 0 ? 0.5 - random.nextDouble() : (double)direction.getOffsetX() * 0.1);
                double y = (double)pos.getY() + 0.5 + (direction.getOffsetY() == 0 ? 0.5 - random.nextDouble() : (double)direction.getOffsetY() * 0.3);
                double z = (double)pos.getZ() + 0.5 + (direction.getOffsetZ() == 0 ? 0.5 - random.nextDouble() : (double)direction.getOffsetZ() * 0.1);
                world.addParticle(LittleDetailsParticleTypes.GLOW_BERRY_SHINE, x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

}
