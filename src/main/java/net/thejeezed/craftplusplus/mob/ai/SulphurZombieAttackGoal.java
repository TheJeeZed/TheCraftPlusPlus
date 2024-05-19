package net.thejeezed.craftplusplus.mob.ai;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.thejeezed.craftplusplus.mob.SulphurZombieEntity;

public class SulphurZombieAttackGoal extends MeleeAttackGoal {
    private final SulphurZombieEntity zombie;
    private int raiseArmTicks;

    public SulphurZombieAttackGoal(SulphurZombieEntity pZombie, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pZombie, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.zombie = pZombie;
    }

    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    public void stop() {
        super.stop();
        this.zombie.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        this.zombie.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);

    }
}
