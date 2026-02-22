package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.item.Item;

public interface MonsoonItems {
    ItemRegistrant rant = new ItemRegistrant(Monsoon.MOD_ID);

    Item TEST_ITEM = rant.register("test_item", Item::new, new Item.Settings());

    static void init() {
        //
    }
}
