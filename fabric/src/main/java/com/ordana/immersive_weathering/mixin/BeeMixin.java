package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.registry.entities.FollowFlowerCrownGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bee.class)
public abstract class BeeMixin extends Animal {

    protected BeeMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    protected void initGoals(CallbackInfo ci) {
        this.beeGoalHelper(this);
    }

    private void beeGoalHelper(Animal animal){
        this.goalSelector.addGoal(3, new FollowFlowerCrownGoal(animal, 1D, null, false));
    }

    @Nullable
    @Shadow
    public abstract AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity);
}
