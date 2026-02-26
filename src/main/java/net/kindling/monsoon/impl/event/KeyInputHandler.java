package net.kindling.monsoon.impl.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.kindling.monsoon.impl.networking.c2s.FlashlightTogglePayload;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_MONSOON = "key.category.monsoon.monsoon";
    public static final String KEY_FLASHLIGHT_TOGGLE = "key.monsoon.flashlight";

    public static KeyBinding flashlightKey;

    // Global variable to track the last time the key was pressed
    private static long lastToggleTime = 0;  // Time of last toggle in milliseconds
    private static final long COOLDOWN_TIME = 500;  // Cooldown time in milliseconds (500 ms = 0.5 seconds)

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (flashlightKey.wasPressed() && client.player != null) {

                long currentTime = System.currentTimeMillis();

                if (currentTime - lastToggleTime < COOLDOWN_TIME) return;

                try {
                    PlayerGameComponent component = PlayerGameComponent.KEY.get(client.player);

                    FlashlightTogglePayload payload = new FlashlightTogglePayload(!component.isFlashlight());
                    ClientPlayNetworking.send(payload);

                    client.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.8F, 0.2F);

                    lastToggleTime = System.currentTimeMillis();
                } catch (Exception e) {
                    Monsoon.LOGGER.error("Failed to send FlashlightTogglePayload");
                }
            }
        });
    }

    public static void register() {
        flashlightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FLASHLIGHT_TOGGLE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_MONSOON
        ));

        registerKeyInputs();
    }
}
