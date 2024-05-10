package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class MagicMirrorItem extends Item {
    public MagicMirrorItem(Properties pProperties) {
        super(pProperties);
    }


    public InteractionResult useOn(UseOnContext pContext) {
        //TODO: Check player spawnpoint
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        ServerPlayer serverPlayer = (ServerPlayer) pContext.getPlayer();
        ItemStack itemStack = pContext.getItemInHand();
        BlockPos spawnpoint = serverPlayer.getRespawnPosition();
        if(!pContext.getLevel().isClientSide()) {
            //TODO: teleport the player to spawn and maybe spice it up with particals and stuff
            double x = spawnpoint.getX();
            double y = spawnpoint.getY();
            double z = spawnpoint.getZ();
            if (player.isPassenger()) {
                player.stopRiding();
            }
            player.teleportTo(x,y,z);
            player.setDeltaMovement(0, 0, 0);
            player.getCooldowns().addCooldown(this, 20);
        }
        //TODO:decrese durability by one upon each use
        return InteractionResult.SUCCESS;
    }
}
