package net.jitl.client.render.entity.frozen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.JModelLayers;
import net.jitl.client.model.ShiverwolfModel;
import net.jitl.client.render.entity.frozen.layer.ShiverwolfCollarLayer;
import net.jitl.common.entity.frozen.Shiverwolf;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfRenderer extends MobRenderer<Shiverwolf, ShiverwolfModel<Shiverwolf>> {

    public ShiverwolfRenderer(EntityRendererProvider.Context c) {
        super(c, new ShiverwolfModel<>(c.bakeLayer(JModelLayers.SHIVERWOLF_MODEL_LAYER)), 0.5F);
        this.addLayer(new ShiverwolfCollarLayer(this));
    }

    @Override
    protected float getBob(Shiverwolf livingBase, float partialTicks) {
        return livingBase.getTailAngle();
    }

    @Override
    public void render(Shiverwolf entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        if(entity.isWet()) {
            float f = entity.getWetShade(partialTicks);
            this.model.setColor(f, f, f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        if(entity.isWet())
            this.model.setColor(1F, 1F, 1F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Shiverwolf entity) {
        return entity.getTexture();
    }
}
