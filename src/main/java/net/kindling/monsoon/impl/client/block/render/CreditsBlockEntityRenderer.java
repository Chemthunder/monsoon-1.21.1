package net.kindling.monsoon.impl.client.block.render;

import net.kindling.monsoon.impl.block.entity.CreditsBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.EnumProperty;

public class CreditsBlockEntityRenderer implements BlockEntityRenderer<CreditsBlockEntity> {

    private static final EnumProperty<CreditsBlockEntity.Authors> AUTHORS = CreditsBlockEntity.AUTHORS;

    public void render(CreditsBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        /* TODO
        Render a floating text stating the author, and their contributions (i.e:
        AcoYT
        Developer, Artist, Conceptor
         */
    }
}
