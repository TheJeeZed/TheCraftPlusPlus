package net.thejeezed.craftplusplus.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.mob.StraferEntity;

@Mod.EventBusSubscriber(modid = CraftPlusPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STRAFER.get(), StraferEntity.createAttributes().build());
    }
}
