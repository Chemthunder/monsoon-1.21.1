package net.kindling.monsoon.impl.networking.c2s;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kindling.monsoon.impl.Monsoon;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FlashlightTogglePayload(boolean flashlight) implements CustomPayload {
    public static final CustomPayload.Id<FlashlightTogglePayload> ID = new CustomPayload.Id<>(Monsoon.id("flashlight_toggle"));

    public static final PacketCodec<RegistryByteBuf, FlashlightTogglePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL,
            FlashlightTogglePayload::flashlight,
            FlashlightTogglePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<FlashlightTogglePayload> {
        public void receive(FlashlightTogglePayload payload, ServerPlayNetworking.Context context) {
            PlayerEntity player = context.player();
            if (player == null) return;

            PlayerGameComponent component = PlayerGameComponent.KEY.get(player);
            component.setFlashlight(payload.flashlight());
        }
    }
}
