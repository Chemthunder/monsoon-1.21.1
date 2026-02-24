package net.kindling.monsoon.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @WrapMethod(method = "applyFog")
    private static void override(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, Operation<Void> original) {}
}
