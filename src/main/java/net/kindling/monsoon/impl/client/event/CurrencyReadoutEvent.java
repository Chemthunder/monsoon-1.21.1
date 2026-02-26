package net.kindling.monsoon.impl.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.cca.entity.CurrencyGameComponent;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.kindling.monsoon.impl.index.MonsoonSoundEvents;
import net.kindling.monsoon.impl.util.Easing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;

public class CurrencyReadoutEvent implements HudRenderCallback {

    private static int displayedValue = -1;
    private static int previousValue = 0;

    private static long lastChangeTime = 0;
    private static final long ANIM_DURATION = 150;

    private static int direction = 0;

    private static int useTicks = 0;
    private static float alpha = 0f;

    private static int flashColor = 0xFFFFFF;

    private static int flashFadeTicks = 8;
    private static int flashHoldTicks = 0;

    private static int flashTimer = 0;
    private static int flashTotalDuration = 0;

    private static int currentFlashColor = 0xFFFFFF;

    private static Easing flashEasing = Easing.easeInOutCubic;

    public static void flashColor(int hex, int holdTicks, int fadeTicks, Easing easing) {
        flashColor = hex & 0xFFFFFF;
        flashHoldTicks = holdTicks;
        flashFadeTicks = fadeTicks;
        flashEasing = easing;

        flashTotalDuration = fadeTicks + holdTicks + fadeTicks;
        flashTimer = flashTotalDuration;
    }

    public static void flashColor(int hex, int holdTicks) {
        flashColor(hex, holdTicks, 8, Easing.easeInOutCubic);
    }

    public static void toggleUse(boolean value) {
        if (!value) useTicks = 0;
        else alpha = 1f;
    }

    public static void setUseTime(int ticks) {
        useTicks = ticks;
    }

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

        updateFlash();
        render(context);
    }

    private void updateFlash() {
        if (flashTimer <= 0) {
            currentFlashColor = 0xFFFFFF;
            return;
        }

        flashTimer--;

        int fadeInEnd = flashTotalDuration - flashFadeTicks;
        int holdEnd = flashFadeTicks;

        float progress;

        if (flashTimer >= fadeInEnd) {
            float t = 1f - (flashTimer - fadeInEnd) / (float) flashFadeTicks;
            progress = applyEasing(t);
        }

        else if (flashTimer >= holdEnd) {
            progress = 1f;
        }

        else {
            float t = flashTimer / (float) flashFadeTicks;
            progress = applyEasing(t);
        }

        currentFlashColor = lerpColor(0xFFFFFF, flashColor, progress);
    }

    private float applyEasing(float t) {
        t = Math.max(0f, Math.min(1f, t));
        return flashEasing.getFunction().apply((double)t).floatValue();
    }

    private int lerpColor(int from, int to, float t) {
        int r1 = (from >> 16) & 0xFF;
        int g1 = (from >> 8) & 0xFF;
        int b1 = from & 0xFF;

        int r2 = (to >> 16) & 0xFF;
        int g2 = (to >> 8) & 0xFF;
        int b2 = to & 0xFF;

        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);

        return (r << 16) | (g << 8) | b;
    }

    private void render(DrawContext context) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer tr = mc.textRenderer;

        int centerX = context.getScaledWindowWidth() / 2;
        int centerY = context.getScaledWindowHeight() / 2;

        int posX = centerX + 160;
        int posY = centerY - 120;

        int alphaInt = (int)(alpha * 255f);
        int flashRGB = currentFlashColor & 0x00FFFFFF;
        int color = (alphaInt << 24) | flashRGB;

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

                    int oldAlpha = (int)(alphaInt * (1f - t));
                    int newAlpha = (int)(alphaInt * t);

                    int oldColor = (oldAlpha << 24) | flashRGB;
                    int newColor = (newAlpha << 24) | flashRGB;

                    context.drawTextWithShadow(tr,
                            Text.literal(String.valueOf(prevChar)),
                            x,
                            (int)(y + offsetOld),
                            oldColor);

                    context.drawTextWithShadow(tr,
                            Text.literal(String.valueOf(currChar)),
                            x,
                            (int)(y + offsetNew),
                            newColor);
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