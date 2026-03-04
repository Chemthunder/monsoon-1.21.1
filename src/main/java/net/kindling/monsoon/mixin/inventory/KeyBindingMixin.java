package net.kindling.monsoon.mixin.inventory;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.kindling.monsoon.impl.MonsoonClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {
    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
    private boolean monsoon$wasPressed(boolean original) {
        KeyBinding keyBinding = (KeyBinding)(Object)this;
        if (MonsoonClient.isAliveAndInSurvival() && isKeybindBlocked(keyBinding)) return false;
        return original;
    }

    @ModifyReturnValue(method = "isPressed", at = @At("RETURN"))
    private boolean monsoon$isPressed(boolean original) {
        KeyBinding keyBinding = (KeyBinding)(Object)this;
        if (MonsoonClient.isAliveAndInSurvival() && isKeybindBlocked(keyBinding)) return false;
        return original;
    }

    @Unique
    private boolean isKeybindBlocked(KeyBinding keyBinding) {
        GameOptions options = MinecraftClient.getInstance().options;
        if (options != null) {
            return (keyBinding == options.inventoryKey
                    || keyBinding == options.hotbarKeys[5]
                    || keyBinding == options.hotbarKeys[6]
                    || keyBinding == options.hotbarKeys[7]
                    || keyBinding == options.hotbarKeys[8]
                    || keyBinding == options.swapHandsKey
            );
        }

        return false;
    }
}
