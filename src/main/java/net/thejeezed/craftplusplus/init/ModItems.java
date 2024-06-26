package net.thejeezed.craftplusplus.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.init.ModBlocks;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.enums.tools.ToolStats;
import net.thejeezed.craftplusplus.init.ModFoods;
import net.thejeezed.craftplusplus.item.custom.armor.CopperArmor;
import net.thejeezed.craftplusplus.item.custom.item.*;

import static net.thejeezed.craftplusplus.CraftPlusPlus.MOD_ID;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS =
                DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    //GENERIC
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));

    //COPPER BUCKET
    public static final RegistryObject<Item> COPPER_BUCKET = ITEMS.register("copper_bucket", ()-> new CopperBucketItem(()-> Fluids.EMPTY, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> COPPER_WATER_BUCKET = ITEMS.register("copper_water_bucket", ()-> new CopperBucketItem(()-> Fluids.WATER, new Item.Properties().craftRemainder(COPPER_BUCKET.get()).stacksTo(1)));
    public static final RegistryObject<Item> COPPER_MILK_BUCKET = ITEMS.register("copper_milk_bucket", ()-> new CopperMilkBucketItem((new Item.Properties()).craftRemainder(COPPER_BUCKET.get()).stacksTo(1)));

    public static final RegistryObject<Item> RAW_TENTACLE = ITEMS.register("raw_tentacle", ()-> new Item(new Item.Properties().food(ModFoods.RAW_TENTACLE)));
    public static final RegistryObject<Item> COOKED_TENTACLE = ITEMS.register("cooked_tentacle", ()-> new Item(new Item.Properties().food(ModFoods.COOKED_TENTACLE)));
    public static final RegistryObject<Item> SEA_SOUP = ITEMS.register("sea_soup", ()-> new BowlFoodItem(new Item.Properties().stacksTo(1).food(ModFoods.SEA_SOUP)));

    //COPPER TOOLS/THINGS
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", ()-> new SwordItem(ToolStats.COPPER,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", ()-> new AxeItem(ToolStats.COPPER,6.0F,-3.1F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", ()-> new PickaxeItem(ToolStats.COPPER,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", ()-> new ShovelItem(ToolStats.COPPER,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", ()-> new HoeItem(ToolStats.COPPER,-2,-1.0F, new Item.Properties()));

    //THINGS FOR STRAFER
    public static final RegistryObject<Item> SUGARCANE_ON_A_STICK = ITEMS.register("sugarcane_on_a_stick", ()-> new Item((new Item.Properties())));

    //COPPER ARMOR
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> CopperArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> CopperArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> CopperArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> CopperArmor.getInstance(ArmorItem.Type.BOOTS));

    //TURTLE
    public static final RegistryObject<Item> TURTLE_SHELL_CHESTPLATE = ITEMS.register("turtle_shell_chestplate", () -> new ArmorItem(ArmorMaterials.TURTLE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TURTLE_SHELL_LEGGINGS = ITEMS.register("turtle_shell_leggings", () -> new ArmorItem(ArmorMaterials.TURTLE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TURTLE_SHELL_BOOTS = ITEMS.register("turtle_shell_boots", () -> new ArmorItem(ArmorMaterials.TURTLE, ArmorItem.Type.BOOTS, new Item.Properties()));

    //HORSE ARMOR
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", () -> new HorseArmorItem(15, new ResourceLocation(MOD_ID, "textures/entity/horse/armor/horse_armor_netherite.png"), new Item.Properties().stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor", () -> new HorseArmorItem(4, new ResourceLocation(MOD_ID, "textures/entity/horse/armor/horse_armor_copper.png"), new Item.Properties().stacksTo(1).fireResistant()));

    //SPAWN EGGS
    public static final RegistryObject<ForgeSpawnEggItem> STRAFER_SPAWN_EGG = ITEMS.register("strafer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.STRAFER, 0x34dbeb, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> SULPHUR_ZOMBIE_SPAWN_EGG = ITEMS.register("sulphur_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SULPHUR_ZOMBIE, 0xDDB903, 0xF19E33, new Item.Properties()));

    //MAGIC MIRROR
    public static final RegistryObject<Item> MAGIC_MIRROR = ITEMS.register("magic_mirror", ()-> new MagicMirrorItem(new Item.Properties().durability(3).defaultDurability(3)));

    //COTTON
    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds", ()-> new ItemNameBlockItem(ModBlocks.COTTON_CROP.get(),new Item.Properties()));
    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton", () -> new Item(new Item.Properties()));

    //NLE CHOPPA
    public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new DynamiteItem(new Item.Properties()));
    public static final RegistryObject<Item> ECHO_BLASTER = ITEMS.register("echo_blaster", () -> new EchoBlasterItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    //ORES
    public static final RegistryObject<Item> SULPHUR_CHUNK = ITEMS.register("sulphur_chunk", () -> new Item(new Item.Properties()));

    //MUSIC DISCS
    public static final RegistryObject<Item> CALM_NIGHT_MUSIC_DISC = ITEMS.register("calm_night_music_disc", () -> new RecordItem(6, ModSounds.CALM_NIGHT, new Item.Properties().stacksTo(1), 1240));
    public static final RegistryObject<Item> COUNTRY_ROAD_MUSIC_DISC = ITEMS.register("country_road_music_disc", () -> new RecordItem(6, ModSounds.COUNTRY_ROAD, new Item.Properties().stacksTo(1), 3420));


    public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
}

