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

public class AxeItem extends MonsoonToolItem implements ModelVaryingItem {
    public AxeItem(Settings settings) {
        super(settings,
                "tooltip.monsoon.axe.fixed.",
                "tooltip.monsoon.axe.broken."
        );
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.getOrDefault(MonsoonDataComponents.REPAIRED, false)) {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("axe") : Monsoon.id("axe_in_hand");
        } else {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("axe_broken") : Monsoon.id("axe_broken_in_hand");
        }
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Monsoon.id("axe"),
                Monsoon.id("axe_in_hand"),
                Monsoon.id("axe_broken"),
                Monsoon.id("axe_broken_in_hand")
        );
    }
}
