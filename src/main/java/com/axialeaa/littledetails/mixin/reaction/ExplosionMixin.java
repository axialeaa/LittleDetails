package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.config.Configs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final private World world;
    @Shadow @Final private double x;
    @Shadow @Final private double y;
    @Shadow @Final private double z;

    @Shadow public abstract boolean shouldDestroy();

    @Shadow @Final private float power;

    @Inject(method = "affectWorld", at = @At(value = "HEAD"))
    public void addFlash(CallbackInfo ci) {
        if (Configs.Particles.PARTICLE_EXPLOSION_FLASH.getBooleanValue() && this.shouldDestroy() && this.power >= Configs.Generic.EXPLOSION_FLASH_POWER_THRESHOLD.getDoubleValue())
            this.world.addParticle(ParticleTypes.FLASH, this.x, this.y, this.z, 0.0, 0.0, 0.0);
    }

}