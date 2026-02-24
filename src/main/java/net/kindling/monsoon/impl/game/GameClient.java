package net.kindling.monsoon.impl.game;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.kindling.monsoon.impl.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;

public class GameClient {
    public static void init(ClientWorld world) {
        Monsoon.LOGGER.info("Client-Side has been initialized");

        enableFog();
    }

    public static void forceEndClient(ClientWorld world) {
        disableFog();
        FogRenderer.resetFog();

        Monsoon.LOGGER.info("game ended successfully");
    }

    public static void enableFog() {
        try {
            PostProcessingManager postProcessingManager = VeilRenderSystem.renderer().getPostProcessingManager();
            PostPipeline postPipeline = postProcessingManager.getPipeline(Monsoon.id("custom_fog"));

            assert postPipeline != null;
            postProcessingManager.add(Monsoon.id("custom_fog"));
            postProcessingManager.runPipeline(postPipeline);


        } catch (Exception ignored) {}
    }

    public static void disableFog() {
        try {
            PostProcessingManager postProcessingManager = VeilRenderSystem.renderer().getPostProcessingManager();
            PostPipeline postPipeline = postProcessingManager.getPipeline(Monsoon.id("custom_fog"));

            assert postPipeline != null;
            postProcessingManager.remove(Monsoon.id("custom_fog"));
        } catch (Exception ignored) {}
    }
}
