package net.thejeezed.craftplusplus.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.client.layers.ModModelLayers;
import net.thejeezed.craftplusplus.mob.client.StraferModel;
import net.thejeezed.craftplusplus.mob.client.SulphurZombieModel;

@Mod.EventBusSubscriber(modid = CraftPlusPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.STRAFER_LAYER, StraferModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SULPHUR_ZOMBIE_LAYER, SulphurZombieModel::createBodyLayer);
    }
}
