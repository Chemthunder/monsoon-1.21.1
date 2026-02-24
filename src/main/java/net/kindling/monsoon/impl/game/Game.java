package net.kindling.monsoon.impl.game;

import net.kindling.monsoon.api.event.EndGameEvent;
import net.kindling.monsoon.api.event.StartGameEvent;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.minecraft.world.World;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;

public class Game {
    public static void startGame(World world) {
        WorldGameComponent component = WorldGameComponent.KEY.get(world);
        component.setActive(true);

        world.getPlayers().forEach(player -> StartGameEvent.EVENT.invoker().startGame(player, world, component));

        ifDev(() -> Monsoon.LOGGER.info("Game has been initialized"));
    }

    public static void endGame(World world) {
        WorldGameComponent component = WorldGameComponent.KEY.get(world);
        component.setActive(false);

        world.getPlayers().forEach(player -> EndGameEvent.EVENT.invoker().startGame(player, world, component));

        ifDev(() -> Monsoon.LOGGER.info("Game ended successfully"));
    }

    public static boolean isActive(World world) {
        return world != null && WorldGameComponent.KEY.get(world).isActive();
    }
}
