package net.thejeezed.craftplusplus.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.mob.StraferEntity;
import net.thejeezed.craftplusplus.mob.SulphurZombieEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CraftPlusPlus.MOD_ID);

    public static final RegistryObject<EntityType<StraferEntity>> STRAFER =
            ENTITY_TYPES.register("strafer", () -> EntityType.Builder.of(StraferEntity::new, MobCategory.CREATURE)
                    .sized(0.7f, 1.5f).build("strafer"));

    public static final RegistryObject<EntityType<SulphurZombieEntity>> SULPHUR_ZOMBIE =
            ENTITY_TYPES.register("sulphur_zombie_spawn_egg.json", () -> EntityType.Builder.of(SulphurZombieEntity::new, MobCategory.CREATURE)
                    .sized(0.7f, 1.5f).build("sulphur_zombie_spawn_egg.json"));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}