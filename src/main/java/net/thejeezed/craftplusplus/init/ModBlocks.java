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
