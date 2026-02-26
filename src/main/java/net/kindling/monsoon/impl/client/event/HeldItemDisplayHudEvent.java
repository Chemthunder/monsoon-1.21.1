package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.game.util.GameUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class HeldItemDisplayHudEvent implements HudRenderCallback {
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        int centerX = drawContext.getScaledWindowWidth() / 2;
        int centerY = drawContext.getScaledWindowHeight() / 2;

        if (MonsoonClient.isAliveAndInSurvival()) {
            int itemX = centerX - 110;
            int itemY = centerY + 70;
            int offset = 5;

            boolean shouldRender = true; // dev

            if (!GameUtils.getHeldStack(player).isEmpty()) {
                if (shouldRender) {
                    drawContext.drawItem(GameUtils.getHeldStack(player),
                            itemX,
                            itemY
                    );

                    drawContext.drawTextWithShadow(
                            textRenderer,
                            Text.translatable("monsoon.misc.itemReadout", GameUtils.getHeldStack(player).getName()),
                            itemX + 30,
                            itemY + offset,
                            0xffffff
                    );
                }
            }
        }
    }
}
