package net.kindling.monsoon.impl;

import foundry.veil.platform.VeilEventPlatform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.kindling.monsoon.compat.AppleskinCompat;
import net.kindling.monsoon.impl.client.event.CurrencyReadoutEvent;
import net.kindling.monsoon.impl.client.event.HandleFlashlightsEvent;
import net.kindling.monsoon.impl.client.event.HeldItemDisplayHudEvent;
import net.kindling.monsoon.impl.event.KeyInputHandler;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.kindling.monsoon.impl.index.*;
import net.kindling.monsoon.impl.index.client.MonsoonModelLayers;
import net.minecraft.client.MinecraftClient;
import squeek.appleskin.api.event.HUDOverlayEvent;

public class MonsoonClient implements ClientModInitializer {
    public void onInitializeClient() {
        /* Initialization */
        MonsoonBlockEntities.clientInit();
        MonsoonBlocks.clientInit();
        MonsoonEntities.clientInit();
        MonsoonFog.init();

        MonsoonModelLayers.init();

        /* Networking */
        MonsoonNetworking.registerS2CPackets();

        /* Events */
        HudRenderCallback.EVENT.register(new HeldItemDisplayHudEvent());
        HudRenderCallback.EVENT.register(new CurrencyReadoutEvent());

        ClientPlayConnectionEvents.DISCONNECT.register(new HandleFlashlightsEvent());
        VeilEventPlatform.INSTANCE.onVeilRenderLevelStage(new HandleFlashlightsEvent());

        KeyInputHandler.register();

        /* Compat */
        if (FabricLoader.getInstance().isModLoaded("appleskin")) {
            HUDOverlayEvent.Exhaustion.EVENT.register(new AppleskinCompat.Exhaustion());
            HUDOverlayEvent.Saturation.EVENT.register(new AppleskinCompat.Saturation());
            HUDOverlayEvent.HungerRestored.EVENT.register(new AppleskinCompat.HungerRestored());
            HUDOverlayEvent.HealthRestored.EVENT.register(new AppleskinCompat.HealthRestored());
        }
    }

    public static boolean isAliveAndInSurvival() {
        if (MinecraftClient.getInstance().player == null) return false;
        return GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().player);
    }
}
