package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.thejeezed.craftplusplus.client.gui.MessageRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MagicMirrorItem extends Item {
    public MagicMirrorItem(Integer uses, Properties pProperties) {
        super(pProperties);
    }


    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        assert player != null;
        ServerPlayer serverPlayer = Objects.requireNonNull(pContext.getPlayer().getServer()).getPlayerList().getPlayer(player.getUUID());
        assert serverPlayer != null;
        BlockPos spawnpoint = serverPlayer.getRespawnPosition();
        if(!pContext.getLevel().isClientSide()) {
            MessageRenderer.renderMessage("Not Client-Side");
            //TODO: teleport the player to spawn and maybe spice it up with particals and stuff
            double x = spawnpoint.getX();
            double y = spawnpoint.getY();
            double z = spawnpoint.getZ();
            if (serverPlayer.isPassenger()) {
                MessageRenderer.renderMessage("Is Riding");
                serverPlayer.stopRiding();
                serverPlayer.teleportTo(x,y,z);
                serverPlayer.setDeltaMovement(0, 0, 0);
                serverPlayer.getCooldowns().addCooldown(this, 20);
            } else {
                MessageRenderer.renderMessage("Not Riding");
                serverPlayer.teleportTo(x,y,z);
                serverPlayer.setDeltaMovement(0, 0, 0);
                serverPlayer.getCooldowns().addCooldown(this, 20);
            }

        } else {
            MessageRenderer.renderMessage("Is Client-Side");
            double x = spawnpoint.getX();
            double y = spawnpoint.getY();
            double z = spawnpoint.getZ();
            if (player.isPassenger()) {
                MessageRenderer.renderMessage("Is Riding");
                player.stopRiding();
                player.teleportTo(x,y,z);
                player.setDeltaMovement(0, 0, 0);
                player.getCooldowns().addCooldown(this, 20);
            } else {
                MessageRenderer.renderMessage("Not Riding");
                player.teleportTo(x,y,z);
                player.setDeltaMovement(0, 0, 0);
                player.getCooldowns().addCooldown(this, 20);
            }

        }
        //TODO:decrese durability by one upon each use
        return InteractionResult.SUCCESS;
    }
}
