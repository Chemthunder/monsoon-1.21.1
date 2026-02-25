package net.kindling.monsoon.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;

public interface MonsoonDataComponents {
    ComponentTypeRegistrant COMPONENT_TYPES = new ComponentTypeRegistrant(Monsoon.MOD_ID);

    ComponentType<Boolean> FIXED = COMPONENT_TYPES.register("fixed", builder -> builder.codec(Codec.BOOL));

    static void init() {
        //
    }
}
