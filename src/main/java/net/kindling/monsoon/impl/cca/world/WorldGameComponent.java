package net.kindling.monsoon.impl.cca.world;

import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class WorldGameComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<WorldGameComponent> KEY = ComponentRegistry.getOrCreate(Monsoon.id("game"), WorldGameComponent.class);
    private final World world;
    public boolean isActive = false;

    public WorldGameComponent(World world) {
        this.world = world;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.isActive = nbtCompound.getBoolean("isActive");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("isActive", isActive);
    }

    public void tick() {
        if (isActive) {

        }
    }
}
