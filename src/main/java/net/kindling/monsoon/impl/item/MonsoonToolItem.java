package net.kindling.monsoon.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.kindling.monsoon.impl.index.MonsoonDataComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class MonsoonToolItem extends Item implements ColorableItem {
    String fixedKey;
    String brokenKey;

    public MonsoonToolItem(Settings settings, String fixedKey, String brokenKey) {
        super(settings.component(MonsoonDataComponents.REPAIRED, false));

        this.fixedKey = fixedKey;
        this.brokenKey = brokenKey;
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.getOrDefault(MonsoonDataComponents.REPAIRED, false)) {
            tooltip.add(Text.translatable(this.fixedKey).withColor(startColor(stack)));
        } else {
            tooltip.add(Text.translatable(this.brokenKey).withColor(startColor(stack)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public int getMaxCount() {
        return 1;
    }

    public int startColor(ItemStack itemStack) {
        return 0xFF3b2923;
    }

    public int endColor(ItemStack itemStack) {
        return 0xFF998f7b;
    }

    public int backgroundColor(ItemStack itemStack) {
        return 0xF00f0401;
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(endColor(stack));
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        stack.set(MonsoonDataComponents.REPAIRED, true);

        return super.useOnBlock(context);
    }
}
