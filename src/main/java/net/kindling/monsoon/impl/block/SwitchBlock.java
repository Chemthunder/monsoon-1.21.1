package net.kindling.monsoon.impl.block;

import com.mojang.serialization.MapCodec;
import net.kindling.monsoon.impl.block.entity.SwitchBlockEntity;
import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.kindling.monsoon.impl.index.MonsoonSoundEvents;
import net.kindling.monsoon.impl.util.ModUtils;
import net.kindling.monsoon.impl.util.WorldBroadcastManager;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SwitchBlock extends BlockWithEntity implements Waterloggable {
    public static final MapCodec<SwitchBlock> CODEC = createCodec(SwitchBlock::new);

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public SwitchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape north = createCuboidShape(15.0F, 4.0F, 5.0F, 16.0F, 13.0F, 11.0F);
        return switch (state.get(FACING)) {
            case SOUTH -> ModUtils.rotate(north, Direction.EAST);
            case EAST -> ModUtils.rotate(north, Direction.SOUTH);
            case WEST -> ModUtils.rotate(north, Direction.NORTH);
            default -> ModUtils.rotate(north, Direction.WEST);
        };
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Direction.Type.HORIZONTAL.stream().toList().contains(ctx.getSide()) ? this.getDefaultState().with(FACING, ctx.getSide()) : null;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof SwitchBlockEntity switchBlock) {
            switchBlock.flip();

            if (switchBlock.isFlipped()) {
                if (world instanceof ServerWorld serverWorld) {
                    world.playSound(null, pos, MonsoonSoundEvents.SWITCH_2, SoundCategory.BLOCKS, 1, 1);
                    WorldBroadcastManager.playScreenshakeGlobally(serverWorld, 0.5f, 10);
                }
            }
            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? validateTicker(type, MonsoonBlockEntities.SWITCH, SwitchBlockEntity::clientTick) : null;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SwitchBlockEntity(pos, state);
    }

    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1f;
    }
}
