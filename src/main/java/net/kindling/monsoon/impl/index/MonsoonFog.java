package net.kindling.monsoon.impl.index;

import net.kindling.monsoon.impl.fog.FogRenderer;
import net.kindling.monsoon.impl.fog.FogSettings;

public class MonsoonFog {
    private static FogSettings settings;
    public static FogRenderer renderer;

    public static void init() {
        settings = FogSettings.defaultSettings();

        renderer = new FogRenderer(settings);
        renderer.register();
    }

    public static FogSettings getFog() {
        return settings;
    }
}
