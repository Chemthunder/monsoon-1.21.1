package net.kindling.monsoon.mixin.client;

import net.kindling.monsoon.impl.game.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionEffects.Overworld.class)
public abstract class DimensionEffectsMixin {

    @Inject(method = "adjustFogColor", at = @At("HEAD"), cancellable = true)
    private void monsoon$fogForceBlack(Vec3d par1, float par2, CallbackInfoReturnable<Vec3d> cir) {
        if (GameUtils.isInWasteland(MinecraftClient.getInstance().player)) {
            cir.setReturnValue(new Vec3d( 10, 21, 41).normalize());
        }
    }
}
