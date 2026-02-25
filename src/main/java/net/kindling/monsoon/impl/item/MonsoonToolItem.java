package net.kindling.monsoon.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.index.MonsoonDataComponents;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class MonsoonToolItem extends Item implements ColorableItem {
    String fixedKey1;
    String brokenKey1;
    String fixedKey2;
    String brokenKey2;

    public MonsoonToolItem(Settings settings, String fixedKey1, String brokenKey1, String fixedKey2, String brokenKey2) {
        super(settings
                .component(MonsoonDataComponents.FIXED, false));

        this.fixedKey1 = fixedKey1;
        this.brokenKey1 = brokenKey1;
        this.fixedKey2 = fixedKey2;
        this.brokenKey2 = brokenKey2;
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var fixed = stack.getOrDefault(MonsoonDataComponents.FIXED, false);

        if (fixed == true) {
            tooltip.add(Text.translatable(fixedKey1).withColor(startColor(stack)));
            tooltip.add(Text.translatable(fixedKey2).withColor(startColor(stack)));
        } else {
            tooltip.add(Text.translatable(brokenKey1).withColor(startColor(stack)));
            tooltip.add(Text.translatable(brokenKey2).withColor(startColor(stack)));
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

        var component = MonsoonDataComponents.FIXED;

        stack.set(component, true);
        return super.useOnBlock(context);
    }
}
