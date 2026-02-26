package net.kindling.monsoon.impl.game.util;

import net.kindling.monsoon.api.event.PlayerGameDeathEvent;
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

    // Server-Side check
    public static boolean isAliveAndInSurvival(PlayerEntity player) {
        return player != null && !player.isInCreativeMode() && !PlayerGameComponent.KEY.get(player).isDead();
    }

    // Kills the player using the component, do not actually kill the player.
    public static void killPlayer(PlayerEntity player) {
        PlayerGameComponent component = PlayerGameComponent.KEY.get(player);
        component.setDead(true);

        PlayerGameDeathEvent.EVENT.invoker().playerDeath(player, player.getWorld(), component);

        if (player instanceof ServerPlayerEntity serverPlayer) serverPlayer.changeGameMode(GameMode.SPECTATOR);
    }

    // Checks if the player is in the custom dimension.
    public static boolean isInWasteland(PlayerEntity player) {
        return player != null && player.getWorld().getRegistryKey().equals(wastelandKey);
    }

    public static void setHeldStack(PlayerEntity player, ItemStack givenStack) {
        PlayerGameComponent component = PlayerGameComponent.KEY.get(player);
        component.setHeldStack(givenStack);
    }

    // "Held Stack" was meant to replace the inventory, as in being able to pick up blocks in one hand and use items in another. This functionality prolly makes more sense qwq
    public static ItemStack getHeldStack(PlayerEntity player) {
        return player.getMainHandStack().isEmpty() ? player.getOffHandStack() : player.getMainHandStack();
    }

    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    public static int getInTicks(int seconds, int minutes) {
        return (minutes * 60 * 20) + (seconds * 20);
    }
}
