package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void honkMimi(CallbackInfo ci) {
        ParticleLogic.createHonkMimi(Configs.Generic.PLAYER_SNORING.getBooleanValue() && this.isSleeping(), this.getEntityWorld(), this.getEyePos(), 0.03);
    }

}
