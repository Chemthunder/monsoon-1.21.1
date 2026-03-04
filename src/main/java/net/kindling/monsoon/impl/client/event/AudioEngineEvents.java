package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kindling.monsoon.impl.MonsoonClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class AudioEngineEvents {
    public static class Join implements ClientPlayConnectionEvents.Join {
        @Override
        public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
            MonsoonClient.AUDIO_ENGINE.scanAndLoad("monsoon", "audio")
                    .thenRun(() ->
                            System.out.println("[Monsoon] Audio Engine successfully loaded")
                    );
        }
    }

    public static class Disconnect implements ClientPlayConnectionEvents.Disconnect {
        @Override
        public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
            MonsoonClient.AUDIO_ENGINE.stopAndUnloadAll()
                    .thenRun(() -> System.out.println("[Monsoon] Audio Engine unloaded"));
        }
    }

    public static class Tick implements ClientTickEvents.EndTick {
        @Override
        public void onEndTick(MinecraftClient client) {
            MonsoonClient.AUDIO_ENGINE.tick();
        }
    }
}
