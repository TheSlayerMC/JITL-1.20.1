package net.jitl.core.init.network;

import net.jitl.common.capability.keypressed.PressedKeyCapProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPressKey {

    private final boolean isAmulet;
    private final boolean isGear;

    public PacketPressKey(FriendlyByteBuf buf) {
        this.isAmulet = buf.readBoolean();
        this.isGear = buf.readBoolean();
    }

    public PacketPressKey(boolean isAmuletPressed, boolean isGearPressed) {
        this.isAmulet = isAmuletPressed;
        this.isGear = isGearPressed;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isAmulet);
        buffer.writeBoolean(isGear);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(keys -> {
                keys.setAmuletPressed(this.isAmulet);
                keys.setArmorPressed(this.isGear);
            });
        ctx.get().setPacketHandled(true);
    }
}