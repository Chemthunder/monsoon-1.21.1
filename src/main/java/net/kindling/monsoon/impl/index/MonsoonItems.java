package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.item.FlashlightItem;
import net.minecraft.item.Item;

public interface MonsoonItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Monsoon.MOD_ID);

    Item TEST_ITEM = ITEMS.register("test_item", Item::new, new Item.Settings());
    Item FLASHLIGHT = ITEMS.register("flashlight", FlashlightItem::new, new Item.Settings().maxCount(1));

    static void init() {
        //
    }
}
