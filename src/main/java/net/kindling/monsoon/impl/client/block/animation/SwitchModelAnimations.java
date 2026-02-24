package net.kindling.monsoon.impl.client.block.animation;// Save this class in your mod and generate all required imports

import net.acoyt.acornlib.api.client.Interpolations;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class SwitchModelAnimations {
    public static final Animation FLIP_UP = Animation.Builder.create(1.0f)
            .addBoneAnimation("arm",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.1F, AnimationHelper.createRotationalVector(-90.0F, 0.0F, 0.0F),
                                    Interpolations.EASE_OUT_EXPO),
                            //new Keyframe(0.2083F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F),
                            //        Interpolations.EASE_IN_OUT_CUBIC),
                            new Keyframe(0.7F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.EASE_OUT_EXPO))).build();

    public static final Animation FLIP_DOWN = Animation.Builder.create(1.0f)
            .addBoneAnimation("arm",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.1F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.EASE_OUT_EXPO),
                            //new Keyframe(0.2083F, AnimationHelper.createRotationalVector(-110.0F, 0.0F, 0.0F),
                            //        Interpolations.EASE_IN_OUT_CUBIC),
                            new Keyframe(0.7F, AnimationHelper.createRotationalVector(-90.0F, 0.0F, 0.0F),
                                    Interpolations.EASE_OUT_EXPO))).build();
}
