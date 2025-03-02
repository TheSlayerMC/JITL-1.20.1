package net.jitl.core.data;

import net.jitl.client.stats.PacketPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.PacketBuyItem;
import net.jitl.core.init.network.PacketPressKey;
import net.jitl.core.init.network.PacketUpdateClientPlayerMovement;
import net.jitl.core.init.network.PacketBossBar;
import net.jitl.core.network.PacketEssenceBar;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class JNetworkRegistry {

    private static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(JITL.MODID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int index = 0;
        INSTANCE.registerMessage(index++, PacketEssenceBar.class, PacketEssenceBar::encode, PacketEssenceBar::decode, PacketEssenceBar::handle);
        INSTANCE.registerMessage(index++, PacketPressKey.class, PacketPressKey::encode, PacketPressKey::new, PacketPressKey::handle);
        INSTANCE.registerMessage(index++, PacketBossBar.class, PacketBossBar::encode, PacketBossBar::new, PacketBossBar::handle);
        INSTANCE.registerMessage(index++, PacketPlayerStats.class, PacketPlayerStats::encode, PacketPlayerStats::new, PacketPlayerStats::handle);
        INSTANCE.registerMessage(index++, PacketUpdateClientPlayerMovement.class, PacketUpdateClientPlayerMovement::encode, PacketUpdateClientPlayerMovement::new, PacketUpdateClientPlayerMovement::handle);
        INSTANCE.registerMessage(index++, PacketBuyItem.class, PacketBuyItem::encode, PacketBuyItem::new, PacketBuyItem::handle);
    }
}
