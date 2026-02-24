package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.block.entity.SwitchBlockEntity;
import net.kindling.monsoon.impl.client.block.render.SwitchBlockEntityRenderer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@SuppressWarnings("deprecation")
public interface MonsoonBlockEntities {
    BlockEntityTypeRegistrant BLOCK_ENTITIES = new BlockEntityTypeRegistrant(Monsoon.MOD_ID);

    BlockEntityType<SwitchBlockEntity> SWITCH = BLOCK_ENTITIES.register(
            "switch",
            FabricBlockEntityTypeBuilder.create(SwitchBlockEntity::new, MonsoonBlocks.SWITCH)
    );

    static void init() {
        //
    }

    static void clientInit() {
        BlockEntityRendererFactories.register(SWITCH, ctx -> new SwitchBlockEntityRenderer(Monsoon.id("textures/entity/switch.png"), ctx));
    }
}
