package net.thejeezed.craftplusplus.item.custom.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.thejeezed.craftplusplus.enums.armor.ArmorStats;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


public class CopperArmor extends ArmorItem {
    private CopperArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    @Contract("_ -> new")
    public static @NotNull CopperArmor getInstance(Type ptype) {
        return new CopperArmor(ArmorStats.COPPER, ptype, new Properties().stacksTo(1));
    }
}
