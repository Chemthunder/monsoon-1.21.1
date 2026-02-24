package net.kindling.monsoon.impl.block;

import com.mojang.serialization.MapCodec;
import net.kindling.monsoon.impl.block.entity.CreditsBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CreditsBlock extends BlockWithEntity {
    public static final MapCodec<CreditsBlock> CODEC = createCodec(CreditsBlock::new);
    protected MapCodec<? extends BlockWithEntity> getCodec() {return CODEC;}

    public CreditsBlock(Settings settings) {
        super(settings);
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CreditsBlockEntity(pos, state);
    }
}
