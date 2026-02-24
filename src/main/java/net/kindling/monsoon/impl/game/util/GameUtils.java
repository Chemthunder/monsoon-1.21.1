package net.kindling.monsoon.impl.game.util;

import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class GameUtils {
    public static RegistryKey<World> wastelandKey = RegistryKey.of(RegistryKeys.WORLD, Monsoon.id("wasteland"));

    public static boolean isAliveAndInSurvival(PlayerEntity player) {
        return player != null && !player.isInCreativeMode() && !PlayerGameComponent.KEY.get(player).isDead();
    }

    public static void killPlayer(PlayerEntity player) {
        PlayerGameComponent game = PlayerGameComponent.KEY.get(player);

        game.setDead(true);

        if (player instanceof ServerPlayerEntity serverPlayer) serverPlayer.changeGameMode(GameMode.SPECTATOR);
    }

    public static boolean isInWasteland(PlayerEntity player) {
        return player != null && player.getWorld().getRegistryKey().equals(wastelandKey);
    }

    public static void setHeldStack(PlayerEntity player, ItemStack givenStack) {
        PlayerGameComponent game = PlayerGameComponent.KEY.get(player);

        game.setHeldStack(givenStack);
    }

    public static ItemStack getHeldStack(PlayerEntity player) {
        return player.getMainHandStack().isEmpty() ? player.getOffHandStack() : player.getMainHandStack();
        //return PlayerGameComponent.KEY.get(player).getHeldStack();
    }

    public int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    public int getInTicks(int seconds, int minutes) {
        return (minutes * 60 * 20) + (seconds * 20);
    }
}
