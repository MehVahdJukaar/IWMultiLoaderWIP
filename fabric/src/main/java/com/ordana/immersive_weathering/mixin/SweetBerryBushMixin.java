package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushMixin {

    @Redirect(method = "onEntityCollision",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean onEntityCollision(Entity entity, DamageSource source, float amount) {
        if(entity instanceof Player player && !player.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && ImmersiveWeathering.getConfig().leavesConfig.leggingsPreventThornDamage){
            return false;
        }
        else{
            return entity.hurt(source, amount);
        }
    }
}