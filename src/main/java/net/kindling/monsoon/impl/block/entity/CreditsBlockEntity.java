package net.kindling.monsoon.impl.block.entity;

import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;

public class CreditsBlockEntity extends BlockEntity {
    public static Authors current;
    public static final EnumProperty<Authors> AUTHORS = EnumProperty.of("authors", Authors.class);

    public CreditsBlockEntity(BlockPos pos, BlockState state) {
        super(MonsoonBlockEntities.CREDITS, pos, state);
    }

    public static Authors getCurrent() {
        return current;
    }

    public enum Authors implements StringIdentifiable {
        KINDLINGCINDER("KindlingCinder"),
        CHEMTHUNDER("Chemthunder"),
        EEVEREST("eeverest"),
        ACOYT("AcoYT");

        private final String name;

        Authors(final String name) {
            this.name = name;
        }

        public String asString() {
            return name;
        }
    }
}
