package net.kindling.monsoon.impl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.kindling.monsoon.impl.client.event.MonsoonHud;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonEntities;
import net.kindling.monsoon.impl.index.MonsoonFog;

public class MonsoonClient implements ClientModInitializer {
    public void onInitializeClient() {
        MonsoonBlocks.clientInit();
        MonsoonEntities.clientInit();

        HudRenderCallback.EVENT.register(new MonsoonHud());

        MonsoonFog.init();
    }
}
