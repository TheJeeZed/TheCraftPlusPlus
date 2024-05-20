package net.thejeezed.craftplusplus.enums.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.enums.tools.ToolStats;

@MethodsReturnNonnullByDefault
public enum ArmorStats implements ArmorMaterial {
    COPPER("copper", 12, new int[] {2, 5, 5, 2}, ToolStats.COPPER.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 0.0f,
            0.0f, ToolStats.COPPER.getRepairIngredient());

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;
    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};
    ArmorStats(String name, int durabilityMultiplier, int[] protectAmounts, int enchantmentValue,
               SoundEvent equipSound, float toughness, float knockbackResistance,
               Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectAmounts = protectAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }
    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectAmounts[pType.ordinal()];
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
    @Override
    public String getName() {
        return CraftPlusPlus.MOD_ID + ":" + this.name;
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}