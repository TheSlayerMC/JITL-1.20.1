package net.jitl.common.items.curios.amulet;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.capability.keypressed.PressedKeyCapProvider;
import net.jitl.common.items.curios.JCurioItem;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.network.PacketUpdateClientPlayerMovement;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import top.theillusivec4.curios.api.SlotContext;

public class CloudWalkerAmuletItem extends JCurioItem {

    public CloudWalkerAmuletItem(Item.Properties properties) {
        super(properties);
        properties.durability(256);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(key -> {

                if(key.isAmuletPressed()) {
                    player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                        if(essence.consumeEssence(player, 0.15F)) {
                            player.fallDistance = 0.0F;

                            JNetworkRegistry.INSTANCE.sendTo(new PacketUpdateClientPlayerMovement(PacketUpdateClientPlayerMovement.Operation.ADD, 0.1), ((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);

                            if (!player.level().isClientSide()) {
                                double halfSize = player.getBbWidth() / 2;
                                ((ServerLevel) player.level()).sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, halfSize, 0, halfSize, 0);
                            }
                        }
                    });
                }
            });
        }
    }
}
