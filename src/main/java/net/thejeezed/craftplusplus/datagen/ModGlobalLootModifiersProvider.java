package net.thejeezed.craftplusplus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.init.ModItems;
import net.thejeezed.craftplusplus.datagen.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, CraftPlusPlus.MOD_ID);
    }

    @Override
    protected void start() {
        add("raw_tentacle_from_squid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/squid")).build(),LootItemRandomChanceCondition.randomChance(0.90f).build() }, ModItems.RAW_TENTACLE.get()));
        add("magic_mirror_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),LootItemRandomChanceCondition.randomChance(0.15F).build() }, ModItems.MAGIC_MIRROR.get()));
        add("magic_mirror_from_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),LootItemRandomChanceCondition.randomChance(0.25F).build() }, ModItems.MAGIC_MIRROR.get()));
    }
}