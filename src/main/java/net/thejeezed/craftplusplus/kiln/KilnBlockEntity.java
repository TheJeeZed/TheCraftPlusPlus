package net.thejeezed.craftplusplus.kiln;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.thejeezed.craftplusplus.init.ModBlockEntities;
import net.thejeezed.craftplusplus.recipe.KilnRecipe;

import java.util.Map;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity  {
    private Map<Item, Integer> BURN_DURATION_MAP =
            Map.of(Items.COAL, 100,
                    Items.CHARCOAL, 100);

    public KilnBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.KILN_BE.get(), pPos, pBlockState, KilnRecipe.Type.INSTANCE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.craftplusplus.kiln");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new KilnMenu(pContainerId, pInventory, this, dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return BURN_DURATION_MAP.getOrDefault(pFuel.getItem(), 0);
    }
}
