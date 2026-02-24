package net.kindling.monsoon.impl.util;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class WorldBroadcastManager {
    public static void playSoundEventGlobally(ServerWorld serverWorld, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
            serverPlayer.playSoundToPlayer(sound, category, volume, pitch);
        }
    }

    public static void playScreenshakeGlobally(ServerWorld serverWorld, float intensity, int duration) {
        for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
            if (serverPlayer instanceof ScreenShaker screenShaker) {
                screenShaker.addScreenShake(intensity, duration);
            }
        }
    }
}
