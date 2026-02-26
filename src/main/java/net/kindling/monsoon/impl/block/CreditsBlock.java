package net.kindling.monsoon.impl.block;

import com.mojang.serialization.MapCodec;
import net.kindling.monsoon.impl.block.entity.CreditsBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CreditsBlock extends BlockWithEntity {
    public static final MapCodec<CreditsBlock> CODEC = createCodec(CreditsBlock::new);

    public CreditsBlock(Settings settings) {
        super(settings);
    }

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof CreditsBlockEntity creditsBlock) {
                creditsBlock.tick(world1, pos, state1);
            }
        };
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CreditsBlockEntity(pos, state);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof CreditsBlockEntity blockEntity) {
            if (player.isInCreativeMode()) {
                if (player.isSneaking()) {
                    blockEntity.setShouldNotTick(!blockEntity.getShouldNotTick());

                    player.swingHand(player.getActiveHand());
                    player.playSoundToPlayer(SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1, 3f);
                } else {
                    if (blockEntity.getShouldNotTick()) {
                        blockEntity.setAuthor(CreditsBlockEntity.Author.getNext(blockEntity.getAuthor()));
                        player.swingHand(player.getActiveHand());
                    }
                }
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
