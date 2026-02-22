package net.kindling.monsoon.impl.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface MonsoonItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Monsoon.id("monsoon"));
    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MonsoonItems.TEST_ITEM))
            .displayName(Text.translatable("itemGroup.monsoon").styled(style -> style.withColor(0x233e37)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, MAIN);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(MonsoonItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        for (Item ITEMS : MonsoonItems.rant.toRegister) {
            itemGroup.add(ITEMS);
        }

        for (Block BLOCKS : MonsoonBlocks.rant.toRegister) {
            itemGroup.add(BLOCKS);
        }
    }
}
