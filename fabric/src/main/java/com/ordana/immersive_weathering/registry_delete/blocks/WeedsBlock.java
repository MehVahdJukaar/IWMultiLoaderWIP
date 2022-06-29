package com.ordana.immersive_weathering.registry_delete.blocks;

import com.ordana.immersive_weathering.ImmersiveWeatheringFabric;
import com.ordana.immersive_weathering.registry_delete.ModTags;
import com.ordana.immersive_weathering.registry_delete.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.entity.passive.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Random;

public class WeedsBlock extends CropBlock {

    public WeedsBlock(Properties settings) {
        super(settings);
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !(entity instanceof Fox || entity instanceof Bee || entity instanceof Sheep || entity instanceof Cat || entity instanceof Villager)) {
            if (!world.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                if (entity instanceof Player player && !player.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && ImmersiveWeatheringFabric.getConfig().leavesConfig.leggingsPreventThornDamage) {
                    return;
                } else if (entity instanceof Player player) {
                    double d = Math.abs(entity.getX() - entity.xOld);
                    double e = Math.abs(entity.getZ() - entity.zOld);
                    if (d >= 0.003000000026077032D || e >= 0.003000000026077032D) {
                        entity.hurt(DamageSource.SWEET_BERRY_BUSH, 0.5F);
                    }
                }
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        int i = this.getAge(state);
        if (i < this.getMaxAge()) {
            float f = getGrowthSpeed(this, world, pos);
            if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                world.setBlock(pos, this.getStateForAge(i + 1), 2);
            }
        }
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        if (this.getAge(state) == this.getMaxAge() && random.nextInt(10)==0) {
            double r = 0.3;
            double x = (double) pos.getX() + 0.5 + (random.nextDouble() - 0.5) * r;
            double y = (double) pos.getY() + 0.8 + (random.nextDouble() - 0.5) * r;
            double z = (double) pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * r;
            world.addParticle(ParticleTypes.WHITE_ASH, x, y, z, 0.1D, 0.5D, 0.1D);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos below = pos.below();
        return this.mayPlaceOn(world.getBlockState(below), world, below);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModTags.FERTILE_BLOCKS) || floor.is(BlockTags.DIRT) || floor.is(ModTags.CRACKED) || floor.is(ModTags.MOSSY);
    }
}
