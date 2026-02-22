package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MonsoonHud implements HudRenderCallback {

    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        if (GameUtils.isAliveAndInSurvival(player)) {
            drawContext.drawTextWithShadow(
                    textRenderer,
                    Text.literal("test"),
                    drawContext.getScaledWindowWidth() / 2 - 10,
                    drawContext.getScaledWindowHeight() / 2 - 15,
                    0xffffff
            );
        }
    }
}
