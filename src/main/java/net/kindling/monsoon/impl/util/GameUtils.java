package net.kindling.monsoon.impl.util;

import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.kindling.monsoon.impl.cca.world.GameWorldComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class GameUtils {

    public static boolean isAliveAndInSurvival(PlayerEntity player) {
        return player != null && !player.isInCreativeMode() && !player.isDead() && !PlayerGameComponent.KEY.get(player).isDead;
    }

    public static void killPlayer(PlayerEntity player) {
        PlayerGameComponent game = PlayerGameComponent.KEY.get(player);

        game.isDead = true;
        game.sync();

        if (player instanceof ServerPlayerEntity serverPlayer) serverPlayer.changeGameMode(GameMode.SPECTATOR);
    }

    public static boolean isGameActive(World world) {
        return world != null && GameWorldComponent.KEY.get(world).isActive;
    }
}
