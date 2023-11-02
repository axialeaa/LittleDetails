package com.axialeaa.littledetails;

import com.axialeaa.littledetails.particle.LittleDetailsParticleTypes;
import fi.dy.masa.malilib.config.options.ConfigColorList;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.util.Color4f;
import fi.dy.masa.malilib.util.StringUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.random.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainEntrypoint implements ModInitializer {

	public static final String MOD_ID = "littledetails";
	public static final String MOD_NAME = "Axia's Little Details";
	public static final String MOD_VERSION = StringUtils.getModVersionString(MOD_ID);
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LittleDetailsParticleTypes.registerParticles();
		InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
	}

	public static Color4f getRandomColorFrom(Random random, ConfigColorList configOption) {
		List<Color4f> colorList = configOption.getColors();
		return colorList.size() > 0 ? colorList.get(random.nextInt(colorList.size())) : Color4f.ZERO;
		// If the length of the color list is more than zero, pick a random entry from the list otherwise pick #00000000.
		// In more verbose terminology, get the size of the color list. If this is greater than zero, get the entry index matching the random integer between
		//      zero inclusive and the length of the list exclusive, otherwise return the default Color4f value.
	}

}