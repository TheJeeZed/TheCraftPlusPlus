package net.thejeezed.pandorium.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.thejeezed.pandorium.client.layers.ModModelLayers;
import net.thejeezed.pandorium.Pandorium;
import net.thejeezed.pandorium.mob.client.StraferModel;
import net.thejeezed.pandorium.mob.client.SulphurZombieModel;

@Mod.EventBusSubscriber(modid = Pandorium.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.STRAFER_LAYER, StraferModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SULPHUR_ZOMBIE_LAYER, SulphurZombieModel::createBodyLayer);
    }
}
