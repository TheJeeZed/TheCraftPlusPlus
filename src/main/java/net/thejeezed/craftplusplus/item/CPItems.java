package net.thejeezed.craftplusplus.item;

import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thejeezed.craftplusplus.CraftPlusPlus;

public class CPItems {
        public static final DeferredRegister<Item> ITEMS =
                DeferredRegister.create(ForgeRegistries.ITEMS, CraftPlusPlus.MOD_ID);

    public static final RegistryObject<Item> ITEM_HERE = ITEMS.register("item_here", ()-> new Item(new Item.Properties()));

    //Food Section(idk why i am putting a comment
    public static final RegistryObject<Item> RAW_TENTACLE = ITEMS.register("raw_tentacle", ()-> new Item(new Item.Properties().food(CPFoods.RAW_TENTACLE)));
    public static final RegistryObject<Item> COOKED_TENTACLE = ITEMS.register("cooked_tentacle", ()-> new Item(new Item.Properties().food(CPFoods.COOKED_TENTACLE)));
    public static final RegistryObject<Item> SEA_SOUP = ITEMS.register("sea_soup", ()-> new BowlFoodItem(new Item.Properties().stacksTo(1).food(CPFoods.SEA_SOUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
