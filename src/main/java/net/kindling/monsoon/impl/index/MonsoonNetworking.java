package net.kindling.monsoon.impl.index;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kindling.monsoon.impl.networking.FlashlightToggleC2S;
import net.minecraft.util.Identifier;

import static net.kindling.monsoon.impl.Monsoon.id;

public interface MonsoonNetworking {
    Identifier FLASHLIGHT_ID = id("flashlight");
    Identifier FLASHLIGHT_SYNC_ID = id("flashlight");

    static void registerC2SPackets() {
    }

    static void registerS2CPackets() {

    }
}
