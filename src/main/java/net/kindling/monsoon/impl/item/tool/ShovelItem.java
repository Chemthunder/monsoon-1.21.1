package net.kindling.monsoon.impl.item.tool;

import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.index.MonsoonDataComponents;
import net.kindling.monsoon.impl.item.MonsoonToolItem;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ShovelItem extends MonsoonToolItem implements ModelVaryingItem {
    public ShovelItem(Settings settings) {
        super(settings,
                "tooltip.monsoon.shovel.fixed.1",
                "tooltip.monsoon.shovel.broken.1",
                "tooltip.monsoon.shovel.fixed.2",
                "tooltip.monsoon.shovel.broken.2");
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        var fixed = stack.getOrDefault(MonsoonDataComponents.FIXED, false);

        if (fixed == true) {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("shovel") : Monsoon.id("shovel_handheld");
        } else {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("shovel_broken") : Monsoon.id("shovel_handheld_broken");
        }
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Monsoon.id("shovel"),
                Monsoon.id("shovel_handheld"),
                Monsoon.id("shovel_broken"),
                Monsoon.id("shovel_handheld_broken")
        );
    }
}
