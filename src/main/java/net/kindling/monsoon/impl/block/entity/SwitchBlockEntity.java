package net.kindling.monsoon.impl.block.entity;

import net.kindling.monsoon.impl.block.SwitchBlock;
import net.kindling.monsoon.impl.game.Game;
import net.kindling.monsoon.impl.game.GameClient;
import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SwitchBlockEntity extends BlockEntity {
    public AnimationState state = new AnimationState();
    private boolean flipped = false;
    protected int age = 0;
    protected long lastUpdate = 0L;

    public SwitchBlockEntity(BlockPos pos, BlockState state) {
        super(MonsoonBlockEntities.SWITCH, pos, state);
        this.state.start(this.age);
        this.state.skip(10, 1);
    }

    public void updateListeners() {
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, SwitchBlockEntity entity) {
        entity.age++;
    }

    public void flip() {
        if (this.world == null || this.world.getTime() == this.lastUpdate) return;

        this.lastUpdate = this.world.getTime();
        this.flipped = !this.flipped;
        this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), Block.NOTIFY_NEIGHBORS, this.flipped ? 1 : 0);
        this.updateListeners();

        if (this.world.isClient) {
            if (this.flipped) {
                GameClient.startGame((ClientWorld) this.world);
            } else {
                GameClient.endGame((ClientWorld) this.world);
            }
        }

        if (this.flipped) {
            Game.startGame(this.world);
        } else {
            Game.endGame(this.world);
        }
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (this.world != null && type == 1) {
            this.state.start(this.age);
            this.flipped = data != 0;
            return true;
        }

        return super.onSyncedBlockEvent(type, data);
    }

    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.flipped = nbt.getBoolean("Flipped");
    }

    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        nbt.putBoolean("Flipped", this.flipped);
    }

    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    public float getYaw() {
        return 180 - this.getFacing().asRotation();
    }

    public Direction getFacing() {
        return this.getCachedState().get(SwitchBlock.FACING);
    }

    public int getAge() {
        return this.age;
    }
}
