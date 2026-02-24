package net.kindling.monsoon.impl.game;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;

public class GameClient {
    public static void startGame(ClientWorld world) {
        enableFog();

        ifDev(() -> Monsoon.LOGGER.info("Game has been initialized [CLIENT]"));
    }

    public static void endGame(ClientWorld world) {
        disableFog();
        FogRenderer.resetFog();

        ifDev(() -> Monsoon.LOGGER.info("Game ended successfully [CLIENT]"));
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
