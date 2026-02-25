package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.kindling.monsoon.impl.item.MonsoonToolItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
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

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void monsoon$unableToAttackEntitiesIfNotHoldingWeapon(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (!(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof MonsoonToolItem)) {
            if (GameUtils.isAliveAndInSurvival(player)) {
                ci.cancel();
            }
        }
    }
}
