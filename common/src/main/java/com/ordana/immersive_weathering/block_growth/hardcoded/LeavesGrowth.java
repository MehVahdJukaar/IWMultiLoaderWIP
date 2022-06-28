package com.ordana.immersive_weathering.block_growth.hardcoded;

import com.ordana.immersive_weathering.block_growth.IBlockGrowth;
import com.ordana.immersive_weathering.block_growth.TickSource;
import com.ordana.immersive_weathering.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.common.ModBlocks;
import com.ordana.immersive_weathering.common.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.common.blocks.LeafPilesRegistry;
import com.ordana.immersive_weathering.common.WeatheringHelper;
import com.ordana.immersive_weathering.configs.ServerConfigs;
import com.ordana.immersive_weathering.platform.CommonPlatform;
import com.ordana.immersive_weathering.reg.LeafPilesRegistry;
import com.ordana.immersive_weathering.utils.WeatheringHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class LeavesGrowth implements IBlockGrowth {

    @Override
    public Iterable<LeafPileBlock> getOwners() {
        return CommonPlatform.getLeafPiles();
    }

    @Override
    public void tryGrowing(BlockPos pos, BlockState state, ServerLevel level, Holder<Biome> biome) {

        Random random = level.random;

        //Drastically reduced this chance to help lag
        //checking if it has this properties because mcreator mods...
        if ((!state.hasProperty(LeavesBlock.PERSISTENT) || !state.getValue(LeavesBlock.PERSISTENT))
                && random.nextFloat() < ServerConfigs.FALLING_LEAVES.get()) {

            var leafPile = LeafPilesRegistry.getFallenLeafPile(state).orElse(null);
            if (leafPile != null && level.getBlockState(pos.below()).isAir()) {


                if (random.nextBoolean() && WeatheringHelper.isIciclePos(pos) && level.getBiome(pos).value().coldEnoughToSnow(pos)) {
                    level.setBlock(pos.below(), ModBlocks.ICICLE.get().defaultBlockState()
                            .setValue(PointedDripstoneBlock.TIP_DIRECTION, Direction.DOWN), 2);
                }

                if (!level.isAreaLoaded(pos, 2)) return;
                BlockPos targetPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
                int maxFallenLeavesReach = ServerConfigs.LEAF_PILE_REACH.get();
                int maxPileHeight = ServerConfigs.MAX_LEAF_PILE_HEIGHT.get();
                int dist = pos.getY() - targetPos.getY();
                //calculating normally if heightmap fails
                if (dist < 0) {
                    targetPos = pos;
                    do {
                        targetPos = targetPos.below();
                        dist = pos.getY() - targetPos.getY();
                    } while (level.getBlockState(targetPos).getMaterial().isReplaceable() &&
                            dist < maxFallenLeavesReach);
                    targetPos = targetPos.above();

                }
                if (dist < maxFallenLeavesReach) {

                    BlockState replaceState = level.getBlockState(targetPos);

                    boolean isOnLeaf = replaceState.getBlock() instanceof LeafPileBlock;
                    int pileHeight = 1;
                    if (isOnLeaf) {
                        pileHeight = replaceState.getValue(LeafPileBlock.LAYERS);
                        if (pileHeight == 0 || pileHeight >= maxPileHeight) return;
                    }

                    BlockState baseLeaf = leafPile.defaultBlockState().setValue(LeafPileBlock.LAYERS, 0);
                    //if we find a non-air block we check if its upper face is sturdy. Given previous iteration if we are not on the first cycle blocks above must be air
                    if (isOnLeaf ||
                            (replaceState.getMaterial().isReplaceable() && baseLeaf.canSurvive(level, targetPos)
                                    && !WeatheringHelper.hasEnoughBlocksAround(targetPos, 2, 2, 2,
                                    level, b -> b.getBlock() instanceof LeafPileBlock, 6))) {


                        if (level.getBlockState(targetPos.below()).is(Blocks.WATER)) {
                            level.setBlock(targetPos, baseLeaf.setValue(LeafPileBlock.LAYERS, 0), 2);
                        } else {
                            if (isOnLeaf) {
                                int original = pileHeight;
                                boolean hasLog = false;
                                BlockState[] neighbors = new BlockState[4];
                                for (Direction direction : Direction.Plane.HORIZONTAL) {
                                    neighbors[direction.get2DDataValue()] = level.getBlockState(targetPos.relative(direction));
                                }
                                for (var neighbor : neighbors) {
                                    if (WeatheringHelper.isLog(neighbor)) {
                                        hasLog = true;
                                        break;
                                    }
                                }
                                for (var neighbor : neighbors) {
                                    if (neighbor.getBlock() instanceof LeafPileBlock) {
                                        int i = hasLog ? maxPileHeight :
                                                Math.min(neighbor.getValue(LeafPileBlock.LAYERS) - 1, maxPileHeight);
                                        if (i > pileHeight) {
                                            pileHeight = Math.min(pileHeight + 1, i);
                                            break;
                                        }
                                    }
                                }
                                if (pileHeight == original) return;
                            }
                            level.setBlock(targetPos, baseLeaf.setValue(LeafPileBlock.LAYERS, pileHeight), 2);
                        }
                    }

                }
            }

        }

    }


    //TODO: new, CHECK. ALso merge the others

    @Override
    public void spawnLeafPile(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (!state.getValue(LeavesBlock.PERSISTENT) && state.getValue(LeavesBlock.DISTANCE) == 7) {
            var leafPile = WeatheringHelper.getFallenLeafPile(state).orElse(null);
            BlockState baseLeaf = leafPile.defaultBlockState().with(LeafPileBlock.LAYERS, 0);
            if (world.random.nextFloat() < 0.3f) {
                world.setBlockAndUpdate(pos, baseLeaf.setValue(LeafPileBlock.LAYERS, Mth.nextBetween(random, 1, 6)), 2);
            }
            else {
                LeavesBlock.dropStacks(state, world, pos);
                world.removeBlock(pos, false);
            }
        }
    }

    @Override
    public Collection<TickSource> getTickSources() {
        return List.of(TickSource.BLOCK_TICK);
    }




    //FABRIC STUFF: TODO: Merge
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
