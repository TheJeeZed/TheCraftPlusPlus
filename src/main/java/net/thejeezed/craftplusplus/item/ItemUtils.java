package net.thejeezed.craftplusplus.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class ItemUtils
{
    public ItemUtils() {
    }

    public static InteractionResultHolder<ItemStack> startUsingInstantly(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
    }

    public static ItemStack createFilledResult(ItemStack pEmptyStack, Player pPlayer, ItemStack pFilledStack, boolean pPreventDuplicates) {
        boolean $$4 = pPlayer.getAbilities().instabuild;
        if (pPreventDuplicates && $$4) {
            if (!pPlayer.getInventory().contains(pFilledStack)) {
                pPlayer.getInventory().add(pFilledStack);
            }

            return pEmptyStack;
        } else {
            if (!$$4) {
                pEmptyStack.shrink(1);
            }

            if (pEmptyStack.isEmpty()) {
                return pFilledStack;
            } else {
                if (!pPlayer.getInventory().add(pFilledStack)) {
                    pPlayer.drop(pFilledStack, false);
                }

                return pEmptyStack;
            }
        }
    }

    public static ItemStack createFilledResult(ItemStack pEmptyStack, Player pPlayer, ItemStack pFilledStack, Item item, boolean pPreventDuplicates) {
        boolean $$4 = pPlayer.getAbilities().instabuild;
        if (pPreventDuplicates && $$4) {
            if (!pPlayer.getInventory().contains(pFilledStack)) {
                pPlayer.getInventory().add(pFilledStack);
            }

            return pEmptyStack;
        } else {
            if (!$$4) {
                pEmptyStack.shrink(1);
            }

            if (pEmptyStack.isEmpty()) {
                return new ItemStack(item, pFilledStack.getCount());
            } else {
                if (!pPlayer.getInventory().add(new ItemStack(item, pFilledStack.getCount()))) {
                    pPlayer.drop(new ItemStack(item, pFilledStack.getCount()), false);
                }

                return pEmptyStack;
            }
        }
    }

    public static void onContainerDestroyed(ItemEntity pItemEntity, Stream<ItemStack> pContainerContents) {
        Level $$2 = pItemEntity.level();
        if (!$$2.isClientSide) {
            pContainerContents.forEach((p_289504_) -> {
                $$2.addFreshEntity(new ItemEntity($$2, pItemEntity.getX(), pItemEntity.getY(), pItemEntity.getZ(), p_289504_));
            });
        }
    }
}
