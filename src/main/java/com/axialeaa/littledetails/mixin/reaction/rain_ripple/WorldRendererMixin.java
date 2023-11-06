package com.axialeaa.littledetails.mixin.reaction.rain_ripple;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyArg(method = "tickRainSplashing", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private ParticleEffect test(ParticleEffect parameters, @Local FluidState fluidState) {
        return Configs.Particles.PARTICLE_RAIN_RIPPLE.getBooleanValue() && fluidState.isEqualAndStill(Fluids.WATER) ?
            LittleDetailsParticleTypes.RAIN_RIPPLE : parameters;
    }

}
