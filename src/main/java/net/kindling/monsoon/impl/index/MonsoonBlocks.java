package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.block.SwitchBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public interface MonsoonBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Monsoon.MOD_ID);

    Block SWITCH = BLOCKS.registerWithItem("switch", SwitchBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BARS));

    Block TEST_BLOCK = BLOCKS.registerWithItem("test_block", Block::new, AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD));

    static void init() {
        //
    }

    static void clientInit() {
        //
    }
}
