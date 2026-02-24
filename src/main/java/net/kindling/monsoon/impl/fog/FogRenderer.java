package net.kindling.monsoon.impl.fog;

import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.shader.uniform.ShaderUniformAccess;
import foundry.veil.platform.VeilEventPlatform;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class FogRenderer {

    private static final Identifier FOG_PIPELINE =
            Identifier.of("monsoon", "custom_fog");

    private final FogSettings settings;
    private static long lastTime = -1;

    private PostPipeline cachedPipeline = null;
    private ShaderUniformAccess uFogStart;
    private ShaderUniformAccess uFogEnd;
    private ShaderUniformAccess uFogThickness;
    private ShaderUniformAccess uHeightFalloff;
    private ShaderUniformAccess uChaos;
    private ShaderUniformAccess uCameraY;
    private ShaderUniformAccess uFogColor;

    public FogRenderer(FogSettings settings) {
        this.settings = settings;
    }

    public void register() {
        VeilEventPlatform.INSTANCE.preVeilPostProcessing((pipelineName, pipeline, context) -> {
            if (!FOG_PIPELINE.equals(pipelineName)) return;

            long now = System.nanoTime();
            float deltaTime = lastTime == -1 ? 0 : (now - lastTime) / 1_000_000_000F;
            lastTime = now;
            settings.tick(deltaTime);

            if (pipeline != cachedPipeline) {
                cachedPipeline = pipeline;
                uFogStart = pipeline.getUniform("uFogStart");
                uFogEnd = pipeline.getUniform("uFogEnd");
                uFogThickness = pipeline.getUniform("uFogThickness");
                uHeightFalloff = pipeline.getUniform("uHeightFalloff");
                uChaos = pipeline.getUniform("uChaos");
                uCameraY = pipeline.getUniform("uCameraY");
                uFogColor = pipeline.getUniform("uFogColor");
            }

            if (uFogStart != null) uFogStart.setFloat(settings.getFogStart());
            if (uFogEnd != null) uFogEnd.setFloat(settings.getFogEnd());
            if (uFogThickness != null) uFogThickness.setFloat(settings.getFogThickness());
            if (uHeightFalloff != null) uHeightFalloff.setFloat(settings.getHeightFalloff());
            if (uChaos != null) uChaos.setFloat(settings.getChaos());

            if (uCameraY != null) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    uCameraY.setFloat((float) mc.player.getEyeY());
                }
            }

            if (uFogColor != null) uFogColor.setVector(
                    settings.getFogColor().x,
                    settings.getFogColor().y,
                    settings.getFogColor().z
            );
        });
    }

    public static void resetFog() {
        lastTime = -1;
    }
}