package net.jitl.client;

import net.jitl.core.config.JClientConfig;
import net.jitl.core.helper.InternetHandler;
import net.jitl.core.init.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.TextComponentHelper;

import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientLoginChecker {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(JClientConfig.UPDATE_MESSAGE.get()) {
            if(!player.level().isClientSide) {
                sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
                sendColouredMessage(player, ChatFormatting.GOLD, "[" + JITL.MOD_NAME + "]");
                sendColouredTranslatedMessage(player, ChatFormatting.LIGHT_PURPLE, "jitl.message.thank_you", player.getDisplayName());
                sendColouredTranslatedMessage(player, ChatFormatting.BLUE, "jitl.message.current_version", JITL.MOD_VERSION);

                try {
                    if(!InternetHandler.isOnline()) {
                        MutableComponent msg = Component.translatable("jitl.message.no_internet");
                        msg.withStyle(ChatFormatting.RED);
                        player.sendSystemMessage(msg);
                    }
                    try {
                        if(InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            sendColouredTranslatedMessage(player, ChatFormatting.GREEN, "jitl.message.update_available", InternetHandler.getUpdateVersion());
                        }
                        if(!InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            sendColouredTranslatedMessage(player, ChatFormatting.AQUA, "jitl.message.up_to_date");
                        }
                    } catch(IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch(SocketException e) {
                    throw new RuntimeException(e);
                }
                sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
            }
        }
    }

    public static void sendColouredTranslatedMessage(Player player, ChatFormatting colour, String translationKey, Object... args) {
        MutableComponent msg = Component.translatable(translationKey, args);
        msg.withStyle(colour);
        player.sendSystemMessage(msg);
    }

    public static void sendColouredMessage(Player player, ChatFormatting colour, String key) {
        MutableComponent msg = Component.literal(key);
        msg.withStyle(colour);
        player.sendSystemMessage(msg);
    }
}