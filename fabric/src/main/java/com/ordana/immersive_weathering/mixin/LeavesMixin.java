package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.registry.blocks.WeatheringHelper;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LeavesBlock.class)
public abstract class LeavesMixin extends Block implements BonemealableBlock {

    public LeavesMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random, CallbackInfo ci) {
        if(ImmersiveWeathering1.getConfig().leavesConfig.leafDecayPiles) {
            if (state.hasProperty(LeavesBlock.PERSISTENT) && !state.getValue(LeavesBlock.PERSISTENT) && state.hasProperty(LeavesBlock.DISTANCE) && state.getValue(LeavesBlock.DISTANCE) == 7 && state.is(ModTags.VANILLA_LEAVES)) {
                var leafPile = WeatheringHelper.getFallenLeafPile(state).orElse(null);
                if (leafPile == null) return;
                BlockState baseLeaf = leafPile.defaultBlockState().setValue(LeafPileBlock.LAYERS, 0);
                var leafParticle = WeatheringHelper.getFallenLeafParticle(state).orElse(null);
                if(ImmersiveWeathering1.getConfig().leavesConfig.leafDecayParticles) {
                    world.sendParticles(leafParticle, (double) pos.getX() + 0.5D,
                            (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 10,
                            0.5D, 0.5D, 0.5D, 0.0D);
                }
                if(ImmersiveWeathering1.getConfig().leavesConfig.leafDecaySound) {
                    world.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
                if (world.random.nextFloat() < 0.3f) {
                    world.setBlock(pos, baseLeaf.setValue(LeafPileBlock.LAYERS, Mth.randomBetweenInclusive(random, 1, 6)), 2);
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "randomDisplayTick", at = @At("HEAD"))
    public void randomDisplayTick(BlockState state, Level world, BlockPos pos, Random random, CallbackInfo ci) {
        var leafParticle = WeatheringHelper.getFallenLeafParticle(state).orElse(null);
        if (leafParticle == null) return;
        if(ImmersiveWeathering1.getConfig().leavesConfig.fallingLeafParticles && state.is(ModTags.VANILLA_LEAVES)) {
            if (random.nextInt(32) == 0 && !world.getBlockState(pos.below()).isRedstoneConductor(world, pos)) {
                if (!(world.getBlockState(pos.below()).getBlock() instanceof LeavesBlock) && state.is(ModTags.VANILLA_LEAVES)) {
                    ParticleUtils.spawnParticlesOnBlockFaces(world, pos, leafParticle, UniformInt.of(0, 1));
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return state.is(Blocks.FLOWERING_AZALEA_LEAVES);
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
        return state.is(Blocks.FLOWERING_AZALEA_LEAVES);
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        for (var direction : Direction.values()) {
            if (random.nextFloat() > 0.5f) {
                var targetPos = pos.relative(direction);
                BlockState targetBlock = world.getBlockState(targetPos);
                WeatheringHelper.getAzaleaGrowth(targetBlock).ifPresent(s ->
                        world.setBlockAndUpdate(targetPos, s)
                );
            }
        }
    }
}

