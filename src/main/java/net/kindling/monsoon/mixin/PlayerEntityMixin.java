package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyReturnValue(method = "canConsume", at = @At("RETURN"))
    private boolean monsoon$forceEating(boolean original) {
        return true;
    }
}
