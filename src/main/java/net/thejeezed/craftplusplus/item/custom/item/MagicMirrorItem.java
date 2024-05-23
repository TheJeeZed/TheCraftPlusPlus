package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MagicMirrorItem extends Item {
    public MagicMirrorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player player, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && player instanceof ServerPlayer serverPlayer)
        {
            ServerLevel world = (ServerLevel) pLevel;

            if (!player.getAbilities().instabuild && !player.isCreative())
            {
                player.getCooldowns().addCooldown(this, 1200);
            }

            if (serverPlayer.getRespawnPosition() != null) {
                Optional<Vec3> respawnPosition = Player.findRespawnPositionAndUseSpawnBlock(
                        world,
                        serverPlayer.getRespawnPosition(),
                        serverPlayer.getYRot(),
                        serverPlayer.isRespawnForced(),
                        true
                );

                if (respawnPosition.isPresent()) {
                    ServerLevel respawnWorld = world.getServer().getLevel(serverPlayer.getRespawnDimension());

                    if (respawnWorld != null) {
                        serverPlayer.teleportTo(
                                respawnWorld,
                                respawnPosition.get().x,
                                respawnPosition.get().y,
                                respawnPosition.get().z,
                                serverPlayer.getYRot(),
                                serverPlayer.getXRot()
                        );

                        if (!player.isCreative())
                        {
                            serverPlayer.getCooldowns().addCooldown(this, 200);
                        }

                        player.getItemInHand(pUsedHand).hurtAndBreak(1, player,
                                player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                } else {
                    serverPlayer.displayClientMessage(Component.translatable("magic_mirror.craftplusplus.failure_to_teleport").withStyle(ChatFormatting.RED), false);
                }
            } else {
                serverPlayer.displayClientMessage(Component.translatable("magic_mirror.craftplusplus.failure_to_teleport").withStyle(ChatFormatting.RED), false);
            }
        }
        return super.use(pLevel, player, pUsedHand);
    }
}