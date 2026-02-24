package net.kindling.monsoon.impl.fog;

import org.joml.Vector3f;

public class FogSettings {

    private float fogStart;
    private float fogEnd;
    private float fogThickness;
    private float heightFalloff;
    private float chaos;
    private final Vector3f fogColor = new Vector3f(0.78F, 0.84F, 0.92F);

    private float targetFogStart;
    private float targetFogEnd;
    private float targetFogThickness;
    private float targetHeightFalloff;
    private float targetChaos;
    private final Vector3f targetFogColor = new Vector3f(0.78F, 0.84F, 0.92F);

    private float lerpSpeed = 2.0F;

    public FogSettings(float fogStart, float fogEnd) {
        this.fogStart = fogStart;
        this.fogEnd = fogEnd;
        this.targetFogStart = fogStart;
        this.targetFogEnd = fogEnd;
    }

    public void tick(float deltaTime) {
        float t = Math.min(1.0F, lerpSpeed * deltaTime);
        fogStart     = lerp(fogStart,     targetFogStart,     t);
        fogEnd       = lerp(fogEnd,       targetFogEnd,       t);
        fogThickness = lerp(fogThickness, targetFogThickness, t);
        heightFalloff= lerp(heightFalloff,targetHeightFalloff,t);
        chaos        = lerp(chaos,        targetChaos,        t);
        fogColor.lerp(targetFogColor, t);
    }

    private float lerp(float a, float b, float t) { return a + (b - a) * t; }

    public FogSettings setFogStart(float v)          { targetFogStart = v;      return this; }
    public FogSettings setFogEnd(float v)            { targetFogEnd = v;        return this; }
    public FogSettings setFogThickness(float v)      { targetFogThickness = v;  return this; }
    public FogSettings setHeightFalloff(float v)     { targetHeightFalloff = v; return this; }
    public FogSettings setChaos(float v)             { targetChaos = v;         return this; }
    public FogSettings setFogColor(float r, float g, float b) { targetFogColor.set(r, g, b); return this; }
    public FogSettings setLerpSpeed(float v)         { lerpSpeed = v;           return this; }

    public FogSettings snapToTargets() {
        fogStart      = targetFogStart;
        fogEnd        = targetFogEnd;
        fogThickness  = targetFogThickness;
        heightFalloff = targetHeightFalloff;
        chaos         = targetChaos;
        fogColor.set(targetFogColor);
        return this;
    }

    public float getFogStart()      { return fogStart; }
    public float getFogEnd()        { return fogEnd; }
    public float getFogThickness()  { return fogThickness; }
    public float getHeightFalloff() { return heightFalloff; }
    public float getChaos()         { return chaos; }
    public Vector3f getFogColor()   { return fogColor; }
}