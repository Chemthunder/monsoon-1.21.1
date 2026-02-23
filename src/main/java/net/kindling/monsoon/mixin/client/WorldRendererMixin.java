package net.kindling.monsoon.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BackgroundRenderer.class)
public class WorldRendererMixin {
    @WrapMethod(method = "applyFog")
}
