package net.kindling.monsoon.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.kindling.monsoon.api.item.SpecialItem;
import net.kindling.monsoon.impl.cca.entity.CurrencyGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class CrispItem extends SpecialItem implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFae6144;}
    public int endColor(ItemStack itemStack) {return 0xFFf4b04f;}
    public int backgroundColor(ItemStack itemStack) {return 0xF022130f;}

    public CrispItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(endColor(stack));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        CurrencyGameComponent currencyGameComponent = CurrencyGameComponent.KEY.get(user);
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            currencyGameComponent.currency ++;
            currencyGameComponent.sync();

            stack.split(1);

            user.swingHand(hand);
        }

        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.monsoon.crisp").withColor(startColor(stack)));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
