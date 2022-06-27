package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.reg.ModBlocks;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal {

    protected RabbitMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Ingredient;of([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;"))
    private Ingredient isTempting(ItemLike[] itemLikes) {
        var list = new ArrayList<>(List.of(itemLikes));
        list.add(ModBlocks.WEEDS.get());
        return Ingredient.of(list.toArray(new ItemLike[itemLikes.length]));
    }
}
