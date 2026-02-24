package net.kindling.monsoon.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public interface EndGameEvent {
    Event<EndGameEvent> EVENT = EventFactory.createArrayBacked(EndGameEvent.class, events -> (player, world, component) -> {
        List<EndGameEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(EndGameEvent::getPriority));
        for (EndGameEvent event : sortedEvents) {
            event.startGame(player, world, component);
        }
    });

    default int getPriority() {
        return 1000;
    }

    void startGame(PlayerEntity player, World world, WorldGameComponent component);
}
