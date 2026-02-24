package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "canConsume", at = @At("RETURN"))
    private boolean monsoon$forceEating(boolean original) {
        return true;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void monsoonDebug$stack(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        //GameUtils.setHeldStack(player, player.getMainHandStack()); // girlie this runs every tick so sending packets every tick on client and server TwT
    }
}
