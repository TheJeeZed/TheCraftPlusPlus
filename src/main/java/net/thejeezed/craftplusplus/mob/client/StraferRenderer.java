package net.thejeezed.craftplusplus.mob.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.client.layers.ModModelLayers;
import net.thejeezed.craftplusplus.mob.StraferEntity;
import org.jetbrains.annotations.NotNull;

public class StraferRenderer extends MobRenderer<StraferEntity, StraferModel<StraferEntity>> {
    public StraferRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new StraferModel<>(pContext.bakeLayer(ModModelLayers.STRAFER_LAYER)), 0.2f);
    }

    @Override
    public void render(StraferEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.8f, 0.8f, 0.8f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull StraferEntity pEntity) {
        return new ResourceLocation(CraftPlusPlus.MOD_ID, "textures/entity/strafer.png");
    }
}
