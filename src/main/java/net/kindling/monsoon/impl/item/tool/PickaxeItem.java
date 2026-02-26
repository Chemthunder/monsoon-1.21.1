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

public class PickaxeItem extends MonsoonToolItem implements ModelVaryingItem {
    public PickaxeItem(Settings settings) {
        super(settings,
                "tooltip.monsoon.pickaxe.fixed",
                "tooltip.monsoon.pickaxe.broken"
        );
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        var fixed = stack.getOrDefault(MonsoonDataComponents.REPAIRED, false);

        if (fixed == true) {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("pickaxe") : Monsoon.id("pickaxe_in_hand");
        } else {
            return MiscUtils.isGui(renderMode) ? Monsoon.id("pickaxe_broken") : Monsoon.id("pickaxe_broken_in_hand");
        }
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Monsoon.id("pickaxe"),
                Monsoon.id("pickaxe_in_hand"),
                Monsoon.id("pickaxe_broken"),
                Monsoon.id("pickaxe_broken_in_hand")
        );
    }
}
