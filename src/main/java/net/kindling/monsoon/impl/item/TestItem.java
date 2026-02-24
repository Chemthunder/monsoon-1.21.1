package net.kindling.monsoon.impl.item;

import net.kindling.monsoon.impl.game.Game;
import net.kindling.monsoon.impl.game.GameClient;
import net.kindling.monsoon.impl.index.MonsoonFog;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getOffHandStack().isOf(this)) {
            String string;

            if (!Game.isActive(world)) {
                if (world.isClient && world instanceof ClientWorld clientWorld) {
                    GameClient.startGame(clientWorld);
                } else {
                    Game.startGame(world);
                }

                string = "started";

            } else {
                if (world.isClient && world instanceof ClientWorld clientWorld) {
                    GameClient.endGame(clientWorld);
                }

                Game.endGame(world);
                string = "stopped";
            }

            user.sendMessage(Text.literal("Game has been: " + string), true);
            user.playSoundToPlayer(SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.MASTER, 1, 1);
        } else {
            MonsoonFog.getFog()
                    .setFogStart(20.0F)
                    .setFogEnd(75.0F)
                    .setFogThickness(0.9F)
                    .setHeightFalloff(0.0F)
                    .setChaos(0.1F)
                    .setFogColor(0F, 0F, 0F);
        }

        return super.use(world, user, hand);
    }
}
