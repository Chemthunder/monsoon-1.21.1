package net.kindling.monsoon.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.kindling.monsoon.impl.index.tag.MonsoonItemTags.RESOURCE_ITEMS;
import static net.kindling.monsoon.impl.index.tag.MonsoonItemTags.SPECIAL_ITEMS;

public class MonsoonItemTagGen extends FabricTagProvider.ItemTagProvider {
    public MonsoonItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(SPECIAL_ITEMS)
                .add(MonsoonItems.FLASHLIGHT)
                .setReplace(false);

        this.getOrCreateTagBuilder(RESOURCE_ITEMS).setReplace(false);
    }
}
