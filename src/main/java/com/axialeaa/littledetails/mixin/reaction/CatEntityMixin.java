package com.axialeaa.littledetails.mixin.reaction;

import com.axialeaa.littledetails.helpers.ParticleLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends Entity {

    @Shadow public abstract boolean isInSleepingPose();
    @Shadow public abstract boolean isHeadDown();

    public CatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void honkMimi(CallbackInfo ci) {
        ParticleLogic.createHonkMimi(this.isInSleepingPose() && this.isHeadDown(), this.getEntityWorld(), this.getEyePos(), 0.03);
    }

}
