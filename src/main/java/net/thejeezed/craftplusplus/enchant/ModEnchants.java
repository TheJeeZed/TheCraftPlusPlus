package net.thejeezed.craftplusplus.enchant;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.thejeezed.craftplusplus.CraftPlusPlus.MOD_ID;

public class ModEnchants {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static RegistryObject<Enchantment> SCREAMING = ENCHANTMENTS.register("screaming", () -> new ScreamingEnchant(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> QUICK_SHOCK = ENCHANTMENTS.register("quick_shock", () -> new QuickShockEnchant(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> SELF_CHARGING = ENCHANTMENTS.register("self_charging", () -> new SelfChargingEnchant(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
}
