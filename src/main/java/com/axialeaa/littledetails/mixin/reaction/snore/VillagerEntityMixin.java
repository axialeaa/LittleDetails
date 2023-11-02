package com.axialeaa.littledetails.mixin.reaction.snore;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends LivingEntity {

    protected VillagerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void honkMimi(CallbackInfo ci) {
        ParticleLogic.createHonkMimi(this.isSleeping(), this.getEntityWorld(), this.getEyePos(), 0.03);
    }

}