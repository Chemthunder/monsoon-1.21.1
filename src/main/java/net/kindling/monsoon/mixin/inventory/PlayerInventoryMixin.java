package net.kindling.monsoon.mixin.inventory;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @ModifyReturnValue(method = "getHotbarSize", at = @At("RETURN"))
    private static int monsoon$hotbarSize(int original) {
        return 5;
    }

    @ModifyArgs(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/collection/DefaultedList;ofSize(ILjava/lang/Object;)Lnet/minecraft/util/collection/DefaultedList;"
            )
    )
    private void monsoon$removeInventory(Args args) {
        int original = args.get(0);
        if (original == 36) {
            args.set(0, 5);
        }
    }

    @ModifyExpressionValue(method = "scrollInHotbar", at = @At(value = "CONSTANT", args = "intValue=9"))
    private int monsoon$scrollInHotbar(int original) {
        return 5;
    }

    @ModifyExpressionValue(method = "isValidHotbarIndex", at = @At(value = "CONSTANT", args = "intValue=9"))
    private static int monsoon$isValidHotbarIndex(int original) {
        return 5;
    }

    @ModifyExpressionValue(method = "getSwappableHotbarSlot", at = @At(value = "CONSTANT", args = "intValue=9"))
    private int monsoon$getSwappableHotbarSlot(int original) {
        return 5;
    }
}
