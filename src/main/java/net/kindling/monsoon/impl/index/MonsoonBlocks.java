package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.block.CreditsBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public interface MonsoonBlocks {
    BlockRegistrant rant = new BlockRegistrant(Monsoon.MOD_ID);

    Block TEST_BLOCK = rant.registerWithItem("test_block", Block::new, AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD));
    Block CREDITS_BLOCK = rant.registerWithItem("credits_block", CreditsBlock::new, AbstractBlock.Settings.copy(Blocks.BEDROCK).sounds(BlockSoundGroup.INTENTIONALLY_EMPTY).noBlockBreakParticles());


    static void init() {
        //
    }

    static void clientInit() {
        //
    }
}
