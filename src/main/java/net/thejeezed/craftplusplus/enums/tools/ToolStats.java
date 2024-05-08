package net.thejeezed.craftplusplus.enums.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum ToolStats implements Tier {
    COPPER(2, 185, 8.0F, 2.0F, 15, Items.COPPER_INGOT);

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairMaterial;

    ToolStats(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Item repairMaterialIn)
    {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = Ingredient.of(repairMaterialIn);
    }
    @Override
    public int getUses() {
        return maxUses;
    }
    @Override
    public float getSpeed() {
        return efficiency;
    }
    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }
    @Override
    public int getLevel() {
        return harvestLevel;
    }
    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }
    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairMaterial;
    }
}
