package net.kindling.monsoon.mixin;

import net.kindling.monsoon.impl.item.CrispItem;
import net.kindling.monsoon.mixin.access.ItemComponentAccessor;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyMaxCount(Item.Settings settings, CallbackInfo ci) {
        Item self = (Item)(Object)this;
        ComponentMap stackMap = self.getComponents();

        Integer sizeMap = stackMap.get(DataComponentTypes.MAX_STACK_SIZE);

        if (sizeMap != null && sizeMap == 64) {
            ComponentMap.Builder builder = ComponentMap.builder();
            builder.addAll(stackMap);
            builder.add(DataComponentTypes.MAX_STACK_SIZE, 99); // max is 99
            ((ItemComponentAccessor) self).setComponents(builder.build());
        }
    }
}
