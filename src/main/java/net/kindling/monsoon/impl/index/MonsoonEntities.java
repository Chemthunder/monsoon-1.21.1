package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.entity.AirshipEntity;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

@SuppressWarnings("all")
public interface MonsoonEntities {
    EntityTypeRegistrant<Entity> rant = new EntityTypeRegistrant<>(Monsoon.MOD_ID);

    EntityType AIRSHIP = rant.register("airship", EntityType.Builder.create(AirshipEntity::new, SpawnGroup.MISC).dimensions(1.0f, 1.0f).build());

    static void init() {
        //
    }

    static void clientInit() {
        EntityRendererRegistry.register(AIRSHIP, EmptyEntityRenderer::new);
    }
}
