package net.kindling.monsoon.impl.index.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.client.block.render.SwitchBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public interface MonsoonModelLayers {
    EntityModelLayer SWITCH = create("switch");

    private static EntityModelLayer create(String name) {
        return new EntityModelLayer(Monsoon.id(name), "main");
    }

    static void init() {
        EntityModelLayerRegistry.registerModelLayer(SWITCH, SwitchBlockEntityRenderer::getTexturedModelData);
    }
}
