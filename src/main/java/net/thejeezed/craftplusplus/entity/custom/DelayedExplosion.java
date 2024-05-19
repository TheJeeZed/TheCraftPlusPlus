package net.thejeezed.craftplusplus.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class DelayedExplosion {
    private final ServerLevel serverLevel;
    private final double x, y, z;
    private final DynamiteProjectileEntity dynamite;
    private int ticks = 0;

    public DelayedExplosion(ServerLevel serverLevel, double x, double y, double z, DynamiteProjectileEntity dynamite) {
        this.serverLevel = serverLevel;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dynamite = dynamite;
    }

    @SubscribeEvent
    public void onTick(@NotNull TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticks++;
            if (ticks >= 60) {
                serverLevel.explode(null, x, y, z, 4.0F, Level.ExplosionInteraction.TNT);
                MinecraftForge.EVENT_BUS.unregister(this);
                this.dynamite.discard();
            }
        }
    }
}

