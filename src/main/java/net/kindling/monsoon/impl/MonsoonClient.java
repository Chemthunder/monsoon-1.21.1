package net.kindling.monsoon.impl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.client.event.MonsoonHud;
import net.kindling.monsoon.impl.index.MonsoonBlocks;

public class MonsoonClient implements ClientModInitializer {
    public void onInitializeClient() {
        MonsoonBlocks.clientInit();

        HudRenderCallback.EVENT.register(new MonsoonHud());
    }
}
