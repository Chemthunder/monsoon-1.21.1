package net.kindling.monsoon.mixin;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void monsoon$hungerOverhaul(PlayerEntity player, CallbackInfo ci) {
        ci.cancel();
    }
}
