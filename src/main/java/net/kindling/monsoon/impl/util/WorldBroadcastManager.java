package net.kindling.monsoon.impl.util;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
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

    public static void playScreenshakeGlobally(ServerWorld serverWorld, float intensity, int duration) {
        for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
            if (serverPlayer instanceof ScreenShaker screenShaker) {
                screenShaker.addScreenShake(intensity, duration);
            }
        }
    }
}
