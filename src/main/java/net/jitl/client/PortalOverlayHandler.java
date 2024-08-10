package net.jitl.client;

import net.jitl.common.capability.portal.PlayerPortalProvider;
import net.jitl.core.init.JITL;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PortalOverlayHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.player != null) {
            event.player.getCapability(PlayerPortalProvider.PORTAL).ifPresent(portal -> {
                if (event.phase == TickEvent.Phase.END) {
                    portal.clientTick();
                }
            });
        }
    }
}
