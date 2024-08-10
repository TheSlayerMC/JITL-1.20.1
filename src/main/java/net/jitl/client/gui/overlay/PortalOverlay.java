package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.common.capability.portal.PlayerPortalProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;

@OnlyIn(Dist.CLIENT)
public class PortalOverlay {

    public static void render(ForgeGui gui, GuiGraphics poseStack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            player.getCapability(PlayerPortalProvider.PORTAL).ifPresent(playerPortalOverlay -> {
                float timeInPortal = playerPortalOverlay.getOldPortalOverlayTime() * 1.45F + (playerPortalOverlay.getPortalOverlayTime() - playerPortalOverlay.getOldPortalOverlayTime()) * partialTick;
                if (timeInPortal > 0.0F) {
                    if (timeInPortal < 1.0F) {
                        timeInPortal *= timeInPortal;
                        timeInPortal *= timeInPortal;
                        timeInPortal = timeInPortal * 0.8F + 0.2F;
                    }
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.enableBlend();
                    poseStack.setColor(1.0F, 1.0F, 1.0F, timeInPortal);
                    TextureAtlasSprite textureatlassprite = Minecraft.getInstance()
                            .getBlockRenderer()
                            .getBlockModelShaper()
                            .getParticleIcon(playerPortalOverlay.getPortalBlockToRender().defaultBlockState());
                    poseStack.blit(0, 0, -90, poseStack.guiWidth(), poseStack.guiHeight(), textureatlassprite);
                    RenderSystem.disableBlend();
                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    poseStack.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                }
            });
        }
    }
}
