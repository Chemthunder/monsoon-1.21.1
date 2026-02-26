package net.kindling.monsoon.impl.client.block.render;

import net.kindling.monsoon.impl.block.entity.CreditsBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CreditsBlockEntityRenderer implements BlockEntityRenderer<CreditsBlockEntity> {
    private final TextRenderer textRenderer;

    public CreditsBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context ctx) {
        this.textRenderer = ctx.getTextRenderer();
    }

    public void render(CreditsBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Text name = Text.literal(entity.getAuthor().getName());
        Text desc = Text.literal(entity.getAuthor().getDesc());
        float nameWidth = this.textRenderer.getWidth(name);
        float descWidth = this.textRenderer.getWidth(desc);

        float opacity = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
        int color = (int)(opacity * 255.0F) << 24;

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        if (camera == null) return;

        Vector3f cameraPos = camera.getPos().toVector3f();

        float dx = (float)entity.getPos().toCenterPos().x + 0.5F - cameraPos.x();
        float dy = (float)entity.getPos().toCenterPos().y + 2.6F - cameraPos.y();
        float dz = (float)entity.getPos().toCenterPos().z + 0.5F - cameraPos.z();
        float distance = (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
        if (distance > 12.0F) return;

        matrices.push();

        matrices.translate(dx, dy, dz);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));

        //matrices.translate(0.32, 1.6F, 0.16F);
        matrices.scale(-0.042F, -0.042F, -0.042F);
        //matrices.translate(16.0F - nameWidth, 0.0F, 0.0F);

        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        VertexConsumer layer1 = vertexConsumers.getBuffer(RenderLayer.getTextBackground());
        layer1.vertex(matrix4f, -nameWidth / 1.8F, -1.0F, 0.0F).color(color).light(light);
        layer1.vertex(matrix4f, -nameWidth / 1.8F, 10.0F, 0.0F).color(color).light(light);
        layer1.vertex(matrix4f, nameWidth / 1.8F, 10.0F, 0.0F).color(color).light(light);
        layer1.vertex(matrix4f, nameWidth / 1.8F, -1.0F, 0.0F).color(color).light(light);

        //matrices.translate(0.0F, -10.0F, 0.0F);

        float xOffset = -nameWidth / 2.0f;

        this.textRenderer.draw(
                name,
                xOffset, 0,
                0xFFFFFF,
                true,
                matrix4f,
                vertexConsumers,
                TextRenderer.TextLayerType.POLYGON_OFFSET,
                0,
                0xFFFFFF
        );

        matrices.pop();

        matrices.push();

        matrices.translate(dx, dy - 0.5F, dz);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));

        //matrices.translate(0.32, 1.6F, 0.16F);
        matrices.scale(-0.042F, -0.042F, -0.042F);
        //matrices.translate(16.0F - descWidth, 0.0F, 0.0F);

        Matrix4f matrix4f2 = matrices.peek().getPositionMatrix();

        VertexConsumer layer2 = vertexConsumers.getBuffer(RenderLayer.getTextBackground());
        layer2.vertex(matrix4f2, -descWidth / 1.8F, -1.0F, 0.0F).color(color).light(light);
        layer2.vertex(matrix4f2, -descWidth / 1.8F, 10.0F, 0.0F).color(color).light(light);
        layer2.vertex(matrix4f2, descWidth / 1.8F, 10.0F, 0.0F).color(color).light(light);
        layer2.vertex(matrix4f2, descWidth / 1.8F, -1.0F, 0.0F).color(color).light(light);

        //matrices.translate(0.0F, -10.0F, 0.0F);

        float xOffset2 = -descWidth / 2.0f;

        this.textRenderer.draw(
                desc,
                xOffset2, 0,
                0xFFFFFF,
                true,
                matrix4f2,
                vertexConsumers,
                TextRenderer.TextLayerType.POLYGON_OFFSET,
                0,
                0xFFFFFF
        );

        matrices.pop();
    }
}
