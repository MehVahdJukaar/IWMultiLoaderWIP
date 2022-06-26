package com.ordana.immersive_weathering.registry.entities;

import com.ordana.immersive_weathering.registry.items.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.crafting.Ingredient;

public class FollowFlowerCrownGoal extends TemptGoal {

    private static final TargetingConditions TEMPTING_ENTITY_PREDICATE = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
    private final TargetingConditions predicate;
    private int cooldown;
    private final double speed;

    public FollowFlowerCrownGoal(PathfinderMob entity, double speed, Ingredient food, boolean canBeScared) {
        super(entity, speed, food, canBeScared);
        this.predicate = TEMPTING_ENTITY_PREDICATE.copy().selector(e -> e.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FLOWER_CROWN));
        this.speed = speed;
    }

    public boolean canUse() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        } else {
            this.player = this.mob.level.getNearestPlayer(this.predicate, this.mob);
            return this.player != null;
        }
    }

    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
        if (this.mob.distanceToSqr(this.player) < 6.25D) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.player, this.speed);
        }
    }
}