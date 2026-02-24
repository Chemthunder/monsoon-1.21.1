package net.kindling.monsoon.impl.game.util;

import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
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
        return player != null && !player.isInCreativeMode() && !PlayerGameComponent.KEY.get(player).isDead;
    }

    public static void killPlayer(PlayerEntity player) {
        PlayerGameComponent game = PlayerGameComponent.KEY.get(player);

        game.isDead = true;
        game.sync();

        if (player instanceof ServerPlayerEntity serverPlayer) serverPlayer.changeGameMode(GameMode.SPECTATOR);
    }

    public static boolean isInWasteland(PlayerEntity player) {
        return player != null && player.getWorld().getRegistryKey().equals(wastelandKey);
    }

    public static void setHeldStack(PlayerEntity player, ItemStack givenStack) {
        PlayerGameComponent game = PlayerGameComponent.KEY.get(player);

        game.heldStack = givenStack;
        game.sync();
    }

    public static ItemStack getHeldStack(PlayerEntity player) {
        return PlayerGameComponent.KEY.get(player).heldStack;
    }

    public int ticksToSeconds(int ticks) {return ticks / 20;}
}
