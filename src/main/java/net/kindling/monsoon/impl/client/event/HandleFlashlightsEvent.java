package net.kindling.monsoon.impl.client.event;

import foundry.veil.api.client.render.MatrixStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.data.AreaLightData;
import foundry.veil.api.event.VeilRenderLevelStageEvent;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class HandleFlashlightsEvent implements VeilRenderLevelStageEvent, ClientPlayConnectionEvents.Disconnect {
    public static Map<UUID, AreaLightData> flashlights = new Object2ObjectOpenHashMap<>();

    public void onRenderLevelStage(Stage stage, WorldRenderer levelRenderer, VertexConsumerProvider.Immediate bufferSource, MatrixStack matrixStack, Matrix4fc frustumMatrix, Matrix4fc projectionMatrix, int renderTick, RenderTickCounter deltaTracker, Camera camera, Frustum frustum) {
        float partialTicks = deltaTracker.getTickDelta(false);

        // Flashlights
        if (stage == Stage.AFTER_LEVEL) {
            for (AbstractClientPlayerEntity player : MinecraftClient.getInstance().world.getPlayers()) {
                if (player != null) {
                    PlayerGameComponent playerComponent = PlayerGameComponent.KEY.get(player);
                    if (playerComponent.isFlashlight()) {
                        Vec3d cameraPosVec = player.getCameraPosVec(partialTicks).subtract(player.getRotationVec(partialTicks).multiply(0.3F));
                        if (!flashlights.containsKey(player.getUuid())) {
                            AreaLightData flashLight = new AreaLightData();
                            flashLight.setBrightness(1.5F);
                            flashLight.setColor(0xDEA97E);
                            flashLight.setDistance(75.0F);
                            flashLight.setAngle(0.7F);
                            flashLight.setSize(0.7F, 0.7F);
                            flashLight.getPosition().set(cameraPosVec.getX(), cameraPosVec.getY(), cameraPosVec.getZ());
                            flashLight.getOrientation().identity().rotateXYZ((float)(-Math.toRadians(player.getPitch(partialTicks))), (float)(Math.toRadians(player.getYaw(partialTicks))), 0.0F);
                            player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.5F, 1.0F);
                            flashlights.put(player.getUuid(), flashLight);
                            VeilRenderSystem.renderer().getLightRenderer().addLight(flashLight);
                        }

                        AreaLightData flashLight = flashlights.get(player.getUuid());
                        Vector3d pos = flashLight.getPosition().lerp(new Vector3d(cameraPosVec.getX(), cameraPosVec.getY(), cameraPosVec.getZ()), 0.1F, new Vector3d());
                        flashLight.getPosition().set(pos);
                        Quaternionf goal = new Quaternionf().rotateXYZ((float)(-Math.toRadians(player.getPitch(partialTicks))), (float)(Math.toRadians(player.getYaw(partialTicks))), 0.0F);
                        flashLight.getOrientation().slerp(goal, 0.1f);
                    }
                }
            }

            ArrayList<UUID> flashlightsToRemove = new ArrayList<>();

            for (UUID uuid : flashlights.keySet()) {
                PlayerEntity playerByUuid = MinecraftClient.getInstance().world.getPlayerByUuid(uuid);
                if (playerByUuid != null && !PlayerGameComponent.KEY.get(playerByUuid).isFlashlight()) {
                    flashlightsToRemove.add(uuid);
                    playerByUuid.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.5F, 1.0F);
                }

                if (playerByUuid == null) {
                    flashlightsToRemove.add(uuid);
                }
            }

            for (UUID uuid : flashlightsToRemove) {
                VeilRenderSystem.renderer().getLightRenderer().getLights(flashlights.get(uuid).getType()).clear();
                flashlights.remove(uuid);
            }
        }
    }

    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        flashlights.clear();
    }
}
