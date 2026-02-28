package net.kindling.monsoon.impl;

import foundry.veil.platform.VeilEventPlatform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.client.audio.MonsoonAudioEngine;
import net.kindling.monsoon.impl.client.event.CurrencyReadoutEvent;
import net.kindling.monsoon.impl.client.event.HandleFlashlightsEvent;
import net.kindling.monsoon.impl.client.event.HeldItemDisplayHudEvent;
import net.kindling.monsoon.impl.event.KeyInputHandler;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.kindling.monsoon.impl.index.*;
import net.kindling.monsoon.impl.index.client.MonsoonModelLayers;
import net.minecraft.client.MinecraftClient;

public class MonsoonClient implements ClientModInitializer {
    public static final MonsoonAudioEngine AUDIO_ENGINE = new MonsoonAudioEngine();

    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            AUDIO_ENGINE.scanAndLoad("monsoon", "audio")
                    .thenRun(() ->
                            System.out.println("[Monsoon] Audio Engine successfully loaded")
                    );
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            AUDIO_ENGINE.stopAndUnloadAll()
                    .thenRun(() -> System.out.println("[Monsoon] Audio Engine unloaded"));
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            AUDIO_ENGINE.tick();
        });

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
    }

    public static boolean isAliveAndInSurvival() {
        if (MinecraftClient.getInstance().player == null) return false;
        return GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().player);
    }
}
