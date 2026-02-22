package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public interface MonsoonBlocks {
    BlockRegistrant rant = new BlockRegistrant(Monsoon.MOD_ID);

    Block TEST_BLOCK = rant.registerWithItem("test_block", Block::new, AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD));

    static void init() {

    }

    static void clientInit() {

    }
}
