package net.kindling.monsoon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MonsoonDynamicRegistryGen extends FabricDynamicRegistryProvider {
    public MonsoonDynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE));
    }

    public String getName() {
        return Monsoon.MOD_ID + "_dynamic";
    }
}
