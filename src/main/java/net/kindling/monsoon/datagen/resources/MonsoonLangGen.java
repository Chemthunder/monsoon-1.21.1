package net.kindling.monsoon.datagen.resources;

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.kindling.monsoon.impl.event.KeyInputHandler;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.kindling.monsoon.impl.index.MonsoonSoundEvents;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MonsoonLangGen extends FabricLanguageProvider {
    public MonsoonLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        MonsoonItems.ITEMS.registerLang(wrapperLookup, translationBuilder);
        MonsoonBlocks.BLOCKS.registerLang(wrapperLookup, translationBuilder);
        MonsoonSoundEvents.SOUND_EVENTS.registerLang(wrapperLookup, translationBuilder);

        // Misc
        translationBuilder.add("itemGroup.monsoon", "Monsoon");
        translationBuilder.add("monsoon.misc.itemReadout", "Holding: %s");

        translationBuilder.add(KeyInputHandler.KEY_CATEGORY_MONSOON, "Monsoon");
        translationBuilder.add(KeyInputHandler.KEY_FLASHLIGHT_TOGGLE, "Toggle Flashlight");
    }

    // MidnightConfig
    public void registerConfig(TranslationBuilder builder, String key, String name, @Nullable String tooltip) {
        key = key.transform(string -> AcornLib.MOD_ID + ".midnightconfig." + string);
        builder.add(key, name);
        if (tooltip != null && !tooltip.isEmpty()) builder.add(key + ".tooltip", tooltip);
    }

    public void registerCategory(TranslationBuilder builder, String key, String name) {
        key = key.transform(string -> AcornLib.MOD_ID + ".midnightconfig.category." + string);
        builder.add(key, name);
    }
}
