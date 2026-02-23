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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MonsoonFog.getFog()
                .setFogStart(30.0F)
                .setFogEnd(50.0F)
                .setFogThickness(0.7F)
                .setHeightFalloff(0.0F)
                .setChaos(0.3F)
                .setFogColor(0.6F, 0.1F, 0.1F);

        return super.use(world, user, hand);
    }
}
