package net.kindling.monsoon.impl.cca.entity;

import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PlayerGameComponent implements AutoSyncedComponent {
    public static final ComponentKey<PlayerGameComponent> KEY = ComponentRegistry.getOrCreate(Monsoon.id("player"), PlayerGameComponent.class);
    private final PlayerEntity player;

    public boolean isSuited = false;
    public boolean isDead = false;

    public PlayerGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.isSuited = nbtCompound.getBoolean("isSuited");
        this.isDead = nbtCompound.getBoolean("isDead");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("isSuited", isSuited);
        nbtCompound.putBoolean("isDead", isDead);
    }

    public void sync() {
        KEY.sync(player);
    }
}
