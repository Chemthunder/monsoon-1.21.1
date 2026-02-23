#include veil:space_helper

uniform sampler2D DiffuseSampler0;
uniform sampler2D DiffuseDepthSampler;

uniform float uFogStart;
uniform float uFogEnd;
uniform float uFogThickness;
uniform float uHeightFalloff;
uniform float uChaos;
uniform float uCameraY;
uniform float VeilRenderTime;
uniform vec3  uFogColor;

in vec2 texCoord;
out vec4 fragColor;

float hash(vec3 p) {
    p = fract(p * 0.3183099 + 0.1);
    p *= 17.0;
    return fract(p.x * p.y * p.z * (p.x + p.y + p.z));
}

float noise(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(
        mix(
            mix(hash(i + vec3(0,0,0)), hash(i + vec3(1,0,0)), f.x),
            mix(hash(i + vec3(0,1,0)), hash(i + vec3(1,1,0)), f.x),
            f.y
        ),
        mix(
            mix(hash(i + vec3(0,0,1)), hash(i + vec3(1,0,1)), f.x),
            mix(hash(i + vec3(0,1,1)), hash(i + vec3(1,1,1)), f.x),
            f.y
        ),
        f.z
    );
}

void main() {
    vec3 sceneColor = texture(DiffuseSampler0, texCoord).rgb;
    float depth = texture(DiffuseDepthSampler, texCoord).r;

    if (depth >= 1.0) {
        if (uHeightFalloff != 0.0) {
            fragColor = vec4(sceneColor, 1.0);
            return;
        }
        fragColor = vec4(uFogColor, 1.0);
        return;
    }

    vec3 pos = screenToLocalSpace(texCoord, depth).xyz;
    float dist = length(pos);

    float fogFactor = clamp((dist - uFogStart) / max(uFogEnd - uFogStart, 0.001), 0.0, 1.0);
    fogFactor = 1.0 - exp(-fogFactor * 3.0); // exponential easing

    float worldY = pos.y + uCameraY;
    float heightFactor = exp(-max(worldY, 0.0) * uHeightFalloff);
    heightFactor = clamp(heightFactor, 0.0, 1.0);

    vec3 noisePos = pos * 0.02;
    noisePos.y += VeilRenderTime * 0.02;
    float n = noise(noisePos);
    float distortion = mix(1.0, n, uChaos);

    fogFactor = clamp(fogFactor * heightFactor * distortion * uFogThickness, 0.0, 1.0);

    fragColor = vec4(mix(sceneColor, uFogColor, fogFactor), 1.0);
}
