package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
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

        int centerX = drawContext.getScaledWindowWidth() / 2;
        int centerY = drawContext.getScaledWindowHeight() / 2;

        if (GameUtils.isAliveAndInSurvival(player)) {
            PlayerGameComponent playerGameComponent = PlayerGameComponent.KEY.get(player);

            int itemX = centerX - 110;
            int itemY = centerY + 70;
            boolean shouldRender = false;

            if (!GameUtils.getHeldStack(player).isEmpty()) {
                if (shouldRender) {
                    drawContext.drawItem(GameUtils.getHeldStack(player),
                            itemX,
                            itemY
                    );

                    drawContext.drawTextWithShadow(
                            textRenderer,
                            Text.translatable("monsoon.misc.itemreadout.filled " + GameUtils.getHeldStack(player)),
                            itemX,
                            itemY + 10,
                            0xffffff
                    );
                }
            } else {
                drawContext.drawTextWithShadow(
                        textRenderer,
                        Text.translatable("monsoon.misc.itemreadout.empty"),
                        itemX,
                        itemY,
                        0xffffff
                );
            }

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
