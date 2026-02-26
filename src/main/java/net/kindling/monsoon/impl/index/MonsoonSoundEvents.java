package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.sound.SoundEvent;

public interface MonsoonSoundEvents {
    SoundEventRegistrant SOUND_EVENTS = new SoundEventRegistrant(Monsoon.MOD_ID);

    SoundEvent SWITCH = SOUND_EVENTS.register("block.switch");
    SoundEvent CRISP_USE = SOUND_EVENTS.register("item.crisp_use");
    SoundEvent WHATTHEACTUALFUCKISTHIS = SOUND_EVENTS.register("gui.whattheactualfuckisthis");

    static void init() {
        //
    }
}
