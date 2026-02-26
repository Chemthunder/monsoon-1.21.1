package net.kindling.monsoon.datagen.resources;

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.kindling.monsoon.impl.event.KeyInputHandler;
import net.kindling.monsoon.impl.index.MonsoonBlocks;
import net.kindling.monsoon.impl.index.MonsoonItemGroups;
import net.kindling.monsoon.impl.index.MonsoonItems;
import net.kindling.monsoon.impl.index.MonsoonSoundEvents;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MonsoonLangGen extends FabricLanguageProvider {
    public MonsoonLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        MonsoonItems.ITEMS.registerLang(registries, builder);
        MonsoonBlocks.BLOCKS.registerLang(registries, builder);
        MonsoonSoundEvents.SOUND_EVENTS.registerLang(registries, builder);
        //MonsoonItemGroups.ITEM_GROUPS.registerLang(registries, builder);

        // Item Groups
        builder.add("itemGroup.monsoon", "Monsoon");

        // Items
        builder.add("tooltip.monsoon.crisp", "An odd metal that's kind of like gold, a great currency!");

        builder.add("tooltip.monsoon.pickaxe.fixed", "An old yet trusty tool gathering rocks and minerals. \n It's age is apparent.");
        //builder.add("tooltip.monsoon.pickaxe.fixed.1", "An old yet trusty tool gathering rocks and minerals.");
        //builder.add("tooltip.monsoon.pickaxe.fixed.2", " It's age is apparent.");

        builder.add("tooltip.monsoon.pickaxe.broken", "A... broken tool that used to be great for gathering rocks and minerals, \n it can no longer do so...");
        //builder.add("tooltip.monsoon.pickaxe.broken.1", "A... broken tool that used to be great for gathering rocks and minerals,");
        //builder.add("tooltip.monsoon.pickaxe.broken.2", "it can no longer do so...");

        builder.add("tooltip.monsoon.shovel.fixed", "An tool used primarily for excavating dirt! \n That doesn't mean it can't be used for more... nefarious things.");
        //builder.add("tooltip.monsoon.shovel.fixed.1", "An tool used primarily for excavating dirt!");
        //builder.add("tooltip.monsoon.shovel.fixed.2", "That doesn't mean it can't be used for more... nefarious things.");

        builder.add("tooltip.monsoon.shovel.broken", "A... broken tool that could have been used for excavating. \n It can't even be used for nefarious uses at this state.");
        //builder.add("tooltip.monsoon.shovel.broken.1", "A... broken tool that could have been used for excavating.");
        //builder.add("tooltip.monsoon.shovel.broken.2", "It can't even be used for nefarious uses at this state.");

        builder.add("tooltip.monsoon.axe.fixed", "A great tool to gather wood and heads- \n I MEAN peacefully resolve conflict!");
        //builder.add("tooltip.monsoon.axe.fixed.1", "A great tool to gather wood and heads-");
        //builder.add("tooltip.monsoon.axe.fixed.2", "I MEAN peacefully resolve conflict!");

        builder.add("tooltip.monsoon.axe.broken", "A... broken tool that could have been used for resolving conflict bitterly or gather wood, \n now all it can do is waste space...");
        //builder.add("tooltip.monsoon.axe.broken.1", "A... broken tool that could have been used for resolving conflict bitterly or gather wood,");
        //builder.add("tooltip.monsoon.axe.broken.2", "now all it can do is waste space...");

        // Misc
        builder.add("monsoon.misc.itemReadout", "Holding: %s");

        builder.add(KeyInputHandler.KEY_CATEGORY_MONSOON, "Monsoon");
        builder.add(KeyInputHandler.KEY_FLASHLIGHT_TOGGLE, "Toggle Flashlight");

        // Game
        builder.add("monsoon.game.begin", "The game is starting!");

        // Sounds
        builder.add("subtitles.monsoon.item.crisp_use", "Crisp is added to balance");

        builder.add("subtitles.monsoon.block.switch", "Switch is flipped");
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
