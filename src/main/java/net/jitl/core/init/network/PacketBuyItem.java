package net.jitl.core.init.network;

import net.jitl.common.capability.keypressed.PressedKeyCapProvider;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PacketBuyItem {

    private final String itemName;
    private final int amount, cost;

    public PacketBuyItem(FriendlyByteBuf buf) {
        this.itemName = buf.readUtf();
        this.amount = buf.readInt();
        this.cost = buf.readInt();
    }

    public PacketBuyItem(String itemName, int amount, int cost) {
        this.itemName = itemName;
        this.amount = amount;
        this.cost = cost;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(itemName);
        buffer.writeInt(amount);
        buffer.writeInt(cost);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        assert player != null;
        player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JITL.MODID, itemName));
            if(stats.useSentacoins(cost)) {
                assert item != null;
                player.addItem(new ItemStack(item, amount));
            } else {
                player.sendSystemMessage(Component.translatable("jitl.trade.no"));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}