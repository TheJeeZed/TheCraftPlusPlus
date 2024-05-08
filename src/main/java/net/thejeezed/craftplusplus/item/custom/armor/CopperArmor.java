package net.thejeezed.craftplusplus.item.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.thejeezed.craftplusplus.enums.armor.ArmorStats;


import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CopperArmor extends ArmorItem {
    private CopperArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static CopperArmor getInstance(Type ptype) {
        return new CopperArmor(ArmorStats.COPPER, ptype, new Properties().stacksTo(1));
    }
}
