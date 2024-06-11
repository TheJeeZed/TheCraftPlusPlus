package net.thejeezed.craftplusplus.kiln;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;
import net.thejeezed.craftplusplus.recipe.KilnRecipe;
import net.thejeezed.craftplusplus.recipe.ModRecipes;

public class KilnMenu extends AbstractFurnaceMenu {
    public KilnMenu(int pContainerId, Inventory pPlayerInventory) {
        super(MenuType.BLAST_FURNACE, KilnRecipe.Type.INSTANCE, RecipeBookType.BLAST_FURNACE, pContainerId, pPlayerInventory);
    }

    public KilnMenu(int pContainerId, Inventory pPlayerInventory, Container pBlastFurnaceContainer, ContainerData pBlastFurnaceData) {
        super(MenuType.BLAST_FURNACE, KilnRecipe.Type.INSTANCE, RecipeBookType.BLAST_FURNACE, pContainerId, pPlayerInventory, pBlastFurnaceContainer, pBlastFurnaceData);
    }
}
