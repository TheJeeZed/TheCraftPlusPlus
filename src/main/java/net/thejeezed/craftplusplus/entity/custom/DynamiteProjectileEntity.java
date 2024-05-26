package net.thejeezed.craftplusplus.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.MinecraftForge;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.init.ModItems;
import org.jetbrains.annotations.NotNull;

public class DynamiteProjectileEntity extends ThrowableItemProjectile {
    private final Level level;
    private int ticksLived = 0;
    private boolean hasExploded = false;
    private boolean explosionRegistered = false;

    public DynamiteProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.level = pLevel;
    }

    public DynamiteProjectileEntity(Level pLevel) {
        super(ModEntities.DYNAMITE_PROJECTILE.get(), pLevel);
        this.level = pLevel;
    }

    public DynamiteProjectileEntity(Level pLevel, Player player) {
        super(ModEntities.DYNAMITE_PROJECTILE.get(), player, pLevel);
        this.level = pLevel;
    }


    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.DYNAMITE.get();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        BlockPos hitPos = pResult.getBlockPos();
        if (!this.level.isClientSide && this.level instanceof ServerLevel serverLevel && !explosionRegistered) {
            MinecraftForge.EVENT_BUS.register(new DelayedExplosion(serverLevel, hitPos.getX(), hitPos.getY(), hitPos.getZ(), this));
            explosionRegistered = true;
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        double hitX = entity.getX();
        double hitY = entity.getY();
        double hitZ = entity.getZ();
        if (!this.level.isClientSide && this.level instanceof ServerLevel serverLevel && !explosionRegistered) {
            MinecraftForge.EVENT_BUS.register(new DelayedExplosion(serverLevel, hitX, hitY, hitZ, this));
            explosionRegistered = true;
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.ticksLived++;
        if (!this.level.isClientSide && this.level instanceof ServerLevel serverLevel) {
            if (this.ticksLived >= 60 && !this.hasExploded) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                serverLevel.explode(null, x, y, z, 2.0F, Level.ExplosionInteraction.TNT);
                this.hasExploded = true;
                this.discard();
            }
        }
    }
}