package net.kindling.monsoon.impl.item;

import net.kindling.monsoon.impl.index.MonsoonFog;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    private static boolean green = false;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!green) {
            MonsoonFog.getFog()
                    .setFogStart(8.0F)
                    .setFogEnd(30.0F)
                    .setFogThickness(2.0F)
                    .setHeightFalloff(0.0F)
                    .setChaos(0.3F)
                    .setFogColor(0.6F, 0.1F, 0.1F);

            green = true;
        } else {
            MonsoonFog.getFog()
                    .setFogStart(30.0F)
                    .setFogEnd(50.0F)
                    .setFogThickness(0.7F)
                    .setHeightFalloff(0.0F)
                    .setChaos(0.3F)
                    .setFogColor(0.0F, 0.5F, 0.2F);

            green = false;
        }

        return super.use(world, user, hand);
    }
}
