package net.jitl.core.init.network;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.util.ClientPlayerMovement;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateClientPlayerMovement {

    private final double x, y, z;
    private final Operation operation;

    public PacketUpdateClientPlayerMovement(Operation operation, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.operation = operation;
    }

    public PacketUpdateClientPlayerMovement(FriendlyByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.operation = buf.readEnum(Operation.class);
    }

    public PacketUpdateClientPlayerMovement(Operation operation, double x, double z) {
        this(operation, x, 0, z);
    }

    public PacketUpdateClientPlayerMovement(Operation operation, double y) {
        this(operation, 0, y, 0);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeEnum(operation);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(Minecraft.getInstance().player != null) {
            ClientPlayerMovement.adjustPlayerMovement(this.x, this.y, this.z, this.operation);
            ctx.get().setPacketHandled(true);
        }
    }

    public enum Operation {
        SET,
        ADD,
        MULTIPLY,
        MAX,
        MIN;
    }
}