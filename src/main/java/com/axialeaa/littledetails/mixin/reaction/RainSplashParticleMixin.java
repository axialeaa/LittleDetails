package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import net.minecraft.client.particle.RainSplashParticle;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RainSplashParticle.class)
public abstract class RainSplashParticleMixin extends SpriteBillboardParticle {

    protected RainSplashParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void addSplash(CallbackInfo ci) {
        BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
        double d = Math.max(this.world.getBlockState(blockPos).getCollisionShape(this.world, blockPos).getEndingCoord(Direction.Axis.Y, this.x - (double)blockPos.getX(), this.z - (double)blockPos.getZ()), this.world.getFluidState(blockPos).getHeight(this.world, blockPos));
        if (Configs.Particles.PARTICLE_RAIN_RIPPLE.getBooleanValue() && this.world.getFluidState(blockPos).isStill() && d > 0.0 && this.y == (double)blockPos.getY() + d) {
            this.world.addParticle(LittleDetailsParticleTypes.RAIN_RIPPLE, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            this.markDead();
        }
    }

}