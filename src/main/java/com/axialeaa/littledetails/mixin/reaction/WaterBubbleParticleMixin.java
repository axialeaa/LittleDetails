package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.config.Configs;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.WaterBubbleParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
    public void createPopOnMarkedDead(CallbackInfo ci) {
        if (Configs.Particles.PARTICLE_BUBBLE_POP.getBooleanValue()) {
            this.world.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            if (Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue() > 0)
                this.world.playSound(this.x, this.y, this.z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, (float)Configs.Sounds.BUBBLE_POP_VOLUME.getDoubleValue(), 0.9F + random.nextFloat() * 0.15F, true);
        }
    }

}