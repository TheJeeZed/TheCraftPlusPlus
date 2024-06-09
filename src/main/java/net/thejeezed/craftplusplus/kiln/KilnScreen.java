package net.thejeezed.craftplusplus.kiln;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/blast_furnace.png");

    public KilnScreen(KilnMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new BlastingRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE);
    }
}
