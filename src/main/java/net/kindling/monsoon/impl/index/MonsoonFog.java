package net.kindling.monsoon.impl.index;

import net.kindling.monsoon.impl.fog.FogRenderer;
import net.kindling.monsoon.impl.fog.FogSettings;

public class MonsoonFog {
    private static FogSettings settings;
    private static FogRenderer renderer;

    public static void init() {
        settings = new FogSettings(48F, 96F)
                .setFogStart(8.0F)
                .setFogEnd(30.0F)
                .setFogThickness(1.0F)
                .setHeightFalloff(0.0F)
                .setChaos(0.3F)
                .setFogColor(0.6F, 0.1F, 0.1F);

        renderer = new FogRenderer(settings);
        renderer.register();
    }

    public static FogSettings getFog() {
        return settings;
    }
}
