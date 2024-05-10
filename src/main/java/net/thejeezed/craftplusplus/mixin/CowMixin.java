package net.thejeezed.craftplusplus.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.thejeezed.craftplusplus.client.gui.MessageRenderer;
import net.thejeezed.craftplusplus.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Cow.class)
public abstract class CowMixin
{
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Animal;mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;"))
    public InteractionResult mobInteract(Animal instance, Player player, InteractionHand hand)
    {
        MessageRenderer.renderMessage("We're calling the method!");
        ItemStack heldItem = player.getItemInHand(hand);
        player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);

        if (theCraftPlusPlus$isBucket(heldItem) && !instance.isBaby())
        {
            MessageRenderer.renderMessage("We got it!");
            ItemStack filledBucket = theCraftPlusPlus$getFilledBucket(heldItem, player);
            player.setItemInHand(hand, filledBucket);
            return InteractionResult.sidedSuccess(instance.level().isClientSide);
        }
        else
        {
            MessageRenderer.renderMessage("Failed to get Milk (no more dad)");
            return InteractionResult.FAIL;
        }
    }

    @Unique
    private boolean theCraftPlusPlus$isBucket(ItemStack item)
    {
        return item.is(ModItems.COPPER_BUCKET.get()) || item.is(Items.BUCKET);
    }

    @Unique
    private ItemStack theCraftPlusPlus$getFilledBucket(ItemStack bucket, Player player)
    {
        if (bucket.is(ModItems.COPPER_BUCKET.get()))
        {
            MessageRenderer.renderMessage("We're Copper baby!");
            return ItemUtils.createFilledResult(bucket, player, ModItems.COPPER_MILK_BUCKET.get().getDefaultInstance());
        }
        else
        {
            MessageRenderer.renderMessage("We're regular old bucket...");
            return ItemUtils.createFilledResult(bucket, player, Items.MILK_BUCKET.getDefaultInstance());
        }
    }
}