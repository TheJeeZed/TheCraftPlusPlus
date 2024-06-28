package net.thejeezed.craftplusplus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.enchant.ModEnchants;

import java.util.List;

@Mod.EventBusSubscriber(modid = CraftPlusPlus.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addVillagerTrade(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ItemStack quickShock = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchants.QUICK_SHOCK.get(), 1));
        ItemStack screaming = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchants.SCREAMING.get(), 1));
        ItemStack selfCharging = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchants.SELF_CHARGING.get(), 1));
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                    quickShock,
                    5, 8, 0.02f
            ));
            if (event.getType() == VillagerProfession.LIBRARIAN) {
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                        screaming,
                        5, 8, 0.02f
                ));
                if (event.getType() == VillagerProfession.LIBRARIAN) {
                    trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                            selfCharging,
                            5, 8, 0.02f
                    ));
                }
            }
        }
    }
}
