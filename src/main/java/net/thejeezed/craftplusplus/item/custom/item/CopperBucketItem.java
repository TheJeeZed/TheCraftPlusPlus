package net.thejeezed.craftplusplus.item.custom.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.thejeezed.craftplusplus.client.gui.MessageRenderer;
import net.thejeezed.craftplusplus.util.ItemUtils;
import net.thejeezed.craftplusplus.init.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class CopperBucketItem extends Item implements DispensibleContainerItem
{
    private final Fluid content;
    private final Supplier<? extends Fluid> fluidSupplier;

    public CopperBucketItem(Supplier<? extends Fluid> supplier, Item.Properties builder) {
        super(builder);
        this.content = null;
        this.fluidSupplier = supplier;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, this.content == Fluids.EMPTY ? net.minecraft.world.level.ClipContext.Fluid.SOURCE_ONLY : net.minecraft.world.level.ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(pPlayer, pLevel, itemstack, blockhitresult);
        if (ret != null) {
            return ret;
        } else if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos1, direction, itemstack)) {
                BlockState blockstate1;
                if (this.content == Fluids.EMPTY) {
                    blockstate1 = pLevel.getBlockState(blockpos);
                    if (!blockstate1.getFluidState().is(Fluids.LAVA)) {
                        if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
                            ItemStack itemstack1 = bucketpickup.pickupBlock(pLevel, blockpos, blockstate1);
                            if (!itemstack1.isEmpty()) {
                                pPlayer.awardStat(Stats.ITEM_USED.get(this));
                                bucketpickup.getPickupSound(blockstate1).ifPresent((p_150709_) -> pPlayer.playSound(p_150709_, 1.0F, 1.0F));
                                pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                                ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemstack1, ModItems.COPPER_WATER_BUCKET.get(), true);
                                if (!pLevel.isClientSide) {
                                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) pPlayer, itemstack1);
                                }

                                return InteractionResultHolder.sidedSuccess(itemstack2, pLevel.isClientSide());
                            }
                        }
                    }
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    blockstate1 = pLevel.getBlockState(blockpos);
                    BlockPos blockpos2 = this.canBlockContainFluid(pLevel, blockpos, blockstate1) ? blockpos : blockpos1;
                    if (this.emptyContents(pPlayer, pLevel, blockpos2, blockhitresult, itemstack)) {
                        this.checkExtraContent(pPlayer, pLevel, itemstack, blockpos2);
                        if (pPlayer instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)pPlayer, blockpos2, itemstack);
                        }

                        pPlayer.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, pPlayer), pLevel.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack itemStack, @NotNull Player pPlayer, @NotNull LivingEntity pLivingEntity, @NotNull InteractionHand pHand) {
        if (pLivingEntity instanceof Cow cow && !cow.isBaby()) {
            pPlayer.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack milk_bucket = ItemUtils.createMilkResult(itemStack, pPlayer, ModItems.COPPER_MILK_BUCKET.get(), false);
            if (!pPlayer.addItem(milk_bucket)) {
                pPlayer.drop(milk_bucket, false);
            } else {
                MessageRenderer.renderMessage("message.copper_bucket.milked");
            }
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResult.sidedSuccess(cow.level().isClientSide());
        }

        return super.interactLivingEntity(itemStack, pPlayer, pLivingEntity, pHand);
    }

    public static ItemStack getEmptySuccessItem(ItemStack pBucketStack, @NotNull Player pPlayer) {
        return !pPlayer.getAbilities().instabuild ? new ItemStack(ModItems.COPPER_BUCKET.get()) : pBucketStack;
    }

    public void checkExtraContent(@Nullable Player pPlayer, @NotNull Level pLevel, @NotNull ItemStack pContainerStack, @NotNull BlockPos pPos) {
    }

    @Override
    public boolean emptyContents(@org.jetbrains.annotations.Nullable Player player, @NotNull Level level, @NotNull BlockPos blockPos, @Nullable BlockHitResult blockHitResult) {
        return false;
    }

    public boolean emptyContents(@Nullable Player player, Level level, BlockPos blockPos, @Nullable BlockHitResult blockHitResult, @Nullable ItemStack container) {
        if (!(this.content instanceof FlowingFluid)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(blockPos);
            Block block = blockstate.getBlock();
            boolean flag = blockstate.canBeReplaced(this.content);
            boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(level, blockPos, blockstate, this.content);
            Optional<FluidStack> containedFluidStack = Optional.ofNullable(container).flatMap(FluidUtil::getFluidContained);
            if (!flag1) {
                return blockHitResult != null && this.emptyContents(player, level, blockHitResult.getBlockPos().relative(blockHitResult.getDirection()), null, container);
            } else if (containedFluidStack.isPresent() && this.content.getFluidType().isVaporizedOnPlacement(level, blockPos, containedFluidStack.get())) {
                this.content.getFluidType().onVaporize(player, level, blockPos, containedFluidStack.get());
                return true;
            } else if (level.dimensionType().ultraWarm() && this.content.is(FluidTags.WATER)) {
                int i = blockPos.getX();
                int j = blockPos.getY();
                int k = blockPos.getZ();
                level.playSound(player, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);

                for(int l = 0; l < 8; ++l) {
                    level.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
                }

                return true;
            } else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(level, blockPos, blockstate, this.content)) {
                ((LiquidBlockContainer)block).placeLiquid(level, blockPos, blockstate, ((FlowingFluid)this.content).getSource(false));
                this.playEmptySound(player, level, blockPos);
                return true;
            } else {
                if (!level.isClientSide && flag && !blockstate.liquid()) { //TODO .liquid() is @Depicted
                    level.destroyBlock(blockPos, true);
                }

                if (!level.setBlock(blockPos, this.content.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
                    return false;
                } else {
                    this.playEmptySound(player, level, blockPos);
                    return true;
                }
            }
        }
    }

    protected void playEmptySound(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos) {
        assert this.content != null;
        SoundEvent soundevent = this.content.getFluidType().getSound(pPlayer, pLevel, pPos, SoundActions.BUCKET_EMPTY);
        if (soundevent == null) {
            soundevent = this.content.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
        }

        pLevel.playSound(pPlayer, pPos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
        pLevel.gameEvent(pPlayer, GameEvent.FLUID_PLACE, pPos);
    }

    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.getClass() == CopperBucketItem.class ? new FluidBucketWrapper(stack) : super.initCapabilities(stack, nbt);
    }

    public Fluid getFluid() {
        return this.fluidSupplier.get();
    }

    protected boolean canBlockContainFluid(Level worldIn, BlockPos posIn, @NotNull BlockState blockstate) {
        if (!(blockstate.getBlock() instanceof LiquidBlockContainer)) return false;
        assert this.content != null;
        return ((LiquidBlockContainer)blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.content);
    }
}
