package net.kindling.monsoon.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kindling.monsoon.datagen.data.MonsoonItemTagGen;
import net.kindling.monsoon.datagen.resources.MonsoonLangGen;
import net.kindling.monsoon.datagen.resources.MonsoonModelGen;
import net.kindling.monsoon.impl.index.data.MonsoonDamageTypes;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MonsoonDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        /* Assets */
        pack.addProvider(MonsoonLangGen::new);
        pack.addProvider(MonsoonModelGen::new);

        /* Data */
        pack.addProvider(MonsoonItemTagGen::new);

        /* Other */
        pack.addProvider(MonsoonDynamicRegistryGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, MonsoonDamageTypes::bootstrap);
    }
}
