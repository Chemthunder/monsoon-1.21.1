package net.kindling.monsoon.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public interface PlayerGameDeathEvent {
    Event<PlayerGameDeathEvent> EVENT = EventFactory.createArrayBacked(PlayerGameDeathEvent.class, events -> (player, world, component) -> {
        List<PlayerGameDeathEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(PlayerGameDeathEvent::getPriority));
        for (PlayerGameDeathEvent event : sortedEvents) {
            event.playerDeath(player, world, component);
        }
    });

    default int getPriority() {
        return 1000;
    }

    void playerDeath(PlayerEntity player, World world, PlayerGameComponent component);
}
