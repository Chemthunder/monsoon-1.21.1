package net.kindling.monsoon.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class CurrencyGameComponent implements AutoSyncedComponent {
    public static final ComponentKey<CurrencyGameComponent> KEY = MiscUtils.getOrCreateKey(Monsoon.id("currency"), CurrencyGameComponent.class);
    private final PlayerEntity player;
    private int currency;

    public CurrencyGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.currency = nbt.getInt("Currency");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putInt("Currency", currency);
    }

    public void sync() {
        KEY.sync(player);
    }

    public int getCurrency() {
        return this.currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
        this.sync();
    }
}
