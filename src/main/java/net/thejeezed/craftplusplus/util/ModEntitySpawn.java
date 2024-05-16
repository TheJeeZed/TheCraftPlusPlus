package net.thejeezed.craftplusplus.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;

public record ModEntitySpawn (HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {

    public static DeferredRegister<Codec<? extends BiomeModifier>> SERIALIZER = DeferredRegister
            .create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, CraftPlusPlus.MOD_ID);

    static RegistryObject<Codec<ModEntitySpawn>> ENTITY_SPAWN_CODEC = SERIALIZER.register("mobspawns",
            () -> RecordCodecBuilder.create(builder -> builder
                    .group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(ModEntitySpawn::biomes),
                            MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(ModEntitySpawn::spawn))
                    .apply(builder, ModEntitySpawn::new)));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, this.spawn).addSpawn(MobCategory.CREATURE, this.spawn);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ENTITY_SPAWN_CODEC.get();
    }
}
