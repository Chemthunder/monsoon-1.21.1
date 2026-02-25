package net.kindling.monsoon.impl.event;

import net.kindling.monsoon.api.event.StartGameEvent;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class GameBeginEvent implements StartGameEvent {

    public void startGame(PlayerEntity player, World world, WorldGameComponent component) {
        int ticks = component.getTicks();

        player.sendMessage(Text.translatable("monsoon.game.begin"), true);

        /*
            Fades to black screen, before teleporting all to the airship, and then fades to original
         */
    }
}
