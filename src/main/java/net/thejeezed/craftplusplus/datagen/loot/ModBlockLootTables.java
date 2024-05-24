package net.thejeezed.craftplusplus.datagen.loot;


import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.thejeezed.craftplusplus.init.ModBlocks;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;

import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.block.crops.CottonCropBlock;
import net.thejeezed.craftplusplus.init.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    //Loot Tables For Blocks

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CHARCOAL_BLOCK.get());
        this.dropSelf(ModBlocks.COMPRESSED_COPPER.get());
        this.add(ModBlocks.SULPHUR_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SULPHUR_ORE.get(), ModItems.SULPHUR_CHUNK.get()));
        this.dropSelf(ModBlocks.COMPRESSED_SULPHUR.get());
        this.dropSelf(ModBlocks.SULPHUR_BLOCK.get());
        this.dropSelf(ModBlocks.AMETHYST_STAIRS.get());
        this.dropSelf(ModBlocks.AMETHYST_SLAB.get());
        this.dropSelf(ModBlocks.AMETHYST_WALL.get());
        this.dropSelf(ModBlocks.AMETHYST_BRICK.get());
        this.dropSelf(ModBlocks.AMETHYST_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.AMETHYST_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.AMETHYST_BRICK_WALL.get());
        this.dropSelf(ModBlocks.SMOOTH_AMETHYST.get());
        this.dropSelf(ModBlocks.CHISLED_AMETHYST.get());
        this.dropSelf(ModBlocks.SMOOTH_AMETHYST_STAIRS.get());
        this.dropSelf(ModBlocks.SMOOTH_AMETHYST_SLAB.get());
        this.dropSelf(ModBlocks.SMOOTH_AMETHYST_WALL.get());

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.COTTON_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 7));

        this.add(ModBlocks.COTTON_CROP.get(), createCropDrops(ModBlocks.COTTON_CROP.get(), ModItems.COTTON.get(),
                ModItems.COTTON_SEEDS.get(), lootitemcondition$builder));
        }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}