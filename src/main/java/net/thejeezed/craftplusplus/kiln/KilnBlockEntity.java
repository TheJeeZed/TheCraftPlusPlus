package net.thejeezed.craftplusplus.kiln;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.thejeezed.craftplusplus.init.ModBlockEntities;
import net.thejeezed.craftplusplus.recipe.ModRecipes;

import java.util.Optional;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.KILN_BE.get(), pPos, pBlockState, RecipeType.BLASTING);
    }


    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.craftplusplus.kiln");
    }


    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return super.getBurnDuration(pFuel) / 2;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new KilnMenu(pId, pPlayer, this, this.dataAccess);
    }
}
