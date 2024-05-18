package net.thejeezed.craftplusplus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
        add("string_from_strafer.json", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/strafer")).build(),LootItemRandomChanceCondition.randomChance(1f).build() }, Items.STRING));
    }
}