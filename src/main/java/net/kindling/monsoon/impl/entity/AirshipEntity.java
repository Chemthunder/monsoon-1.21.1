package net.kindling.monsoon.impl.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AirshipEntity extends Entity {
    private int crusingY = 100;

    public AirshipEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        //
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        //
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        //
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        player.startRiding(this);
        return super.interact(player, hand);
    }
}
