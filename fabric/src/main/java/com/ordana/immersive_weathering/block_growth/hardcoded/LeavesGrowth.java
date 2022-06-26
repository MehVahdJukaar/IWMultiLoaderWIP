package com.ordana.immersive_weathering.block_growth.hardcoded;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.block_growth.IBlockGrowth;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.blocks.WeatheringHelper;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import java.util.Random;
import java.util.stream.Collectors;

public class LeavesGrowth implements IBlockGrowth {

    @Override
    public Iterable<Block> getOwners() {
        return Registry.BLOCK.getTag(BlockTags.LEAVES).get().stream().map(Holder::value).collect(Collectors.toList());
    }

    @Override
    public void tryGrowing(BlockPos pos, BlockState state, ServerLevel world, Holder<Biome> biome) {
        if(ImmersiveWeathering.getConfig().leavesConfig.leafPilesForm) {
            Random random = world.random;
            //Drastically reduced this chance to help lag
            if (state.hasProperty(LeavesBlock.PERSISTENT) && !state.getValue(LeavesBlock.PERSISTENT) && random.nextFloat() < 0.03f) {

                var leafPile = WeatheringHelper.getFallenLeafPile(state).orElse(null);
                if (leafPile == null) return;
                if (world.getBlockState(pos.below()).is(ModTags.LEAF_PILE_REPLACEABLE)) {


                    if (random.nextBoolean() && WeatheringHelper.isIciclePos(pos) && world.getBiome(pos).value().coldEnoughToSnow(pos)) {
                        world.setBlock(pos.below(), ModBlocks.ICICLE.defaultBlockState()
                                .setValue(PointedDripstoneBlock.TIP_DIRECTION, Direction.DOWN), 2);
                    }

                    if (!world.hasChunkAt(pos)) return;
                    BlockPos targetPos = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
                    int maxFallenLeavesReach = 12;
                    int maxPileHeight = 3;
                    int dist = pos.getY() - targetPos.getY();
                    if (dist < 0) {
                        targetPos = pos;
                        do {
                            targetPos = targetPos.below();
                            dist = pos.getY() - targetPos.getY();
                        } while (world.getBlockState(targetPos).getMaterial().isReplaceable() &&
                                dist < maxFallenLeavesReach);
                        targetPos = targetPos.above();

                    }
                    if (dist < maxFallenLeavesReach) {

                        BlockState replaceState = world.getBlockState(targetPos);

                        boolean isOnLeaf = replaceState.getBlock() instanceof LeafPileBlock;

                        int pileHeight = 1;
                        if (isOnLeaf) {
                            pileHeight = replaceState.getValue(LeafPileBlock.LAYERS);
                            if (pileHeight == 0 || pileHeight >= maxPileHeight) return;
                        }

                        BlockPos below = targetPos.below();
                        BlockState belowState = world.getBlockState(below);
                        BlockState baseLeaf = leafPile.defaultBlockState().setValue(LeafPileBlock.LAYERS, 0);
                        //if we find a non-air block we check if its upper face is sturdy. Given previous iteration if we are not on the first cycle blocks above must be air
                        if (isOnLeaf ||
                                (replaceState.getMaterial().isReplaceable()
                                        && belowState.isFaceSturdy(world, below, Direction.UP)
                                        && baseLeaf.canSurvive(world, targetPos)
                                        && !WeatheringHelper.hasEnoughBlocksAround(targetPos, 2, 1, 2, world, b -> b.getBlock() instanceof LeafPileBlock, 6))) {


                            if (world.getBlockState(targetPos.below()).is(Blocks.WATER)) {
                                world.setBlock(targetPos, baseLeaf.setValue(LeafPileBlock.LAYERS, 0), 2);
                            } else {
                                if (isOnLeaf) {
                                    int original = pileHeight;
                                    boolean hasLog = false;
                                    BlockState[] neighbors = new BlockState[4];
                                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                                        neighbors[direction.get2DDataValue()] = world.getBlockState(targetPos.relative(direction));
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
                                world.setBlock(targetPos, baseLeaf.setValue(LeafPileBlock.LAYERS, pileHeight), 2);
                            }
                        }

                    }
                }
            }
        }
    }
}

