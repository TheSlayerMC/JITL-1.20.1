package net.jitl.client.render.entity.frozen.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.model.ShiverwolfModel;
import net.jitl.common.entity.frozen.Shiverwolf;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfCollarLayer extends RenderLayer<Shiverwolf, ShiverwolfModel<Shiverwolf>> {
    private static final ResourceLocation WOLF_COLLAR_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

    public ShiverwolfCollarLayer(RenderLayerParent<Shiverwolf, ShiverwolfModel<Shiverwolf>> renderer) {
        super(renderer);
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Shiverwolf livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isTame() && !livingEntity.isInvisible()) {
            float[] $$10 = livingEntity.getCollarColor().getTextureDiffuseColors();
            renderColoredCutoutModel(this.getParentModel(), WOLF_COLLAR_LOCATION, poseStack, buffer, packedLight, livingEntity, $$10[0], $$10[1], $$10[2]);
        }
    }
}
