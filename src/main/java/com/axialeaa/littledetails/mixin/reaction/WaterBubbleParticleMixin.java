package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.WaterBubbleParticle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaterBubbleParticle.class)
public abstract class WaterBubbleParticleMixin extends SpriteBillboardParticle {

    protected WaterBubbleParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/WaterBubbleParticle;markDead()V"))
    public void popOnMarkedDead(CallbackInfo ci) {
        ParticleLogic.createPop(world, random, x, y, z);
    }

}