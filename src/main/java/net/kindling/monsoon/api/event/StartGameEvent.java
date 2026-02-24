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

public interface StartGameEvent {
    Event<StartGameEvent> EVENT = EventFactory.createArrayBacked(StartGameEvent.class, events -> (player, world, component) -> {
        List<StartGameEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(StartGameEvent::getPriority));
        for (StartGameEvent event : sortedEvents) {
            event.startGame(player, world, component);
        }
    });

    default int getPriority() {
        return 1000;
    }

    void startGame(PlayerEntity player, World world, WorldGameComponent component);
}
