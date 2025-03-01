package net.jitl.client.gui;

import com.mojang.blaze3d.platform.InputConstants;
import net.jitl.client.gui.overlay.PlayerStats;
import net.jitl.core.init.JITL;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindEvents {

    public static KeyMapping keyStats = new KeyMapping("Open Journey Stats", KeyConflictContext.IN_GAME, InputConstants.getKey(InputConstants.KEY_J, -1), I18n.get("jitl.key"));
    public static KeyMapping keyArmor = new KeyMapping("Use Armor Ability", GLFW.GLFW_KEY_C, I18n.get("jitl.key"));
    public static KeyMapping keyAmulet = new KeyMapping("Use Amulet Ability", GLFW.GLFW_KEY_V, I18n.get("jitl.key"));

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(keyStats);
//        event.register(keyArmor);
        event.register(keyAmulet);
    }
}
