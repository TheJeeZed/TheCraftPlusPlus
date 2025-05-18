package net.thejeezed.craftplusplus.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.block.crops.CottonCropBlock;
import net.thejeezed.craftplusplus.kiln.KilnBlock;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CraftPlusPlus.MOD_ID);

    //expanding vanilla tile sets;

    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_STONE_WALL = registerBlock("smooth_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> WHITE_CONCRETE_WALL = registerBlock("white_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ORANGE_CONCRETE_WALL = registerBlock("orange_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_WALL = registerBlock("magenta_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_WALL = registerBlock("light_blue_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> YELLOW_CONCRETE_WALL = registerBlock("yellow_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIME_CONCRETE_WALL = registerBlock("lime_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PINK_CONCRETE_WALL = registerBlock("pink_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRAY_CONCRETE_WALL = registerBlock("gray_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_WALL = registerBlock("light_gray_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CYAN_CONCRETE_WALL = registerBlock("cyan_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURPLE_CONCRETE_WALL = registerBlock("purple_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLUE_CONCRETE_WALL = registerBlock("blue_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BROWN_CONCRETE_WALL = registerBlock("brown_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GREEN_CONCRETE_WALL = registerBlock("green_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RED_CONCRETE_SLAB = registerBlock("red_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RED_CONCRETE_WALL = registerBlock("red_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLACK_CONCRETE_WALL = registerBlock("black_concrete_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END_STONE_STAIRS = registerBlock("end_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END_STONE_SLAB = registerBlock("end_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END_STONE_WALL = registerBlock("end_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHERRACK_STAIRS = registerBlock("netherrack_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHERRACK_SLAB = registerBlock("netherrack_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHERRACK_WALL = registerBlock("netherrack_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DRIPSTONE_STAIRS = registerBlock("dripstone_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DRIPSTONE_SLAB = registerBlock("dripstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DRIPSTONE_WALL = registerBlock("dripstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_STAIRS = registerBlock("cracked_deepslate_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_SLAB = registerBlock("cracked_deepslate_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_WALL = registerBlock("cracked_deepslate_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS = registerBlock("cracked_polished_blackstone_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB = registerBlock("cracked_polished_blackstone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL = registerBlock("cracked_polished_blackstone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_STAIRS = registerBlock("cracked_stone_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_SLAB = registerBlock("cracked_stone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_WALL = registerBlock("cracked_stone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_STAIRS = registerBlock("cracked_nether_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_SLAB = registerBlock("cracked_nether_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_WALL = registerBlock("cracked_nether_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS = registerBlock("quartz_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB = registerBlock("quartz_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL = registerBlock("quartz_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = registerBlock("smooth_basalt_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB = registerBlock("smooth_basalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = registerBlock("smooth_basalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_STAIRS = registerBlock("deepslate_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_SLAB = registerBlock("deepslate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_WALL = registerBlock("deepslate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CALCITE_STAIRS = registerBlock("calcite_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CALCITE_SLAB = registerBlock("calcite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CALCITE_WALL = registerBlock("calcite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STONE_WALL = registerBlock("stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = registerBlock("smooth_quartz_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUARTZ_WALL = registerBlock("quartz_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURPUR_WALL = registerBlock("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PRISMARINE_BRICK_WALL = registerBlock("prismarine_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2f).requiresCorrectToolForDrops()));

    // standard mod blocks

    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).destroyTime(3))); // TODO: ADD CUSTOM TAB HERE; THIS IS WHAT BROKE MY COMMIT!

    public static final RegistryObject<Block> COMPRESSED_COPPER = registerBlock("compressed_copper",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).destroyTime(3))); // TODO: ADD CUSTOM TAB HERE; THIS IS WHAT BROKE MY COMMIT!

    public static final RegistryObject<Block> COMPRESSED_SULPHUR = registerBlock("compressed_sulphur",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).destroyTime(3)));

    public static final RegistryObject<Block> SULPHUR_BLOCK = registerBlock("sulphur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).destroyTime(3)));

    public static final RegistryObject<Block> SULPHUR_ORE = registerBlock("sulphur_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    //Amethyst TileSet
    public static final RegistryObject<Block> AMETHYST_STAIRS = registerBlock("amethyst_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_SLAB = registerBlock("amethyst_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_WALL = registerBlock("amethyst_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_BRICK = registerBlock("amethyst_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_BRICK_STAIRS = registerBlock("amethyst_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_BRICK_SLAB = registerBlock("amethyst_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETHYST_BRICK_WALL = registerBlock("amethyst_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_AMETHYST = registerBlock("smooth_amethyst",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_AMETHYST_STAIRS = registerBlock("smooth_amethyst_stairs",
            () -> new StairBlock(() -> ModBlocks.SMOOTH_AMETHYST.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_AMETHYST_SLAB = registerBlock("smooth_amethyst_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_AMETHYST_WALL = registerBlock("smooth_amethyst_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHISELED_AMETHYST = registerBlock("chiseled_amethyst",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS_STAIRS = registerBlock("cracked_red_nether_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_RED_NETHER_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS_SLAB = registerBlock("cracked_red_nether_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS_WALL = registerBlock("cracked_red_nether_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()));

    //CROPS
    public static final RegistryObject<Block> COTTON_CROP = BLOCKS.register("cotton_crop", ()-> new CottonCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    public static final RegistryObject<Block> KILN = registerBlock("kiln", ()->  new KilnBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel((p_152677_) -> {return 14;}).destroyTime(0.1f)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
