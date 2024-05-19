package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.thejeezed.craftplusplus.client.gui.MessageRenderer;
import org.jetbrains.annotations.NotNull;

public class MagicMirrorItem extends Item {
    public MagicMirrorItem(Properties pProperties) {
        super(pProperties);
    }

    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        try {
            if (player instanceof ServerPlayer serverPlayer) {
                MessageRenderer.renderMessage("Is instance of server player");
                BlockPos spawnpoint = serverPlayer.getRespawnPosition();
                if (serverPlayer.isPassenger()) {
                    MessageRenderer.renderMessage("Is Riding"); // TODO: MessageRenderer calls are for debugging and should be removed when we build.
                    serverPlayer.stopRiding();
                } else {
                    MessageRenderer.renderMessage("Not Riding");
                }
                assert spawnpoint != null;
                double x = spawnpoint.getX();
                double y = spawnpoint.getY();
                double z = spawnpoint.getZ();
                serverPlayer.teleportTo(x, y, z);
                serverPlayer.setDeltaMovement(0, 0, 0);
                serverPlayer.getCooldowns().addCooldown(this, 6000); // 5 Minutes - This will add a cooldown for all Magic Mirrors in the inventory, not just the item you used.
                pContext.getItemInHand().hurtAndBreak(1, serverPlayer, (player1) -> player1.broadcastBreakEvent(pContext.getHand()));
                MessageRenderer.renderMessage(String.valueOf(pContext.getItemInHand().getDamageValue()));
                return InteractionResult.CONSUME_PARTIAL;
            }
        } catch (Exception e) {
            // Log the exception or handle it properly
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }
}