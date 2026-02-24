package net.kindling.monsoon.impl.index.tag;

import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface MonsoonItemTags {
    TagKey<Item> SPECIAL_ITEMS = create("special_items");
    TagKey<Item> RESOURCE_ITEMS = create("resource_items");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Monsoon.id(id));
    }
}
