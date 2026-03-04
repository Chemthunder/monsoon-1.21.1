package net.kindling.monsoon.compat;

import net.kindling.monsoon.impl.game.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import squeek.appleskin.api.event.HUDOverlayEvent;
import squeek.appleskin.api.handler.EventHandler;

public class AppleskinCompat {
    public static class Exhaustion implements EventHandler<HUDOverlayEvent.Exhaustion> {
        @Override
        public void interact(HUDOverlayEvent.Exhaustion exhaustion) {
            exhaustion.isCanceled = GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().getCameraEntity());
        }
    }

    public static class Saturation implements EventHandler<HUDOverlayEvent.Saturation> {
        @Override
        public void interact(HUDOverlayEvent.Saturation saturation) {
            saturation.isCanceled = GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().getCameraEntity());
        }
    }

    public static class HungerRestored implements EventHandler<HUDOverlayEvent.HungerRestored> {
        @Override
        public void interact(HUDOverlayEvent.HungerRestored hungerRestored) {
            hungerRestored.isCanceled = GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().getCameraEntity());
        }
    }

    public static class HealthRestored implements EventHandler<HUDOverlayEvent.HealthRestored> {
        @Override
        public void interact(HUDOverlayEvent.HealthRestored healthRestored) {
            healthRestored.isCanceled = GameUtils.isAliveAndInSurvival(MinecraftClient.getInstance().getCameraEntity());
        }
    }
}
