package net.thejeezed.craftplusplus.item;

import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS =
                DeferredRegister.create(ForgeRegistries.ITEMS, CraftPlusPlus.MOD_ID);

    public static final RegistryObject<Item> ITEM_HERE = ITEMS.register("item_here", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COOPER_BUCKET = ITEMS.register("cooper_bucket", ()-> new BucketItem(Fluids.WATER, new Item.Properties().stacksTo(1)));

    //Food Section(idk why i am putting a comment
    public static final RegistryObject<Item> RAW_TENTACLE = ITEMS.register("raw_tentacle", ()-> new Item(new Item.Properties().food(ModFoods.RAW_TENTACLE)));
    public static final RegistryObject<Item> COOKED_TENTACLE = ITEMS.register("cooked_tentacle", ()-> new Item(new Item.Properties().food(ModFoods.COOKED_TENTACLE)));
    public static final RegistryObject<Item> SEA_SOUP = ITEMS.register("sea_soup", ()-> new BowlFoodItem(new Item.Properties().stacksTo(1).food(ModFoods.SEA_SOUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
