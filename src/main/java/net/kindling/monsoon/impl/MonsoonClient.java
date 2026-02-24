package net.kindling.monsoon.impl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.client.event.MonsoonHud;
import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonEntities;
import net.kindling.monsoon.impl.index.client.MonsoonModelLayers;

@Environment(EnvType.CLIENT)
public class MonsoonClient implements ClientModInitializer {
    public void onInitializeClient() {
        // Initialization
        MonsoonBlockEntities.clientInit();
        MonsoonBlocks.clientInit();
        MonsoonEntities.clientInit();

        MonsoonModelLayers.init();

        // Events
        HudRenderCallback.EVENT.register(new MonsoonHud());
    }
}
