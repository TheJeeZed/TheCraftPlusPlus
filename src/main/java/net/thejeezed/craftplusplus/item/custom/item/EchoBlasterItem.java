package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.server.command.TextComponentHelper;
import net.thejeezed.craftplusplus.enchant.ModEnchants;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class EchoBlasterItem extends Item {
    public EchoBlasterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        int screamingEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.SCREAMING.get(), itemStack);
        int range = 15 + 5 * screamingEnchantmentLevel;

        int quickShockEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.QUICK_SHOCK.get(), itemStack);
        int cooldown = 60 - 20 * quickShockEnchantmentLevel;

        int selfChargingEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.SELF_CHARGING.get(), itemStack);
        boolean requiresEchoShard = selfChargingEnchantmentLevel == 0;

        if (!player.getCooldowns().isOnCooldown(this)) {
            boolean hasEchoShard = player.getInventory().contains(new ItemStack(Items.ECHO_SHARD));
            if ((hasEchoShard && requiresEchoShard) || !requiresEchoShard) {
                player.playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0F, 1.0F);

                Vec3 $$3 = player.position().add(0.0, player.getEyeHeight(), 0.0);
                Vec3 $$4 = player.getLookAngle();
                Vec3 $$5 = $$4.normalize();

                if (!pLevel.isClientSide) {
                    double $$8 = 0.5 * (1.0 - player.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    double $$9 = 2.5 * (1.0 - player.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    player.push($$5.x() * $$9, $$5.y() * $$8, $$5.z() * $$9);

                    for(int $$6 = 1; $$6 < Mth.floor($$4.length()) + range; ++$$6) {
                        Vec3 $$7 = $$3.add($$5.scale((double)$$6));
                        List<Entity> entities = pLevel.getEntities(player, new AABB($$7.x - 1.0, $$7.y - 1.0, $$7.z - 1.0, $$7.x + 1.0, $$7.y + 1.0, $$7.z + 1.0));
                        for (Entity entity : entities) {
                            if (entity instanceof LivingEntity) {
                                ((LivingEntity) entity).hurt(pLevel.damageSources().sonicBoom(player), 20.0F);
                            }
                        }
                    }
                } else {
                    for(int $$6 = 1; $$6 < Mth.floor($$4.length()) + range; ++$$6) {
                        Vec3 $$7 = $$3.add($$5.scale((double)$$6));
                        pLevel.addParticle(ParticleTypes.SONIC_BOOM, $$7.x, $$7.y, $$7.z, 1, 0.0, 0.0);
                    }
                }
                if (!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, cooldown);
                    if (requiresEchoShard) {
                        for (ItemStack stack : player.getInventory().items) {
                            if (stack.getItem() == Items.ECHO_SHARD) {
                                stack.shrink(1);
                                MutableComponent consumeMessage = TextComponentHelper.createComponentTranslation(player, "message.echo_shard_consume");
                                consumeMessage.withStyle(ChatFormatting.WHITE);
                                player.displayClientMessage(consumeMessage, true);
                                break;
                            }
                        }
                    }
                }
                return InteractionResultHolder.consume(player.getItemInHand(hand));
            } else {
                MutableComponent message = TextComponentHelper.createComponentTranslation(player, "message.failure_to_shoot");
                message.withStyle(ChatFormatting.RED);
                player.displayClientMessage(message, true);
                player.playNotifySound(SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 1.2f, 1f);
                return InteractionResultHolder.pass(player.getItemInHand(hand));
            }
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
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EnchantmentHelper.getEnchantments(book).containsKey(ModEnchants.SCREAMING.get()) || EnchantmentHelper.getEnchantments(book).containsKey(ModEnchants.QUICK_SHOCK.get()) || EnchantmentHelper.getEnchantments(book).containsKey(ModEnchants.SELF_CHARGING.get());
    }

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
