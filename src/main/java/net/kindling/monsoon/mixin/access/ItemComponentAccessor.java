package net.kindling.monsoon.mixin.access;

import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemComponentAccessor {
    @Accessor("components")
    void setComponents(ComponentMap components);
}