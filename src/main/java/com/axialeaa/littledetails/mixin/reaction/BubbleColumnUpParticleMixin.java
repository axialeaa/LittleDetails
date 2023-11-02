package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.client.particle.BubbleColumnUpParticle;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BubbleColumnUpParticle.class)
public abstract class BubbleColumnUpParticleMixin extends SpriteBillboardParticle {

    protected BubbleColumnUpParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/BubbleColumnUpParticle;markDead()V"))
    public void popOnMarkedDead(CallbackInfo ci) {
        ParticleLogic.createPop(world, BlockPos.ofFloored(x, y, z), random);
    }

}