package net.kindling.monsoon.impl.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class WorldBroadcastManager {


    public static void playSoundEventGlobally(SoundEvent sound, ServerWorld serverWorld, SoundCategory category, float pitch, float volume) {
        for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
            serverPlayer.playSoundToPlayer(sound, category, pitch, volume);
        }
    }
}
