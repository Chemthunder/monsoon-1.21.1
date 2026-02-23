package net.kindling.monsoon.impl.cca.entity;

import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PlayerGameComponent implements AutoSyncedComponent {
    public static final ComponentKey<PlayerGameComponent> KEY = ComponentRegistry.getOrCreate(Monsoon.id("player"), PlayerGameComponent.class);
    private final PlayerEntity player;

    public boolean isSuited = false;
    public boolean isDead = false;

    public ItemStack heldStack = ItemStack.EMPTY;

    public PlayerGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.isSuited = nbtCompound.getBoolean("isSuited");
        this.isDead = nbtCompound.getBoolean("isDead");

        if (nbtCompound.contains("heldStack", NbtElement.COMPOUND_TYPE)) {
            NbtCompound compound = nbtCompound.getCompound("heldStack");
            this.heldStack = ItemStack.fromNbt(wrapperLookup, compound).orElse(ItemStack.EMPTY);
        } else {
            this.heldStack = ItemStack.EMPTY;
        }
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("isSuited", isSuited);
        nbtCompound.putBoolean("isDead", isDead);

        if (!this.heldStack.isEmpty()) {
            nbtCompound.put("heldStack", this.heldStack.encode(wrapperLookup));
        }
    }

    public void sync() {
        KEY.sync(player);
    }
}
