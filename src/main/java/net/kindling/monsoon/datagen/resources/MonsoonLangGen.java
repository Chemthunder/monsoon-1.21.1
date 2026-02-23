package net.kindling.monsoon.datagen.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MonsoonLangGen extends FabricLanguageProvider {
    public MonsoonLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        MonsoonItems.rant.registerLang(wrapperLookup, translationBuilder);
        MonsoonBlocks.rant.registerLang(wrapperLookup, translationBuilder);

        // misc
        translationBuilder.add("itemGroup.monsoon", "Monsoon");

        translationBuilder.add("monsoon.misc.itemreadout.empty", "Empty");
        translationBuilder.add("monsoon.misc.itemreadout.filled", "Holding: ");

    }
}
