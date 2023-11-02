package com.axialeaa.littledetails.mixin.environment;

import com.axialeaa.littledetails.config.Configs;
import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import com.axialeaa.littledetails.util.FireflySpawnMode;
import com.axialeaa.littledetails.util.SandstormWeatherMode;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import net.minecraft.block.Block;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.Heightmap;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {

    @Shadow public abstract ClientWorld.Properties getLevelProperties();

    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DynamicRegistryManager registryManager, RegistryEntry<DimensionType> dimensionEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates) {
        super(properties, registryRef, registryManager, dimensionEntry, profiler, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
    }

    @Inject(method = "randomBlockDisplayTick", at = @At("HEAD"))
    public void randomBlockDisplayTick(int centerX, int centerY, int centerZ, int radius, Random random, Block block, BlockPos.Mutable pos, CallbackInfo ci) {
        spawnEnvParticle(pos, fireflySatisfiesSetting(pos), Configs.Biomes.FIREFLY_PARTICLE_BIOMES, Configs.Particles.PARTICLE_FIREFLY, Configs.ParticleRarities.FIREFLY_PARTICLE_RARITY, LittleDetailsParticleTypes.FIREFLY);
        spawnEnvParticle(pos, sandstormSatisfiesSetting(), Configs.Biomes.SANDSTORM_PARTICLE_BIOMES, Configs.Particles.PARTICLE_SANDSTORM, Configs.ParticleRarities.SANDSTORM_PARTICLE_RARITY, LittleDetailsParticleTypes.SANDSTORM);
        spawnEnvParticle(pos, sandstormSatisfiesSetting(), Configs.Biomes.RED_SANDSTORM_PARTICLE_BIOMES, Configs.Particles.PARTICLE_RED_SANDSTORM, Configs.ParticleRarities.RED_SANDSTORM_PARTICLE_RARITY, LittleDetailsParticleTypes.RED_SANDSTORM);
        spawnWorldSpawnParticle(pos);
    }

    @Unique
    private boolean fireflySatisfiesSetting(BlockPos pos) {
        FireflySpawnMode fireflySpawnMode = (FireflySpawnMode)Configs.Generic.FIREFLY_SPAWN_MODE.getOptionListValue();
        boolean isNight = this.getTimeOfDay() >= 14000 && this.getTimeOfDay() <= 22000;
        boolean isWaterBelow = this.getFluidState(pos.down(3)).isIn(FluidTags.WATER);
        return switch (fireflySpawnMode) {
            case ALWAYS -> true;
            case NIGHT -> isNight;
            case ABOVE_WATER -> isWaterBelow;
            case NIGHT_ABOVE_WATER -> isNight && isWaterBelow;
        };
    }

    @Unique
    private boolean sandstormSatisfiesSetting() {
        return switch ((SandstormWeatherMode)Configs.Generic.SANDSTORM_WEATHER_MODE.getOptionListValue()) {
            case ALL -> true;
            case RAIN -> isRaining();
            case RAIN_THUNDER -> isRaining() || isThundering();
            case THUNDER -> isThundering();
        };
    }

    @Unique
    private void spawnEnvParticle(BlockPos pos, boolean condition, ConfigStringList biomesConfigList, ConfigBoolean particleEnabled, ConfigInteger particleRarity, DefaultParticleType particleType) {
        boolean isValidSpawnLocation = !this.getBlockState(pos).isFullCube(this, pos) && this.getFluidState(pos).isEmpty() && !(this.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos).getY() > pos.getY());
        if (condition && isValidSpawnLocation && particleEnabled.getBooleanValue() && biomesConfigList.getStrings().size() > 0 && random.nextInt(particleRarity.getIntegerValue()) == 0)
            for (int i = 0; i < biomesConfigList.getStrings().size(); i++)
                if (this.getBiome(pos).matchesId(Identifier.tryParse(biomesConfigList.getStrings().get(i))))
                    this.addParticle(particleType, (double)pos.getX() + this.random.nextDouble(), (double)pos.getY() + this.random.nextDouble(), (double)pos.getZ() + this.random.nextDouble(), 0.0, 0.0, 0.0);
    }

    @Unique
    private void spawnWorldSpawnParticle(BlockPos pos) {
        if (Configs.Particles.PARTICLE_WORLD_SPAWN_CENTER.getBooleanValue() && Objects.equals(this.getSpawnPos(), BlockPos.ofFloored(pos.toCenterPos())) && random.nextInt(Configs.ParticleRarities.WORLD_SPAWN_CENTER_PARTICLE_RARITY.getIntegerValue()) == 0) {
            int blockOffsetX = random.nextInt(2) * 2 - 1;
            int blockOffsetZ = random.nextInt(2) * 2 - 1;
            double x = pos.getX() + 0.5;
            double y = this.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).getY();
            double z = pos.getZ() + 0.5;
            double velocityX = random.nextFloat() * (float)blockOffsetX;
            double velocityY = ((double)random.nextFloat() - 0.5) * 0.125;
            double velocityZ = random.nextFloat() * (float)blockOffsetZ;
            this.addParticle(ParticleTypes.PORTAL, x, y, z, velocityX, velocityY, velocityZ);
        }
    }

}