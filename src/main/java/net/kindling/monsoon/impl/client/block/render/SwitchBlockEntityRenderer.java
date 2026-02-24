package net.kindling.monsoon.impl.client.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kindling.monsoon.impl.block.entity.SwitchBlockEntity;
import net.kindling.monsoon.impl.client.block.animation.SwitchModelAnimations;
import net.kindling.monsoon.impl.index.client.MonsoonModelLayers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SwitchBlockEntityRenderer extends AnimatableBlockEntityRenderer<SwitchBlockEntity> {
    private final Identifier texture;
    private final ModelPart part;

    public SwitchBlockEntityRenderer(Identifier texture, BlockEntityRendererFactory.@NotNull Context ctx) {
        super(RenderLayer::getEntityCutout);
        this.texture = texture;
        this.part = ctx.getLayerModelPart(MonsoonModelLayers.SWITCH);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -12.0F, -8.0F, 6.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData arm = base.addChild("arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.25F, -7.5F));

        ModelPartData handle_r1 = arm.addChild("handle_r1", ModelPartBuilder.create().uv(0, 10).cuboid(-3.0F, -9.25F, -0.5625F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.4375F, -0.3927F, 0.0F, 0.0F));

        ModelPartData pole2_r1 = arm.addChild("pole2_r1", ModelPartBuilder.create().uv(15, 0).cuboid(0.0F, -7.25F, -0.5F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(15, 0).cuboid(4.0F, -7.25F, -0.5F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 16);
    }

    @Override
    public void render(SwitchBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.translate(0.5f, -0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        super.render(entity, tickDelta, matrices, vertexConsumers, light, overlay);
    }

    @Override
    public void setAngles(SwitchBlockEntity entity, float animationProgress) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.part.setAngles(0, entity.getYaw() * MathHelper.RADIANS_PER_DEGREE, 0);
        this.updateAnimation(entity.state, entity.isFlipped() ? SwitchModelAnimations.FLIP_UP : SwitchModelAnimations.FLIP_DOWN, animationProgress);
    }

    @Override
    public Identifier getTexture(SwitchBlockEntity entity, float tickDelta) {
        return this.texture;
    }

    @Override
    public int getAge(SwitchBlockEntity entity) {
        return entity.getAge();
    }

    @Override
    public ModelPart getPart() {
        return this.part;
    }
}
