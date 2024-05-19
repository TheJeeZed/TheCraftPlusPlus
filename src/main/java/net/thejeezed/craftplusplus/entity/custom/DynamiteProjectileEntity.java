package net.thejeezed.craftplusplus.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.init.ModItems;

public class DynamiteProjectileEntity extends ThrowableItemProjectile {
    private Level level;

    public DynamiteProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.level = pLevel;
    }

    public DynamiteProjectileEntity(Level pLevel) {
        super(ModEntities.DYNAMITE_PROJECTILE.get(), pLevel);
        this.level = pLevel;
    }

    public DynamiteProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.DYNAMITE_PROJECTILE.get(), livingEntity, pLevel);
        this.level = pLevel;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        BlockPos hitPos = pResult.getBlockPos();
        if (!this.level.isClientSide && this.level instanceof ServerLevel serverLevel) {
            MinecraftForge.EVENT_BUS.register(new DelayedExplosion(serverLevel, hitPos.getX(), hitPos.getY(), hitPos.getZ(), this));
        }
        this.setDeltaMovement(0, 0, 0);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        double hitX = entity.getX();
        double hitY = entity.getY();
        double hitZ = entity.getZ();
        if (!this.level.isClientSide && this.level instanceof ServerLevel serverLevel) {
            MinecraftForge.EVENT_BUS.register(new DelayedExplosion(serverLevel, hitX, hitY, hitZ, this));
        }
        this.setDeltaMovement(0, 0, 0);
    }
}