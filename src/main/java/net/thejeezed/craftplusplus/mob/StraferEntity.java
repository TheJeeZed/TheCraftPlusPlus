package net.thejeezed.craftplusplus.mob;

import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.init.ModItems;
import net.thejeezed.craftplusplus.tags.ModBlockTags;

public class StraferEntity extends Animal implements ItemSteerable, Saddleable {
    private static final UUID SUFFOCATING_MODIFIER_UUID = UUID.fromString("9e362924-01de-4ddd-a2b2-d0f7a405a174");
    private static final AttributeModifier SUFFOCATING_MODIFIER;
    private static final float SUFFOCATE_STEERING_MODIFIER = 0.35F;
    private static final float STEERING_MODIFIER = 0.55F;
    private static final Ingredient FOOD_ITEMS;
    private static final Ingredient TEMPT_ITEMS;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_SUFFOCATING;
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private final ItemBasedSteering steering;
    @Nullable
    private TemptGoal temptGoal;
    @Nullable
    private PanicGoal panicGoal;

    public StraferEntity(EntityType<StraferEntity> straferEntity, Level level) {
        super(straferEntity,level);
        this.steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
        this.blocksBuilding = true;
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public static boolean checkStriderSpawnRules(EntityType<StraferEntity> pStrider, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        BlockPos.MutableBlockPos $$5 = pPos.mutable();

        do {
            $$5.move(Direction.UP);
        } while(pLevel.getFluidState($$5).is(FluidTags.WATER));

        return pLevel.getBlockState($$5).isAir();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BOOST_TIME.equals(pKey) && this.level().isClientSide) {
            this.steering.onSynced();
        }

        super.onSyncedDataUpdated(pKey);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOOST_TIME, 0);
        this.entityData.define(DATA_SUFFOCATING, false);
        this.entityData.define(DATA_SADDLE_ID, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.steering.addAdditionalSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.steering.readAdditionalSaveData(pCompound);
    }

    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    public void equipSaddle(@Nullable SoundSource pSource) {
        this.steering.setSaddle(true);
        if (pSource != null) {
            this.level().playSound((Player)null, this, SoundEvents.STRIDER_SADDLE, pSource, 0.5F, 1.0F);
        }

    }

    protected void registerGoals() {
        this.panicGoal = new PanicGoal(this, 1.65);
        this.goalSelector.addGoal(1, this.panicGoal);
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, Ingredient.of(new ItemLike[]{ModItems.SUGARCANE_ON_A_STICK.get()}), false));
        this.temptGoal = new TemptGoal(this, 1.4, TEMPT_ITEMS, false);
        this.goalSelector.addGoal(3, this.temptGoal);
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0, 60));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, StraferEntity.class, 8.0F));
    }

    public void setSuffocating(boolean pSuffocating) {
        this.entityData.set(DATA_SUFFOCATING, pSuffocating);
        AttributeInstance $$1 = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if ($$1 != null) {
            $$1.removeModifier(SUFFOCATING_MODIFIER_UUID);
            if (pSuffocating) {
                $$1.addTransientModifier(SUFFOCATING_MODIFIER);
            }
        }

    }

    public boolean isSuffocating() {
        return (Boolean)this.entityData.get(DATA_SUFFOCATING);
    }

    public boolean canStandOnFluid(FluidState pFluidState) {
        return pFluidState.is(FluidTags.WATER);
    }

    public double getPassengersRidingOffset() {
        float $$0 = Math.min(0.25F, this.walkAnimation.speed());
        float $$1 = this.walkAnimation.position();
        return (double)this.getBbHeight() - 0.19 + (double)(0.12F * Mth.cos($$1 * 1.5F) * 2.0F * $$0);
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity var2 = this.getFirstPassenger();
        if (var2 instanceof Player $$0) {
            if ($$0.getMainHandItem().is(ModItems.SUGARCANE_ON_A_STICK.get()) || $$0.getOffhandItem().is(ModItems.SUGARCANE_ON_A_STICK.get())) {
                return $$0;
            }
        }

        return null;
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vec3[] $$1 = new Vec3[]{getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot()), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() - 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() + 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() - 45.0F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() + 45.0F)};
        Set<BlockPos> $$2 = Sets.newLinkedHashSet();
        double $$3 = this.getBoundingBox().maxY;
        double $$4 = this.getBoundingBox().minY - 0.5;
        BlockPos.MutableBlockPos $$5 = new BlockPos.MutableBlockPos();
        Vec3[] var9 = $$1;
        int var10 = $$1.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            Vec3 $$6 = var9[var11];
            $$5.set(this.getX() + $$6.x, $$3, this.getZ() + $$6.z);

            for(double $$7 = $$3; $$7 > $$4; --$$7) {
                $$2.add($$5.immutable());
                $$5.move(Direction.DOWN);
            }
        }

        Iterator var17 = $$2.iterator();

        while(true) {
            BlockPos $$8;
            double $$9;
            do {
                do {
                    if (!var17.hasNext()) {
                        return new Vec3(this.getX(), this.getBoundingBox().maxY, this.getZ());
                    }

                    $$8 = (BlockPos)var17.next();
                } while(this.level().getFluidState($$8).is(FluidTags.WATER));

                $$9 = this.level().getBlockFloorHeight($$8);
            } while(!DismountHelper.isBlockFloorValid($$9));

            Vec3 $$10 = Vec3.upFromBottomCenterOf($$8, $$9);
            UnmodifiableIterator var14 = pLivingEntity.getDismountPoses().iterator();

            while(var14.hasNext()) {
                Pose $$11 = (Pose)var14.next();
                AABB $$12 = pLivingEntity.getLocalBoundsForPose($$11);
                if (DismountHelper.canDismountTo(this.level(), pLivingEntity, $$12.move($$10))) {
                    pLivingEntity.setPose($$11);
                    return $$10;
                }
            }
        }
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        this.setRot(pPlayer.getYRot(), pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        this.steering.tickBoost();
        super.tickRidden(pPlayer, pTravelVector);
    }

    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        return new Vec3(0.0, 0.0, 1.0);
    }

    protected float getRiddenSpeed(Player pPlayer) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED) * (double)(this.isSuffocating() ? 0.35F : 0.55F) * (double)this.steering.boostFactor());
    }

    protected float nextStep() {
        return this.moveDist + 0.6F;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.isInWater() ? SoundEvents.STRIDER_STEP_LAVA : SoundEvents.STRIDER_STEP, 1.0F, 1.0F);
    }

    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        this.checkInsideBlocks();
        if (this.isInWater()) {
            this.resetFallDistance();
        } else {
            super.checkFallDamage(pY, pOnGround, pState, pPos);
        }
    }

    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
        if (this.isBeingTempted() && this.random.nextInt(140) == 0) {
            this.playSound(SoundEvents.STRIDER_HAPPY, 1.0F, this.getVoicePitch());
        } else if (this.isPanicking() && this.random.nextInt(60) == 0) {
            this.playSound(SoundEvents.STRIDER_RETREAT, 1.0F, this.getVoicePitch());
        }

        if (!this.isNoAi()) {
            boolean var10000;
            boolean $$2;
            label36: {
                BlockState $$0 = this.level().getBlockState(this.blockPosition());
                BlockState $$1 = this.getBlockStateOnLegacy();
                $$2 = $$0.is(ModBlockTags.STRAFER_WARM_BLOCKS) || $$1.is(BlockTags.STRIDER_WARM_BLOCKS) || this.getFluidHeight(FluidTags.WATER) > 0.0;
                Entity var6 = this.getVehicle();
                if (var6 instanceof StraferEntity) {
                    StraferEntity $$3 = (StraferEntity)var6;
                    if ($$3.isSuffocating()) {
                        var10000 = true;
                        break label36;
                    }
                }

                var10000 = false;
            }

            boolean $$4 = var10000;
            this.setSuffocating(!$$2 || $$4);
        }

        super.tick();
        this.floatStrider();
        this.checkInsideBlocks();
    }

    private boolean isPanicking() {
        return this.panicGoal != null && this.panicGoal.isRunning();
    }

    private boolean isBeingTempted() {
        return this.temptGoal != null && this.temptGoal.isRunning();
    }

    protected boolean shouldPassengersInheritMalus() {
        return true;
    }

    private void floatStrider() {
        if (this.isInWater()) {
            CollisionContext $$0 = CollisionContext.of(this);
            if ($$0.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setOnGround(true);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5).add(0.0, 0.05, 0.0));
            }
        }

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.17499999701976776).add(Attributes.FOLLOW_RANGE, 16.0);
    }

    protected SoundEvent getAmbientSound() {
        return !this.isPanicking() && !this.isBeingTempted() ? SoundEvents.STRIDER_AMBIENT : null;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.STRIDER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.STRIDER_DEATH;
    }

    protected boolean canAddPassenger(Entity pPassenger) {
        return !this.isVehicle() && !this.isEyeInFluid(FluidTags.WATER);
    }


    public boolean isOnFire() {
        return false;
    }


    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (pLevel.getBlockState(pPos).getFluidState().is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return this.isInWater() ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }

    @Nullable
    public StraferEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return (StraferEntity) ModEntities.STRAFER.get().create(pLevel);
    }

    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean $$2 = this.isFood(pPlayer.getItemInHand(pHand));
        if (!$$2 && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            InteractionResult $$3 = super.mobInteract(pPlayer, pHand);
            if (!$$3.consumesAction()) {
                ItemStack $$4 = pPlayer.getItemInHand(pHand);
                return $$4.is(Items.SADDLE) ? $$4.interactLivingEntity(pPlayer, this, pHand) : InteractionResult.PASS;
            } else {
                if ($$2 && !this.isSilent()) {
                    this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.STRIDER_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                }

                return $$3;
            }
        }
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (this.isBaby()) {
            return super.finalizeSpawn(pLevel, pDifficulty, pReason, (SpawnGroupData)pSpawnData, pDataTag);
        } else {
            RandomSource $$5 = pLevel.getRandom();
            if ($$5.nextInt(30) == 0) {
                Mob $$6 = (Mob)EntityType.DROWNED.create(pLevel.getLevel());
                if ($$6 != null) {
                    pSpawnData = this.spawnJockey(pLevel, pDifficulty, $$6, new Zombie.ZombieGroupData(Zombie.getSpawnAsBabyOdds($$5), false));
                    $$6.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SUGARCANE_ON_A_STICK.get()));
                    this.equipSaddle((SoundSource)null);
                }
            } else if ($$5.nextInt(10) == 0) {
                AgeableMob $$7 = (AgeableMob)ModEntities.STRAFER.get().create(pLevel.getLevel());
                if ($$7 != null) {
                    $$7.setAge(-24000);
                    pSpawnData = this.spawnJockey(pLevel, pDifficulty, $$7, (SpawnGroupData)null);
                }
            } else {
                pSpawnData = new AgeableMob.AgeableMobGroupData(0.5F);
            }

            return super.finalizeSpawn(pLevel, pDifficulty, pReason, (SpawnGroupData)pSpawnData, pDataTag);
        }
    }

    private SpawnGroupData spawnJockey(ServerLevelAccessor pServerLevel, DifficultyInstance pDifficulty, Mob pJockey, @Nullable SpawnGroupData pSpawnData) {
        pJockey.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
        pJockey.finalizeSpawn(pServerLevel, pDifficulty, MobSpawnType.JOCKEY, pSpawnData, (CompoundTag)null);
        pJockey.startRiding(this, true);
        return new AgeableMob.AgeableMobGroupData(0.0F);
    }

    static {
        SUFFOCATING_MODIFIER = new AttributeModifier(SUFFOCATING_MODIFIER_UUID, "Strider suffocating modifier", -0.3400000035762787, Operation.MULTIPLY_BASE);
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.SUGAR_CANE});
        TEMPT_ITEMS = Ingredient.of(new ItemLike[]{Items.SUGAR_CANE, ModItems.SUGARCANE_ON_A_STICK.get()});
        DATA_BOOST_TIME = SynchedEntityData.defineId(StraferEntity.class, EntityDataSerializers.INT);
        DATA_SUFFOCATING = SynchedEntityData.defineId(StraferEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_SADDLE_ID = SynchedEntityData.defineId(StraferEntity.class, EntityDataSerializers.BOOLEAN);
    }

    public static class StraferGoToWaterGoal extends MoveToBlockGoal {
        private final StraferEntity strafer;

        StraferGoToWaterGoal(StraferEntity pStrafer, double pSpeedModifier) {
            super(pStrafer, pSpeedModifier, 8, 2);
            this.strafer = pStrafer;
        }

        public BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        public boolean canContinueToUse() {
            return !this.strafer.isInWater() && this.isValidTarget(this.strafer.level(), this.blockPos);
        }

        public boolean canUse() {
            return !this.strafer.isInWater() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 20 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER) && pLevel.getBlockState(pPos.above()).isPathfindable(pLevel, pPos, PathComputationType.LAND);
        }
    }

    public static class StraferPathNavigation extends GroundPathNavigation {
        StraferPathNavigation(StraferEntity pStrider, Level pLevel) {
            super(pStrider, pLevel);
        }

        protected PathFinder createPathFinder(int pMaxVisitedNodes) {
            this.nodeEvaluator = new WalkNodeEvaluator();
            this.nodeEvaluator.setCanPassDoors(true);
            return new PathFinder(this.nodeEvaluator, pMaxVisitedNodes);
        }

        protected boolean hasValidPathType(BlockPathTypes pPathType) {
            return pPathType != BlockPathTypes.WATER && pPathType != BlockPathTypes.DAMAGE_FIRE && pPathType != BlockPathTypes.DANGER_FIRE ? super.hasValidPathType(pPathType) : true;
        }

        public boolean isStableDestination(BlockPos pPos) {
            return this.level.getBlockState(pPos).is(Blocks.WATER) || super.isStableDestination(pPos);
        }
    }
}