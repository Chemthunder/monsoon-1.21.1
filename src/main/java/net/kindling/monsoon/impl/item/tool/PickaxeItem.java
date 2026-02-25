package net.kindling.monsoon.impl.item.tool;

import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.index.MonsoonDataComponents;
import net.kindling.monsoon.impl.item.MonsoonToolItem;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class PickaxeItem extends MonsoonToolItem implements ModelVaryingItem {
    public PickaxeItem(Settings settings) {
        super(settings,
                "tooltip.monsoon.pickaxe.fixed.1",
                "tooltip.monsoon.pickaxe.broken.1",
                "tooltip.monsoon.pickaxe.fixed.2",
                "tooltip.monsoon.pickaxe.broken.2");
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        var fixed = stack.getOrDefault(MonsoonDataComponents.FIXED, false);

        if (fixed == true) {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("pickaxe") : Monsoon.id("pickaxe_handheld");
        } else {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("pickaxe_broken") : Monsoon.id("pickaxe_handheld_broken");
        }
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Monsoon.id("pickaxe"),
                Monsoon.id("pickaxe_handheld"),
                Monsoon.id("pickaxe_broken"),
                Monsoon.id("pickaxe_handheld_broken")
        );
    }
}
