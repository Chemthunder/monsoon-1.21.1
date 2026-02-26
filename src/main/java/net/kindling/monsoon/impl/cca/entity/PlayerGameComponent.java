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

    private boolean suited = false; // Whether the player currently has a suit on or not.
    private boolean dead = false; // Whether the player is dead or not.
    private boolean isFlashlightActive = false; // Wehter the flashlight is active or not.

    private ItemStack heldStack = ItemStack.EMPTY; // Currently held item, may remove.

    public PlayerGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.suited = nbt.getBoolean("Suited");
        this.dead = nbt.getBoolean("Dead");
        this.isFlashlightActive = nbt.getBoolean("IsFlashlightActive");

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
        nbt.putBoolean("IsFlashlightActive", this.isFlashlightActive);

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

    public boolean getFlashlightActivity() {
        return this.isFlashlightActive;
    }

    public void setFlashlightActivity(boolean value) {
        this.isFlashlightActive = value;
        this.sync();
    }
}
