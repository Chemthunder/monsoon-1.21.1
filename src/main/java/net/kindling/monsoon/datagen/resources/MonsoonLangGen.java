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

        // item
        translationBuilder.add("tooltip.monsoon.crisp", "An odd metal that's kind of like gold, a great currency!");

        translationBuilder.add("tooltip.monsoon.pickaxe.fixed.1", "An old yet trusty tool gathering rocks and minerals.");
        translationBuilder.add("tooltip.monsoon.pickaxe.fixed.2", " It's age is apparent.");

        translationBuilder.add("tooltip.monsoon.pickaxe.broken.1", "A... broken tool that used to be great for gathering rocks and minerals,");
        translationBuilder.add("tooltip.monsoon.pickaxe.broken.2", "it can no longer do so...");

        translationBuilder.add("tooltip.monsoon.shovel.fixed.1", "An tool used primarily for excavating dirt!");
        translationBuilder.add("tooltip.monsoon.shovel.fixed.2", "That doesn't mean it can't be used for more... nefarious things.");

        translationBuilder.add("tooltip.monsoon.shovel.broken.1", "A... broken tool that could have been used for excavating.");
        translationBuilder.add("tooltip.monsoon.shovel.broken.2", "It can't even be used for nefarious uses at this state.");

        translationBuilder.add("tooltip.monsoon.axe.fixed.1", "A great tool to gather wood and heads-");
        translationBuilder.add("tooltip.monsoon.axe.fixed.2", "I MEAN peacefully resolve conflict!");

        translationBuilder.add("tooltip.monsoon.axe.broken.1", "A... broken tool that could have been used for resolving conflict bitterly or gather wood,");
        translationBuilder.add("tooltip.monsoon.axe.broken.2", "now all it can do is waste space...");

        // Misc
        translationBuilder.add("itemGroup.monsoon", "Monsoon");
        translationBuilder.add("monsoon.misc.itemReadout", "Holding: %s");

        translationBuilder.add(KeyInputHandler.KEY_CATEGORY_MONSOON, "Monsoon");
        translationBuilder.add(KeyInputHandler.KEY_FLASHLIGHT_TOGGLE, "Toggle Flashlight");

        // game
        translationBuilder.add("monsoon.game.begin", "The game is starting!");
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
