package net.kindling.monsoon.impl.game;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.VeilRenderer;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.minecraft.world.World;

public class Game {
    public static void init(World world) {
        WorldGameComponent.KEY.get(world).isActive = true;
        WorldGameComponent.KEY.get(world).sync();

        Monsoon.LOGGER.info("Game has been initialized");
    }

    public static void forceEndGame(World world) {
        WorldGameComponent worldGameComponent = WorldGameComponent.KEY.get(world);

        worldGameComponent.isActive = false;
        worldGameComponent.sync();

        Monsoon.LOGGER.info("game ended successfully");
    }

    public static boolean isActive(World world) {
        return world != null && WorldGameComponent.KEY.get(world).isActive;
    }
}
