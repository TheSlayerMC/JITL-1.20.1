package net.jitl.client.gui;

import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.JITL;
import net.jitl.core.init.network.PacketPressKey;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyBindHandler {

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    @SubscribeEvent
    public static void onKeyTick(TickEvent.ClientTickEvent event) {
        if(MINECRAFT.screen == null) {
            if(KeyBindEvents.keyStats.isDown()) {
                KeyBindEvents.keyStats.consumeClick();
                displayPlayerStats(Minecraft.getInstance().player);
            }

            if(KeyBindEvents.keyAmulet.isDown() || KeyBindEvents.keyArmor.isDown()) {
                handleAbilityKeys(KeyBindEvents.keyAmulet.isDown(), KeyBindEvents.keyArmor.isDown());
            } else {
                handleAbilityKeys(false, false);
            }
        }
    }

    public static void handleAbilityKeys(boolean amulet, boolean gear) {
        JNetworkRegistry.INSTANCE.sendToServer(new PacketPressKey(amulet, gear));
    }

    @OnlyIn(Dist.CLIENT)
    public static void displayPlayerStats(Player player) {
        Minecraft.getInstance().setScreen(new PlayerStats(player));
    }
}
