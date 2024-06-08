package net.thejeezed.craftplusplus.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CraftPlusPlus.MOD_ID);

    public static final RegistryObject<SoundEvent> CALM_NIGHT = registerSoundEvents("calm_night");

    public static final RegistryObject<SoundEvent> COUNTRY_ROAD = registerSoundEvents("country_road");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CraftPlusPlus.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}
