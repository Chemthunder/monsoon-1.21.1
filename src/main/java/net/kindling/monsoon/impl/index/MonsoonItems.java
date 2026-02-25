package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.item.CrispItem;
import net.kindling.monsoon.impl.item.FlashlightItem;
import net.kindling.monsoon.impl.item.tool.AxeItem;
import net.kindling.monsoon.impl.item.tool.PickaxeItem;
import net.kindling.monsoon.impl.item.TestItem;
import net.kindling.monsoon.impl.item.tool.ShovelItem;
import net.minecraft.item.Item;

public interface MonsoonItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Monsoon.MOD_ID);

    Item TEST_ITEM = ITEMS.register("test_item", TestItem::new, new Item.Settings());
    Item FLASHLIGHT = ITEMS.register("flashlight", FlashlightItem::new, new Item.Settings().maxCount(1));

    Item CRISP = ITEMS.register("crisp", CrispItem::new, new Item.Settings());

    Item PICKAXE = ITEMS.register("pickaxe", PickaxeItem::new, new Item.Settings().maxCount(1));
    Item SHOVEL = ITEMS.register("shovel", ShovelItem::new, new Item.Settings().maxCount(1));
    Item AXE = ITEMS.register("axe", AxeItem::new, new Item.Settings().maxCount(1));

    static void init() {
        //
    }
}
