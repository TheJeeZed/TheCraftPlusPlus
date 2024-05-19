package net.thejeezed.craftplusplus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.init.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Objects;

import static net.thejeezed.craftplusplus.CraftPlusPlus.MOD_ID;
import static net.thejeezed.craftplusplus.init.ModBlocks.*;
import static net.thejeezed.craftplusplus.init.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        trimmedArmorItem(COPPER_HELMET);
        trimmedArmorItem(COPPER_CHESTPLATE);
        trimmedArmorItem(COPPER_LEGGINGS);
        trimmedArmorItem(COPPER_BOOTS);
        trimmedArmorItem(TURTLE_SHELL_CHESTPLATE);
        trimmedArmorItem(TURTLE_SHELL_LEGGINGS);
        trimmedArmorItem(TURTLE_SHELL_BOOTS);

        evenSimplerBlockItem(ModBlocks.AMETHYST_SLAB);
        evenSimplerBlockItem(ModBlocks.AMETHYST_BRICK_SLAB);
        evenSimplerBlockItem(ModBlocks.SMOOTH_AMETHYST_SLAB);
        evenSimplerBlockItem(ModBlocks.AMETHYST_STAIRS);
        evenSimplerBlockItem(ModBlocks.AMETHYST_BRICK_STAIRS);
        evenSimplerBlockItem(ModBlocks.SMOOTH_AMETHYST_STAIRS);

        wallItem(AMETHYST_WALL, ModBlocks.AMETHYST_BRICK);
        wallItem(AMETHYST_BRICK_WALL, ModBlocks.AMETHYST_BRICK);
        wallItem(SMOOTH_AMETHYST_WALL, SMOOTH_AMETHYST);

        simpleItem(COTTON_SEEDS);


        //trimmedArmorItem for trims
        //simpleItem for a simple item. This will make it generated
        //evenSimplerBlockItem this is for blocks to make an item model.
        //trapdoorItem for trapdoors
        //fenceItem for fences
        //buttonItem for buttons
        //handheldItem anything weilded, like with a stick
        //simpleBlockItem block
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private void simpleItem(@NotNull RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CraftPlusPlus.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(@NotNull RegistryObject<Block> block) {
        this.withExistingParent(CraftPlusPlus.MOD_ID + ":" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
                modLoc("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    public void trapdoorItem(@NotNull RegistryObject<Block> block) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
                modLoc("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath() + "_bottom"));
    }

    public void fenceItem(@NotNull RegistryObject<Block> block, @NotNull RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(CraftPlusPlus.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }

    public void buttonItem(@NotNull RegistryObject<Block> block, @NotNull RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(CraftPlusPlus.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }

    public void wallItem(@NotNull RegistryObject<Block> block, @NotNull RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(CraftPlusPlus.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }

    private ItemModelBuilder handheldItem(@NotNull RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CraftPlusPlus.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(@NotNull RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID,"item/" + item.getId().getPath()));
    }
}
