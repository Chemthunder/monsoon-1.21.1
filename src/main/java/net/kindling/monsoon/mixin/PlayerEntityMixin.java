package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.kindling.monsoon.impl.util.GameUtils;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @ModifyReturnValue(method = "canConsume", at = @At("RETURN"))
    private boolean monsoon$forceEating(boolean original) {
        return true;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void monsoonDebug$stack(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        GameUtils.setHeldStack(player, player.getMainHandStack());
    }
}
