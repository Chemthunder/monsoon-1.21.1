package net.kindling.monsoon.impl.game;

import de.keksuccino.melody.resources.audio.openal.ALException;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.index.MonsoonFog;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.kindling.monsoon.impl.client.audio.DynamicTrack;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;
import static net.kindling.monsoon.impl.Monsoon.id;

public class GameClient {
    public static void startGame(ClientWorld world) {
        enableShader("custom_fog");
        enableShader("curvature");
        enableRain();

        ifDev(() -> Monsoon.LOGGER.info("Game has been initialized [CLIENT]"));
    }

    public static void endGame(ClientWorld world) {
        disableShader("custom_fog");
        disableShader("curvature");
        disableRain();

        MonsoonFog.renderer.resetFog();

        ifDev(() -> Monsoon.LOGGER.info("Game ended successfully [CLIENT]"));
    }

    public static void enableRain() {
        Identifier id = id("audio/ambiance/rain.ogg");

        DynamicTrack track = MonsoonClient.AUDIO_ENGINE.get(id);

        if (track != null) {
            try {
                track.play(3f, true);
            } catch (ALException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableRain() {
        Identifier id = id("audio/rain.ogg");

        DynamicTrack track = MonsoonClient.AUDIO_ENGINE.get(id);

        if (track != null) {
            track.stop(3f);
        }
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
