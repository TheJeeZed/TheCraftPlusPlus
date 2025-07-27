package net.thejeezed.pandorium.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thejeezed.pandorium.entity.ModEntities;
import net.thejeezed.pandorium.Pandorium;
import net.thejeezed.pandorium.mob.StraferEntity;
import net.thejeezed.pandorium.mob.SulphurZombieEntity;

@Mod.EventBusSubscriber(modid = Pandorium.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STRAFER.get(), StraferEntity.createAttributes().build());
        event.put(ModEntities.SULPHUR_ZOMBIE.get(), SulphurZombieEntity.createAttributes().build());
    }
}
