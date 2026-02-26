package net.kindling.monsoon.impl.game;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.index.MonsoonFog;
import net.minecraft.client.world.ClientWorld;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;
import static net.kindling.monsoon.impl.Monsoon.id;

public class GameClient {
    public static void startGame(ClientWorld world) {
        enableShader("custom_fog");
        enableShader("curvature");

        ifDev(() -> Monsoon.LOGGER.info("Game has been initialized [CLIENT]"));
    }

    public static void endGame(ClientWorld world) {
        disableShader("custom_fog");
        disableShader("curvature");

        MonsoonFog.renderer.resetFog();

        ifDev(() -> Monsoon.LOGGER.info("Game ended successfully [CLIENT]"));
    }

    public static void enableShader(String shader) {
        try {
            PostProcessingManager postProcessingManager = VeilRenderSystem.renderer().getPostProcessingManager();
            PostPipeline postPipeline = postProcessingManager.getPipeline(id(shader));

            assert postPipeline != null;
            postProcessingManager.add(id(shader));
            postProcessingManager.runPipeline(postPipeline);


        } catch (Exception ignored) {}
    }

    public static void disableShader(String shader) {
        try {
            PostProcessingManager postProcessingManager = VeilRenderSystem.renderer().getPostProcessingManager();
            PostPipeline postPipeline = postProcessingManager.getPipeline(id(shader));

            assert postPipeline != null;
            postProcessingManager.remove(id(shader));
        } catch (Exception ignored) {}
    }
}
