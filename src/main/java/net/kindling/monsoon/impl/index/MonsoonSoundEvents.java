package net.kindling.monsoon.impl.index;

import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.kindling.monsoon.impl.Monsoon;
import net.minecraft.sound.SoundEvent;

public interface MonsoonSoundEvents {
    SoundEventRegistrant SOUND_EVENTS = new SoundEventRegistrant(Monsoon.MOD_ID);

    SoundEvent SWITCH_1 = SOUND_EVENTS.register("block.switch_1");
    SoundEvent SWITCH_2 = SOUND_EVENTS.register("block.switch_2");

    static void init() {
        //
    }
}
