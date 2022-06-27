package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.blocks.FulguriteBlock;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.blocks.WeatheringHelper;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningEntityMixin extends Entity {

    public LightningEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    protected abstract BlockPos getAffectedBlockPos();

    @Inject(method = "powerLightningRod", at = @At("HEAD"))
    private void powerLightningRod(CallbackInfo ci) {
        BlockPos blockPos = this.getAffectedBlockPos();
        BlockState blockState = this.level.getBlockState(blockPos);
        for (Direction direction : Direction.values()) {
            var targetPos = blockPos.relative(direction);
            BlockState neighborState = level.getBlockState(targetPos);
            if (neighborState.is(BlockTags.BASE_STONE_OVERWORLD) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateMagma) {
                if (level.random.nextFloat() < 0.5f) {
                    level.setBlockAndUpdate(targetPos, Blocks.MAGMA_BLOCK.defaultBlockState());
                }
            }
            else if (neighborState.is(Blocks.MAGMA_BLOCK) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateLava) {
                if (level.random.nextFloat() < 0.5f) {
                    level.setBlockAndUpdate(targetPos, Blocks.LAVA.defaultBlockState());
                }
            }
            else if (neighborState.is(BlockTags.SAND) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateVitrifiedSand) {
                if (level.random.nextFloat() < 0.5f) {
                    level.setBlockAndUpdate(targetPos, ModBlocks.VITRIFIED_SAND.defaultBlockState());
                }
            }
        }
        if (blockState.is(BlockTags.SAND) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateVitrifiedSand) {
            WeatheringHelper.onLightningHit(blockPos, level, 0);
        }
        else if (blockState.is(BlockTags.BASE_STONE_OVERWORLD) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateMagma) {
            level.setBlockAndUpdate(blockPos, Blocks.MAGMA_BLOCK.defaultBlockState());
        }
        else if (blockState.is(Blocks.MAGMA_BLOCK) && ImmersiveWeathering1.getConfig().fireAndIceConfig.lightningCreateLava) {
            level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
        }
        else if (blockState.is(ModBlocks.FULGURITE)) {
            ((FulguriteBlock)blockState.getBlock()).setPowered(blockState, this.level, blockPos);
        }
    }
}
