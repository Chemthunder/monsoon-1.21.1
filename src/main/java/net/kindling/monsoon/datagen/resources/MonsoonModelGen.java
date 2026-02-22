package net.kindling.monsoon.datagen.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import static net.kindling.monsoon.impl.index.MonsoonItems.TEST_ITEM;

public class MonsoonModelGen extends FabricModelProvider {
    public MonsoonModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        //
    }

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(TEST_ITEM, Models.GENERATED);
    }
}
