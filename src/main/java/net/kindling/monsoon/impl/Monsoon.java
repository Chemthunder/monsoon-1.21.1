package net.kindling.monsoon.impl;

import net.fabricmc.api.ModInitializer;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonItemGroups;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Monsoon implements ModInitializer {
	public static final String MOD_ID = "monsoon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	public void onInitialize() {
        MonsoonItems.init();
        MonsoonItemGroups.init();
        MonsoonBlocks.init();

		LOGGER.info("Monsoons are brewing...");
	}
}