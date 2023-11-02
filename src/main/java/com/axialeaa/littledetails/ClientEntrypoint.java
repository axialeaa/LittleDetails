package com.axialeaa.littledetails;

import com.axialeaa.littledetails.particle.*;
import com.axialeaa.littledetails.particle.block.AmethystTwinkleParticle;
import com.axialeaa.littledetails.particle.block.GlowBerryShineParticle;
import com.axialeaa.littledetails.particle.block.GlowstoneGlintParticle;
import com.axialeaa.littledetails.particle.block.SeaLanternShimmerParticle;
import com.axialeaa.littledetails.particle.block.fallingleaf.*;
import com.axialeaa.littledetails.particle.environment.FireflyParticle;
import com.axialeaa.littledetails.particle.environment.RedSandstormParticle;
import com.axialeaa.littledetails.particle.environment.SandstormParticle;
import com.axialeaa.littledetails.particle.reaction.RainRippleParticle;
import com.axialeaa.littledetails.particle.reaction.SnoreParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.axialeaa.littledetails.MainEntrypoint.MOD_ID;

public class ClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.AMETHYST_TWINKLE, AmethystTwinkleParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.GLOWSTONE_GLINT, GlowstoneGlintParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.SEA_LANTERN_SHIMMER, SeaLanternShimmerParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.GLOW_BERRY_SHINE, GlowBerryShineParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.RAIN_RIPPLE, RainRippleParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.SNORE, SnoreParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FIREFLY, FireflyParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.SANDSTORM, SandstormParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.RED_SANDSTORM, RedSandstormParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_OAK_LEAF, FallingOakLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_SPRUCE_LEAF, FallingSpruceLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_BIRCH_LEAF, FallingBirchLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_JUNGLE_LEAF, FallingJungleLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_ACACIA_LEAF, FallingAcaciaLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_DARK_OAK_LEAF, FallingDarkOakLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_MANGROVE_LEAF, FallingMangroveLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_AZALEA_LEAF, FallingAzaleaLeafParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LittleDetailsParticleTypes.FALLING_AZALEA_PETAL, FallingAzaleaPetalParticle.Factory::new);

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(MOD_ID, "32x_upscale"), modContainer, Text.translatable("resourcePack.littledetails:32x_upscale.name"), ResourcePackActivationType.NORMAL)
		);
		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(MOD_ID, "programmer_art"), modContainer, Text.translatable("resourcePack.littledetails:programmer_art.name"), ResourcePackActivationType.NORMAL)
		);
	}

}