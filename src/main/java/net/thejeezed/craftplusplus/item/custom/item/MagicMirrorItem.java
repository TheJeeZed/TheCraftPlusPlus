package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thejeezed.craftplusplus.client.gui.MessageRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
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

            BlockPos spawnpoint = serverPlayer.getRespawnPosition();
            BlockPos worldSpawn = world.getSharedSpawnPos();

            if (serverPlayer.isPassenger()) {
                serverPlayer.stopRiding();
            }

            if (spawnpoint != null)
            {
                Optional<Vec3> respawnPosition = Player.findRespawnPositionAndUseSpawnBlock(
                        world,
                        Objects.requireNonNull(serverPlayer.getRespawnPosition()),
                        serverPlayer.getYRot(),
                        serverPlayer.isRespawnForced(),
                        true
                );

                if (serverPlayer.getRespawnDimension() != serverPlayer.serverLevel().dimension())
                {
                    MessageRenderer.renderMessage("Not in same world!");
                    ServerLevel targetWorld = serverPlayer.server.getLevel(serverPlayer.getRespawnDimension());
                    assert targetWorld != null;
                    serverPlayer.changeDimension(targetWorld);

                    double x;
                    double y;
                    double z;

                    if (respawnPosition.isPresent())
                    {
                        x = respawnPosition.get().x;
                        y = respawnPosition.get().y;
                        z = respawnPosition.get().z;
                        serverPlayer.teleportTo(x, y, z);
                    } else {
                        Vec3 worldedSpawn = worldSpawn.getCenter();
                        x = worldedSpawn.x;
                        y = worldedSpawn.y;
                        z = worldedSpawn.z;
                        serverPlayer.teleportTo(x, y, z);
                    }

                } else {
                    teleportToSpawn(serverPlayer, respawnPosition, worldSpawn.getCenter());
                }
            }

            serverPlayer.setDeltaMovement(0, 0, 0);

            if (!player.isCreative())
            {
                serverPlayer.getCooldowns().addCooldown(this, 200);
            }

            player.getItemInHand(pUsedHand).hurtAndBreak(1, serverPlayer, (player1) -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }
        return super.use(pLevel, player, pUsedHand);
    }

    private static void teleportToSpawn(ServerPlayer player, Optional<Vec3> respawnPosition, Vec3 worldSpawn)
    {
        double x;
        double y;
        double z;
        if (respawnPosition.isPresent())
        {
            x = respawnPosition.get().x;
            y = respawnPosition.get().y;
            z = respawnPosition.get().z;
        } else {
            x = worldSpawn.x;
            y = worldSpawn.y;
            z = worldSpawn.z;
        }
        player.teleportTo(x ,y, z);
    }
}