package net.thejeezed.pandorium.kiln;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.thejeezed.pandorium.init.ModMenuTypes;
import net.thejeezed.pandorium.recipe.KilnRecipe;

public class KilnMenu extends AbstractFurnaceMenu {


    public KilnMenu(int pContainerId, Inventory pPlayerInventory, Container container, ContainerData containerData) {
        super(ModMenuTypes.KILN_MENU.get(), KilnRecipe.Type.INSTANCE, RecipeBookType.FURNACE, pContainerId, pPlayerInventory, container, containerData);
    }

    public KilnMenu(int pContainerId, Inventory pPlayerInventory) {
        super(ModMenuTypes.KILN_MENU.get(), KilnRecipe.Type.INSTANCE, RecipeBookType.FURNACE, pContainerId, pPlayerInventory);
    }

    public KilnMenu(int pCpntainerId, Inventory pPlayerinventory, FriendlyByteBuf pBuffer) {
        this(pCpntainerId, pPlayerinventory);
    }


    @Override
    protected boolean isFuel(ItemStack pStack) {
        return true;
    }
}