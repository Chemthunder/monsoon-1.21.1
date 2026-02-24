package net.kindling.monsoon.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PlayerGameComponent implements AutoSyncedComponent {
    public static final ComponentKey<PlayerGameComponent> KEY = MiscUtils.getOrCreateKey(Monsoon.id("player"), PlayerGameComponent.class);
    private final PlayerEntity player;

    private boolean suited = false;
    private boolean dead = false;

    private ItemStack heldStack = ItemStack.EMPTY;

    public PlayerGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.suited = nbt.getBoolean("Suited");
        this.dead = nbt.getBoolean("Dead");

        if (nbt.contains("HeldStack", NbtElement.COMPOUND_TYPE)) {
            NbtCompound compound = nbt.getCompound("HeldStack");
            this.heldStack = ItemStack.fromNbt(wrapperLookup, compound).orElse(ItemStack.EMPTY);
        } else {
            this.heldStack = ItemStack.EMPTY;
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("Suited", this.suited);
        nbt.putBoolean("Dead", this.dead);

        if (!this.heldStack.isEmpty()) {
            nbt.put("HeldStack", this.heldStack.encode(wrapperLookup));
        }
    }

    public boolean isSuited() {
        return this.suited;
    }

    public void setSuited(boolean suited) {
        this.suited = suited;
        this.sync();
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
        this.sync();
    }

    public ItemStack getHeldStack() {
        return this.heldStack;
    }

    public void setHeldStack(ItemStack heldStack) {
        this.heldStack = heldStack;
        this.sync();
    }
}
