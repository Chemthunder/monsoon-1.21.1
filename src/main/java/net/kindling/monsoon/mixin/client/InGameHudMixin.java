package net.kindling.monsoon.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique private static final Identifier MONSOON_HOTBAR = Monsoon.id("hud/monsoon_hotbar");
    @Unique private static final Identifier MONSOON_HOTBAR_SELECTOR = Monsoon.id("hud/monsoon_selector");

    @Unique private static final Identifier MONSOON_CROSSHAIR = Monsoon.id("hud/monsoon_crosshair");


    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void monsoon$disableHealth(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (GameUtils.isAliveAndInSurvival(player)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void monsoon$disableFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (GameUtils.isAliveAndInSurvival(player)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void monsoon$disableXPLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MonsoonClient.isAliveAndInSurvival()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void monsoon$disableHudRendering(DrawContext context, CallbackInfo ci) {
        if (MonsoonClient.isAliveAndInSurvival()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void monsoon$disableChat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MonsoonClient.isAliveAndInSurvival()) {
            ci.cancel();
        }
    }

    @WrapOperation(
            method = "renderHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void monsoon$hotbarOverride(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
        original.call(instance, MonsoonClient.isAliveAndInSurvival() ? MONSOON_HOTBAR : texture, x, y, width, height);
    }

    @WrapOperation(
            method = "renderHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 1
            )
    )
    private void monsoon$hotbarSelectorOverride(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
        original.call(instance, MonsoonClient.isAliveAndInSurvival() ? MONSOON_HOTBAR_SELECTOR : texture, x, y, width, height);
    }

    @WrapOperation(
            method = "renderCrosshair",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void monsoon$crosshairOverride(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
        original.call(instance, MonsoonClient.isAliveAndInSurvival() ? MONSOON_CROSSHAIR : texture, x, y, width, height);
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void monsoon$crosshairShenanigans(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (MonsoonClient.isAliveAndInSurvival() && GameUtils.getHeldStack(player).isEmpty()) {
            ci.cancel();
        }
    }
}