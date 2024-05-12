package net.thejeezed.craftplusplus.mob.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.client.layers.ModModelLayers;
import net.thejeezed.craftplusplus.mob.StraferEntity;
import net.thejeezed.craftplusplus.mob.SulphurZombieEntity;
import org.jetbrains.annotations.NotNull;

public class SulphurZombieRenderer extends MobRenderer<SulphurZombieEntity, SulphurZombieModel<SulphurZombieEntity>> {
    public SulphurZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SulphurZombieModel<>(pContext.bakeLayer(ModModelLayers.SULPHUR_ZOMBIE_LAYER)), 0.2f);
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SulphurZombieEntity pEntity) {
        return new ResourceLocation(CraftPlusPlus.MOD_ID, "textures/entity/sulphur_zombie.png");
    }
}
