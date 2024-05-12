package net.thejeezed.craftplusplus.mob.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;
import net.thejeezed.craftplusplus.mob.SulphurZombieEntity;

public class SulphurZombieEvent extends EntityEvent {
    private final SulphurZombieEntity zombie;

    public SulphurZombieEvent(SulphurZombieEntity zombie) {
        super(zombie);
        this.zombie = zombie;
    }

    public SulphurZombieEntity getEntity() {
        return this.zombie;
    }

    @HasResult
    public static class SummonAidEvent extends net.minecraftforge.event.entity.living.ZombieEvent {
        private Zombie customSummonedAid;
        private final Level level;
        private final int x;
        private final int y;
        private final int z;
        private final LivingEntity attacker;
        private final double summonChance;

        public SummonAidEvent(Zombie zombie, Level level, int x, int y, int z, LivingEntity attacker, double summonChance) {
            super(zombie);
            this.level = level;
            this.x = x;
            this.y = y;
            this.z = z;
            this.attacker = attacker;
            this.summonChance = summonChance;
        }

        public Zombie getCustomSummonedAid() {
            return this.customSummonedAid;
        }

        public void setCustomSummonedAid(Zombie customSummonedAid) {
            this.customSummonedAid = customSummonedAid;
        }

        public Level getLevel() {
            return this.level;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getZ() {
            return this.z;
        }

        public LivingEntity getAttacker() {
            return this.attacker;
        }

        public double getSummonChance() {
            return this.summonChance;
        }
    }
}
