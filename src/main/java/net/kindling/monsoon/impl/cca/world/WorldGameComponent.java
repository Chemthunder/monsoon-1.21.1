package net.kindling.monsoon.impl.cca.world;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class WorldGameComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<WorldGameComponent> KEY = MiscUtils.getOrCreateKey(Monsoon.id("game"), WorldGameComponent.class);
    private final World world;
    private boolean active = false;

    private int ticks = 0;

    public WorldGameComponent(World world) {
        this.world = world;
    }

    public void sync() {
        KEY.sync(this.world);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.active = nbt.getBoolean("Active");
        this.ticks = nbt.getInt("Ticks");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putBoolean("Active", this.active);
        nbt.putInt("Ticks", this.ticks);
    }

    public void tick() {
        if (this.active) {
            this.ticks++;
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.sync();
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.sync();
    }
}
