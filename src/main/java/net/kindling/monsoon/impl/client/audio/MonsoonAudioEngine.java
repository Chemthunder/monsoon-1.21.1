package net.kindling.monsoon.impl.client.audio;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MonsoonAudioEngine {

    private final Map<Identifier, DynamicTrack> tracks = new HashMap<>();

    public CompletableFuture<Void> scanAndLoad(String namespace, String folder) {
        ResourceManager manager = MinecraftClient.getInstance().getResourceManager();

        Collection<Identifier> found = manager.findResources(
                folder,
                id -> id.getNamespace().equals(namespace)
                        && id.getPath().endsWith(".ogg")
        ).keySet();

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Identifier id : found) {
            if (tracks.containsKey(id)) continue;

            DynamicTrack track = new DynamicTrack(id);
            tracks.put(id, track);
            futures.add(track.load());
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    public CompletableFuture<Void> stopAndUnloadAll() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (DynamicTrack track : tracks.values()) {
            futures.add(track.unload());
            track.stop(0);
        }

        tracks.clear();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    public DynamicTrack get(Identifier id) {
        return tracks.get(id);
    }

    public void tick() {
        tracks.values().forEach(DynamicTrack::tick);
    }
}