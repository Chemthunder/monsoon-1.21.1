package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.item.FlashlightItem;
import net.kindling.monsoon.impl.item.TestItem;
import net.minecraft.item.Item;

public interface MonsoonItems {
    ItemRegistrant rant = new ItemRegistrant(Monsoon.MOD_ID);

    Item TEST_ITEM = rant.register("test_item", TestItem::new, new Item.Settings());
    Item FLASHLIGHT = rant.register("flashlight", FlashlightItem::new, new Item.Settings().maxCount(1));

    static void init() {
        //
    }
}
