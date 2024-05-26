package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class EchoBlasterItem extends Item {
    public EchoBlasterItem(Properties pProperties) {
        super(pProperties);
    }

    /**Code should be pretty easy to understand, I'll go through it tho
        * First just plays the sound easy
        * All the Vec3 things with the $$ is just Minecraft code
        * Rn particles only client side, meaning other players cant see you particle the particle (we gotta fix that I think I have a solution soon
        * Basically the particle is just played 12 or smth and then hurts at the end
        * for(int $$6 = 1; $$6 < Mth.floor($$4.length()) + 12; ++$$6) { you can see the range here, rn I have it at 12 blocks, please change to your liking
        * Then it does damage, damage code small mix between the Minecraft Code in the SonicBoom class adjusted to the gun
        * So yeah pretty simple :) (MINECRAFT FIX YOUR CODE PRETTY PLEASE)
        * If you want more in depth please message me ill explain more don't wanna yap ifykwim!
        * Prolly some of the worst code ive written in a while but kinda proud, showed my dad he didn't undertsand shit but thought it was pretty cool
     */

    //TODO Make particles client side!!!!
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand hand) {
        if (!player.getCooldowns().isOnCooldown(this) || player.isCreative()) {
            player.playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0F, 1.0F);

            Vec3 $$3 = player.position().add(0.0, player.getEyeHeight(), 0.0);
            Vec3 $$4 = player.getLookAngle();
            Vec3 $$5 = $$4.normalize();

            if (!pLevel.isClientSide) {
                double $$8 = 0.5 * (1.0 - player.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                double $$9 = 2.5 * (1.0 - player.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                player.push($$5.x() * $$9, $$5.y() * $$8, $$5.z() * $$9);

                for(int $$6 = 1; $$6 < Mth.floor($$4.length()) + 12; ++$$6) {
                    Vec3 $$7 = $$3.add($$5.scale((double)$$6));
                    List<Entity> entities = pLevel.getEntities(player, new AABB($$7.x - 1.0, $$7.y - 1.0, $$7.z - 1.0, $$7.x + 1.0, $$7.y + 1.0, $$7.z + 1.0));
                    for (Entity entity : entities) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).hurt(pLevel.damageSources().sonicBoom(player), 20.0F);
                        }
                    }
                }
            } else {
                for(int $$6 = 1; $$6 < Mth.floor($$4.length()) + 12; ++$$6) {
                    Vec3 $$7 = $$3.add($$5.scale((double)$$6));
                    pLevel.addParticle(ParticleTypes.SONIC_BOOM, $$7.x, $$7.y, $$7.z, 1, 0.0, 0.0);
                }
            }
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(this, 60);
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {return false;}
    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {return false;}
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, @NotNull ItemStack pRepairCandidate) {return false;}
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.craftplusplus.echo_blaster.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.craftplusplus.shift_for_info"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}



