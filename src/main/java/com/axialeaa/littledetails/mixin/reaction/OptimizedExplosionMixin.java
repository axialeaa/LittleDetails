package com.axialeaa.littledetails.mixin.reaction;

import carpet.helpers.OptimizedExplosion;
import carpet.mixins.ExplosionAccessor;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptimizedExplosion.class)
public class OptimizedExplosionMixin {

    @Inject(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    private static void flashOnExplode(Explosion e, boolean spawnParticles, CallbackInfo ci, @Local ExplosionAccessor eAccess, @Local World world, @Local(ordinal = 0) boolean damagesTerrain) {
        float power = eAccess.getRadius();
        ParticleLogic.createExplosionFlash(world, eAccess.getX(), eAccess.getY(), eAccess.getZ(), power, damagesTerrain && spawnParticles);
    }

}