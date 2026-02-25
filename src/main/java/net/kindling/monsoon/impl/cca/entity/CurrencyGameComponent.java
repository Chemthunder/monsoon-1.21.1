package net.kindling.monsoon.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import javax.swing.*;

public class CurrencyGameComponent implements AutoSyncedComponent {
    public static final ComponentKey<CurrencyGameComponent> KEY = MiscUtils.getOrCreateKey(Monsoon.id("currency"), CurrencyGameComponent.class);

    public int currency;
    private final PlayerEntity player;

    public CurrencyGameComponent(PlayerEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.currency = nbtCompound.getInt("currency");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("currency", currency);
    }

    public void sync() {
        KEY.sync(player);
    }

    public int getCurrency() {
        return currency;
    }
}
