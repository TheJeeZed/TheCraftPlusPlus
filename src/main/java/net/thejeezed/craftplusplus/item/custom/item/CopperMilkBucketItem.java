package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.thejeezed.craftplusplus.init.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperMilkBucketItem extends Item
{
    private static final int DRINK_DURATION = 32;

    public CopperMilkBucketItem(Item.Properties pProperties) {
        super(pProperties);
    }

    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            pEntityLiving.curePotionEffects(pStack);
        }

        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild && pStack.getCount() == 1)
        {
            pStack.shrink(1);
        }

        if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild && pStack.getCount() == 2)
        {
            pStack.shrink(1);
            ((Player) pEntityLiving).addItem(new ItemStack(ModItems.COPPER_BUCKET.get()));
        }

        return pStack.isEmpty() ? new ItemStack(ModItems.COPPER_BUCKET.get()) : pStack;
    }

    public int getUseDuration(@NotNull ItemStack pStack) {
        return 32;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidBucketWrapper(stack);
    }
}
