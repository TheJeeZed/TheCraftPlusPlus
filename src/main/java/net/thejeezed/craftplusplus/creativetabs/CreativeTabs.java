package net.thejeezed.craftplusplus.creativetabs;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.init.ModBlocks;
import net.thejeezed.craftplusplus.init.ModItems;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CraftPlusPlus.MOD_ID);
    public static final RegistryObject<CreativeModeTab> CRAFT_TAB = CREATIVE_MODE_TABS.register("craft_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Blocks.CRAFTING_TABLE))
                    .title(Component.translatable("creativetab.craft_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CHARCOAL_BLOCK.get());
                        pOutput.accept(ModBlocks.COMPRESSED_COPPER.get());
                        pOutput.accept(ModItems.COPPER_BUCKET.get());
                        pOutput.accept(ModItems.COPPER_WATER_BUCKET.get());
                        pOutput.accept(ModItems.COPPER_MILK_BUCKET.get());
                        pOutput.accept(ModItems.COPPER_SHOVEL.get());
                        pOutput.accept(ModItems.COPPER_PICKAXE.get());
                        pOutput.accept(ModItems.COPPER_AXE.get());
                        pOutput.accept(ModItems.COPPER_HOE.get());
                        pOutput.accept(ModItems.COPPER_SWORD.get());
                        pOutput.accept(ModItems.COPPER_HELMET.get());
                        pOutput.accept(ModItems.COPPER_CHESTPLATE.get());
                        pOutput.accept(ModItems.COPPER_LEGGINGS.get());
                        pOutput.accept(ModItems.COPPER_BOOTS.get());
                        pOutput.accept(ModItems.COPPER_HORSE_ARMOR.get());
                        pOutput.accept(ModItems.NETHERITE_HORSE_ARMOR.get());
                        pOutput.accept(ModItems.SEA_SOUP.get());
                        pOutput.accept(ModItems.RAW_TENTACLE.get());
                        pOutput.accept(ModItems.COOKED_TENTACLE.get());
                        pOutput.accept(ModItems.STRAFER_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SULPHUR_ZOMBIE_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MAGIC_MIRROR.get());
                        pOutput.accept(ModItems.TURTLE_SHELL_CHESTPLATE.get());
                        pOutput.accept(ModItems.TURTLE_SHELL_LEGGINGS.get());
                        pOutput.accept(ModItems.TURTLE_SHELL_BOOTS.get());
                        pOutput.accept(ModItems.SUGARCANE_ON_A_STICK.get());
                        pOutput.accept(ModItems.COTTON_SEEDS.get());
                        pOutput.accept(ModBlocks.SULPHUR_ORE.get());
                        pOutput.accept(ModBlocks.COMPRESSED_SULPHUR.get());
                        pOutput.accept(ModBlocks.SULPHUR_BLOCK.get());
                        pOutput.accept(ModBlocks.AMETHYST_STAIRS.get());
                        pOutput.accept(ModBlocks.AMETHYST_SLAB.get());
                        pOutput.accept(ModBlocks.AMETHYST_WALL.get());
                        pOutput.accept(ModBlocks.AMETHYST_BRICK.get());
                        pOutput.accept(ModBlocks.AMETHYST_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.AMETHYST_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.AMETHYST_BRICK_WALL.get());
                        pOutput.accept(ModBlocks.SMOOTH_AMETHYST.get());
                        pOutput.accept(ModBlocks.SMOOTH_AMETHYST_STAIRS.get());
                        pOutput.accept(ModBlocks.SMOOTH_AMETHYST_SLAB.get());
                        pOutput.accept(ModBlocks.SMOOTH_AMETHYST_WALL.get());
                        pOutput.accept(ModBlocks.CHISLED_AMETHYST.get());
                        pOutput.accept(ModItems.SULPHUR_CHUNK.get());
                        pOutput.accept(ModItems.COTTON.get());
                        pOutput.accept(ModItems.DYNAMITE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
