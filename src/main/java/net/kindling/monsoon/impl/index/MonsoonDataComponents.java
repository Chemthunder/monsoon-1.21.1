package net.kindling.monsoon.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.component.ComponentType;

public interface MonsoonDataComponents {
    ComponentTypeRegistrant COMPONENT_TYPES = new ComponentTypeRegistrant(Monsoon.MOD_ID);

    ComponentType<Boolean> REPAIRED = COMPONENT_TYPES.register("repaired", builder -> builder.codec(Codec.BOOL));

    static void init() {
        //
    }
}
