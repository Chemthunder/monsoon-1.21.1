package net.kindling.monsoon.impl.client.audio;

import de.keksuccino.melody.resources.audio.SimpleAudioFactory;
import de.keksuccino.melody.resources.audio.openal.ALAudioClip;
import de.keksuccino.melody.resources.audio.openal.ALException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DynamicTrack {

    private final Identifier id;
    private ALAudioClip clip;

    private float targetVolume = 0f;
    private float currentVolume = 0f;
    private float fadeSpeed = 0.05f;

    private boolean playing = false;

    public DynamicTrack(Identifier id) {
        this.id = id;
    }

    public CompletableFuture<Void> load() {
        CompletableFuture<Void> future = new CompletableFuture<>();

        MinecraftClient.getInstance().execute(() -> {
            try {
                SimpleAudioFactory
                        .ogg(id.toString(), SimpleAudioFactory.SourceType.RESOURCE_LOCATION)
                        .thenAccept(alClip -> {
                            clip = alClip;
                            try {
                                clip.setVolume(0f);
                            } catch (ALException e) {
                                throw new RuntimeException(e);
                            }
                            future.complete(null);
                        })
                        .exceptionally(ex -> {
                            future.completeExceptionally(ex);
                            return null;
                        });
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    public CompletableFuture<Void> unload() {
        CompletableFuture<Void> future = new CompletableFuture<>();

        MinecraftClient.getInstance().execute(() -> {
            try {
                if (clip != null) {
                    try {
                        clip.stop();
                    } catch (Exception ignored) {
                    }

                    try {
                        clip.close();
                    } catch (Exception ignored) {
                    }

                    clip = null;
                }

                future.complete(null);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    public void play(float fadeSeconds, boolean loop) throws ALException {
        if (clip == null) return;

        clip.setLooping(loop);
        clip.play();

        fadeSpeed = 1f / (fadeSeconds * 20f);
        targetVolume = 1f;
        playing = true;
    }

    public void stop(float fadeSeconds) {
        fadeSpeed = 1f / (fadeSeconds * 20f);
        targetVolume = 0f;
    }

    public void setIntensity(float value) {
        targetVolume = Math.clamp(value, 0f, 1f);
    }

    public void tick() {
        if (!playing || clip == null) return;

        try {
            currentVolume += (targetVolume - currentVolume) * fadeSpeed;
            clip.setVolume(currentVolume);

            if (currentVolume <= 0.001f && targetVolume == 0f) {
                clip.stop();
                playing = false;
            }
        } catch (ALException e) {
            e.printStackTrace();
            playing = false;
        }
    }

    public Identifier getId() {
        return id;
    }
}