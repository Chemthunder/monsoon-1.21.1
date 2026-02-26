package net.kindling.monsoon.impl.game.util;

import net.minecraft.util.StringIdentifiable;

public enum GameDifficulties implements StringIdentifiable {
    DRIZZLE("drizzle"), // easy
    DOWNPOUR("downpour"), // medium
    MONSOON("monsoon"), // hard
    TYPOON("typoon"); // secret

    private final String id;

    GameDifficulties(final String id) {
        this.id = id;
    }

    public String asString() {
        return this.id;
    }
}
