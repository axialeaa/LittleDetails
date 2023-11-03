package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final private World world;
    @Shadow @Final private double x;
    @Shadow @Final private double y;
    @Shadow @Final private double z;

    @Shadow public abstract boolean shouldDestroy();

    @Shadow @Final private float power;

    @Inject(method = "affectWorld", at = @At(value = "HEAD"))
    public void flashOnExplode(boolean particles, CallbackInfo ci) {
        ParticleLogic.createExplosionFlash(world, x, y, z, power, this.shouldDestroy() && particles);
    }

}