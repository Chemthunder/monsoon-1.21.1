package net.kindling.monsoon.impl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.client.event.MonsoonHudEvent;
import net.kindling.monsoon.impl.event.KeyInputHandler;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonEntities;
import net.kindling.monsoon.impl.index.MonsoonFog;
import net.kindling.monsoon.impl.index.client.MonsoonModelLayers;
import net.minecraft.client.MinecraftClient;

public class MonsoonClient implements ClientModInitializer {
    public void onInitializeClient() {
        /* Initialization */
        MonsoonBlockEntities.clientInit();
        MonsoonBlocks.clientInit();
        MonsoonEntities.clientInit();
        MonsoonFog.init();

        MonsoonModelLayers.init();

        /* Events */
        HudRenderCallback.EVENT.register(new MonsoonHudEvent());
        KeyInputHandler.register();
    }

    public static boolean isAliveAndInSurvival() {
        if (MinecraftClient.getInstance().player == null) return false;
        return GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().player);
    }
}
