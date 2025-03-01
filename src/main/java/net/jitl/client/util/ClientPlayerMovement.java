package net.jitl.client.util;

import net.jitl.core.init.network.PacketUpdateClientPlayerMovement;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.OptionalDouble;

public class ClientPlayerMovement {

    public static void adjustPlayerMovement(double x, double y, double z, PacketUpdateClientPlayerMovement.Operation operation) {
        Player player = Minecraft.getInstance().player;
        assert player != null;
        Vec3 velocity = player.getDeltaMovement();
        switch (operation) {
            case SET -> player.setDeltaMovement(x, y, z);
            case ADD -> player.setDeltaMovement(velocity.add(x, y, z));
            case MULTIPLY -> player.setDeltaMovement(velocity.multiply(x, y, z));
            case MAX -> player.setDeltaMovement(Math.min(x, velocity.x), Math.min(y, velocity.y), Math.min(z, velocity.z));
            case MIN -> player.setDeltaMovement(Math.max(x, velocity.x), Math.max(y, velocity.y), Math.max(z, velocity.z));
        }
    }
}
