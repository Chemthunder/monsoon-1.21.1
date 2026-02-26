#include veil:space_helper

uniform sampler2D DiffuseSampler0;
uniform sampler2D DiffuseDepthSampler;

uniform mat4 ProjMat;

in vec2 texCoord;

out vec4 fragColor;

const float CURVATURE_STRENGTH = -0.4;
const float DEPTH_FALLOFF_START = 0.9995;

void main() {
    float depth = texture(DiffuseDepthSampler, texCoord).r;
    float farness = smoothstep(DEPTH_FALLOFF_START, 1.0, depth);
    vec2 centered = texCoord * 2.0 - 1.0;

    float warpY = -(centered.x * centered.x) * CURVATURE_STRENGTH * farness;
    vec2 warpedUV = texCoord + vec2(0.0, warpY * 0.5);

    warpedUV = clamp(warpedUV, 0.001, 0.999);

    fragColor = texture(DiffuseSampler0, warpedUV);
}
