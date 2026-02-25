package net.kindling.monsoon.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Unique private static final Identifier MONSOON_SUN = Monsoon.id("textures/environment/monsoon_sun.png");

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void monsoon$clouds(MatrixStack matrices, Matrix4f matrix4f, Matrix4f matrix4f2, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        if (GameUtils.isInWasteland(MinecraftClient.getInstance().player)) {
            ci.cancel();
        }
    }

    @WrapOperation(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
                    ordinal = 0
            )
    )
    private void monsoon$sun(int texture, Identifier id, Operation<Void> original) {
        original.call(texture, GameUtils.isInWasteland(MinecraftClient.getInstance().player) ? MONSOON_SUN : id);
    }
}