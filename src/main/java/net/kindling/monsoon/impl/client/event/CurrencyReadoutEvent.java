package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
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

public class CurrencyReadoutEvent implements HudRenderCallback {

    private static int displayedValue = -1;
    private static int previousValue = 0;

    private static long lastChangeTime = 0;
    private static final long ANIM_DURATION = 150;

    private static int direction = 0;

    private static int useTicks = 0;
    private static float alpha = 0f;

    public static void toggleUse(boolean value) {
        if (!value) useTicks = 0;
        else alpha = 1f;
    }

    public static void setUseTime(int ticks) {
        useTicks = ticks;
    }

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        if (!MonsoonClient.isAliveAndInSurvival()) return;

        CurrencyGameComponent comp = CurrencyGameComponent.KEY.get(player);
        int actual = comp.getCurrency();

        updateFade();
        if (alpha <= 0.01f) return;

        if (displayedValue == -1) {
            displayedValue = actual;
            previousValue = actual;
        }

        long now = System.currentTimeMillis();

        if (now - lastChangeTime >= ANIM_DURATION) {
            if (actual > displayedValue) {
                previousValue = displayedValue;
                displayedValue++;
                direction = 1;
                lastChangeTime = now;
            } else if (actual < displayedValue) {
                previousValue = displayedValue;
                displayedValue--;
                direction = -1;
                lastChangeTime = now;
            } else {
                direction = 0;
            }
        }

        render(context);
    }

    private void render(DrawContext context) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer tr = mc.textRenderer;

        int centerX = context.getScaledWindowWidth() / 2;
        int centerY = context.getScaledWindowHeight() / 2;

        int posX = centerX + 160;
        int posY = centerY - 120;

        int alphaInt = (int)(alpha * 255f);
        int color = (alphaInt << 24) | 0x00ffffff;

        context.drawItem(new ItemStack(MonsoonItems.CRISP), posX, posY);

        String prev = String.valueOf(previousValue);
        String curr = String.valueOf(displayedValue);

        int maxLength = Math.max(prev.length(), curr.length());
        prev = String.format("%" + maxLength + "s", prev).replace(' ', '0');
        curr = String.format("%" + maxLength + "s", curr).replace(' ', '0');

        long now = System.currentTimeMillis();
        float t = Math.min(1f, (now - lastChangeTime) / (float) ANIM_DURATION);
        t = easeOutCubic(t);

        int baseX = posX + 20;
        int y = posY + 5;

        for (int i = 0; i < maxLength; i++) {

            char prevChar = prev.charAt(i);
            char currChar = curr.charAt(i);

            int charWidth = tr.getWidth(String.valueOf(currChar));
            int x = baseX + tr.getWidth(curr.substring(0, i));

            if (prevChar == currChar || direction == 0) {
                context.drawTextWithShadow(tr,
                        Text.literal(String.valueOf(currChar)),
                        x,
                        y,
                        color);
            } else {

                float offsetOld;
                float offsetNew;

                if (direction > 0) {
                    offsetOld = -20f * t;
                    offsetNew = 20f * (1 - t);
                } else {
                    offsetOld = 20f * t;
                    offsetNew = -20f * (1 - t);
                }

                if (t < 1f) {
                    context.drawTextWithShadow(tr,
                            Text.literal(String.valueOf(prevChar)),
                            x,
                            (int)(y + offsetOld),
                            color);

                    context.drawTextWithShadow(tr,
                            Text.literal(String.valueOf(currChar)),
                            x,
                            (int)(y + offsetNew),
                            color);
                } else {
                    context.drawTextWithShadow(tr,
                            Text.literal(String.valueOf(currChar)),
                            x,
                            y,
                            color);
                }
            }
        }
    }

    private void updateFade() {
        if (useTicks > 0) {
            useTicks--;
            alpha = Math.min(1f, alpha + 0.08f);
        } else {
            alpha = Math.max(0f, alpha - 0.08f);
        }
    }

    private float easeOutCubic(float t) {
        return 1f - (float)Math.pow(1f - t, 3);
    }
}