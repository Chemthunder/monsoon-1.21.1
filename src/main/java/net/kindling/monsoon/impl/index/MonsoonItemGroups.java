package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public interface MonsoonItemGroups {
    ItemGroupRegistrant ITEM_GROUPS = new ItemGroupRegistrant(Monsoon.MOD_ID);

    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MonsoonItems.TEST_ITEM))
            .displayName(Text.translatable("itemGroup.monsoon").styled(style -> style.withColor(0xFF233e37)))
            .build();

    static void init() {
        ITEM_GROUPS.register("monsoon", MAIN, MonsoonItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        for (Item ITEMS : MonsoonItems.ITEMS.toRegister) {
            itemGroup.add(ITEMS);
        }

        for (Block BLOCKS : MonsoonBlocks.BLOCKS.toRegister) {
            itemGroup.add(BLOCKS);
        }
    }
}
