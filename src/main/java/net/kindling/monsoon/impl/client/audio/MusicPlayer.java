package net.kindling.monsoon.impl.client.audio;

import de.keksuccino.melody.resources.audio.openal.ALException;
import net.kindling.monsoon.impl.MonsoonClient;
import net.kindling.monsoon.impl.util.ToastHelper;
import net.minecraft.util.Identifier;

import static net.kindling.monsoon.impl.Monsoon.id;

public class MusicPlayer {
    public static void coma() {
        ToastHelper.showMusicToast("Now Playing", "Coma - Bashful");

        Identifier id = id("audio/music/coma.ogg");

        DynamicTrack track = MonsoonClient.AUDIO_ENGINE.get(id);

        if (track != null) {
            try {
                track.play(3f, true);
            } catch (ALException e) {
                e.printStackTrace();
            }
        }
    }

    public static void high() {
        ToastHelper.showMusicToast("Now Playing", "Coma - Bashful");

        Identifier id = id("audio/music/high.ogg");

        DynamicTrack track = MonsoonClient.AUDIO_ENGINE.get(id);

        if (track != null) {
            try {
                track.play(3f, true);
            } catch (ALException e) {
                e.printStackTrace();
            }
        }
    }
}
