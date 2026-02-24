package net.kindling.monsoon.impl.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_MONSOON = "key.category.monsoon.monsoon";
    public static final String KEY_FLASHLIGHT_TOGGLE = "key.monsoon.flashlight";

    public static KeyBinding flashlightKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (flashlightKey.wasPressed()) {
                client.player.sendMessage(Text.of("god i hate keybinds"));
            }
        });
    }

    public static void register() {
        flashlightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FLASHLIGHT_TOGGLE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_MONSOON
        ));

        registerKeyInputs();
    }
}
