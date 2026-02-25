package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.cca.entity.CurrencyGameComponent;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CurrencyReadoutEvent implements HudRenderCallback {
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int centerX = drawContext.getScaledWindowWidth() / 2;
        int centerY = drawContext.getScaledWindowHeight() / 2;

        int posX = centerX + 160;
        int posY = centerY - 120;

        if (player != null) {
            CurrencyGameComponent currencyGameComponent = CurrencyGameComponent.KEY.get(player);
            String currency = currencyGameComponent.getCurrency() + "";

            /* TODO
                Switch positions of icon and text
             */

            if (currencyGameComponent.currency > Integer.MAX_VALUE || currencyGameComponent.currency == -1) {
                currency = "a fuck ton";
            }

            if (MonsoonClient.isAliveAndInSurvival()) {
                drawContext.drawItem(new ItemStack(MonsoonItems.CRISP),
                        posX - (currency.length() / 2),
                        posY
                );

                drawContext.drawTextWithBackground(
                        textRenderer,
                        Text.literal(currency),
                        posX + 20,
                        posY + 5,
                        48,
                        0xffffff
                );
            }
        }
    }

    //    private void renderCountdown(DrawContext context, RenderTickCounter tickCounter) {
    //    ClientPlayerEntity player = MinecraftClient.getInstance().player;
    //        if (player == null) return;
    //
    //        int ticks = ArisenPlayerComponent.KEY.get(player).arisenTicks;
    //        if (ticks <= 0) return;
    //
    //        int seconds = (int) Math.ceil(ticks / 20f);
    //        int prevSeconds = seconds + 1;
    //
    //        if (seconds != lastSeconds) {
    //            lastSeconds = seconds;
    //            lastChangeTime = System.currentTimeMillis();
    //        }
    //
    //        long now = System.currentTimeMillis();
    //        float t = Math.min(1f, (now - lastChangeTime) / (float) ANIM_DURATION);
    //
    //        t = easeOutCubic(t);
    //
    //        float offsetOld = -20f * t;
    //        float offsetNew = 20f * (1 - t);
    //
    //        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
    //        int x = context.getScaledWindowWidth() / 2 - 7;
    //        int y = 3;
    //
    //        if (t < 1f) {
    //            MutableText prevText = Text.literal("" + prevSeconds);
    //            context.drawTextWithShadow(tr, prevText, x, (int) (y + offsetOld), 0x62ffae);
    //        }
    //
    //        MutableText curText = Text.literal("" + seconds);
    //        context.drawTextWithShadow(tr, curText, x, (int) (y + offsetNew), 0x62ffae);
    //
    //        int i = tr.getWidth(curText);
    //        int j = (context.getScaledWindowWidth() - i) / 2;
    //        int k = context.getScaledWindowHeight() - 59;
    //
    //        if (!MinecraftClient.getInstance().interactionManager.hasStatusBars()) {
    //            k += 14;
    //        }
    //
    //        float shouldOffset = (float) (Math.sin(MinecraftClient.getInstance().world.getTime() / 4f) * 2f);
    //
    //        Quaternionf quaternionf = new Quaternionf();
    //        quaternionf.rotateXYZ(0f, 0, (float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
    //        context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
    //        context.getMatrices().translate(0, shouldOffset, -6);
    //        context.getMatrices().translate(0, -shouldOffset, 6);
    //        quaternionf.rotationYXZ(0, 0f, -(float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
    //        context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
    //    }
}
