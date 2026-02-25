package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.kindling.monsoon.impl.item.CrispItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public abstract class ItemMixin {

    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int modifyMaxCount(int original) {
        Item item = (Item) (Object) this;

        if (item instanceof CrispItem) {
            return 128;
        }

        return original;
    }
}
