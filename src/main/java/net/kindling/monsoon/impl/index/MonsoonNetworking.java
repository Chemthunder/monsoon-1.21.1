package net.kindling.monsoon.impl.index;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kindling.monsoon.impl.networking.c2s.FlashlightTogglePayload;

public interface MonsoonNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playC2S().register(FlashlightTogglePayload.ID, FlashlightTogglePayload.CODEC);
    }

    static void registerC2SPackets() {
    }

    static void registerS2CPackets() {
        ServerPlayNetworking.registerGlobalReceiver(FlashlightTogglePayload.ID, new FlashlightTogglePayload.Receiver());
    }
}
