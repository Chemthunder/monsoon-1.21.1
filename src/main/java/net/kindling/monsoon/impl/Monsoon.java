package net.kindling.monsoon.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.kindling.monsoon.compat.MonsoonConfig;
import net.kindling.monsoon.impl.index.*;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Monsoon implements ModInitializer {
    public static final String MOD_ID = "monsoon";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        // AcornLib
        ALib.registerModMenu(MOD_ID, 0x233e37);
        MidnightConfig.init(MOD_ID, MonsoonConfig.class);

        // Initialization
        MonsoonBlockEntities.init();
        MonsoonBlocks.init();
        MonsoonEntities.init();
        MonsoonItemGroups.init();
        MonsoonItems.init();

        // Events

        // Networking

        LOGGER.info("Monsoons are brewing...");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
