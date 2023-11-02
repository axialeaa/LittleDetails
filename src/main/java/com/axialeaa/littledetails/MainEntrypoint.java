package com.axialeaa.littledetails;

import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainEntrypoint implements ModInitializer {

	public static final String MOD_ID = "littledetails";
	public static final String MOD_NAME = "Axia's Little Details";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LittleDetailsParticleTypes.registerParticles();
		InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
		LOGGER.info(MOD_NAME + " initialized. It's all about the little things.");
	}

}