package net.kindling.monsoon.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class MonsoonModelGen extends FabricModelProvider {
    public MonsoonModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //
    }

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //
    }
}
