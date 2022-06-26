package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.entities.WeedEatGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal {

    protected RabbitMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    protected void initGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(ModBlocks.WEEDS), false));
        this.rabbitGoalHelper(this);
    }

    @Inject(method = "isTempting", at = @At("HEAD"), cancellable = true)
    private static void isTempting(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(Items.CARROT) || stack.is(Items.GOLDEN_CARROT) || stack.is(Blocks.DANDELION.asItem()) || stack.is(ModBlocks.WEEDS.asItem())) {
            cir.setReturnValue(true);
        }
    }

    private void rabbitGoalHelper(Animal animal){
        this.goalSelector.addGoal(5, new WeedEatGoal(animal));
    }

    @Nullable
    @Shadow
    public abstract AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity);
}
