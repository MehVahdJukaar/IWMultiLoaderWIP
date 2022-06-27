package com.ordana.immersive_weathering.registry.blocks.test;

import com.ordana.immersive_weathering.block_growth.IConditionalGrowingBlock;
import com.ordana.immersive_weathering.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class CrackedMudBlock extends Block implements IConditionalGrowingBlock {

    public CrackedMudBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(SOAKED, false).setValue(FERTILE, true));
    }

    public static final BooleanProperty SOAKED = BooleanProperty.create("soaked");
    public static final BooleanProperty FERTILE = BooleanProperty.create("fertile");

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (state.getValue(SOAKED)) {
            if (random.nextInt(25) == 1) {
                BlockPos blockpos = pos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, Direction.UP)) {
                    double d0 = (double) pos.getX() + random.nextDouble();
                    double d1 = (double) pos.getY() - 0.05D;
                    double d2 = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        int temperature = 0;
        boolean isTouchingWater = false;
        for (Direction direction : Direction.values()) {
            var targetPos = pos.relative(direction);
            var biome = world.getBiome(pos);
            BlockState neighborState = world.getBlockState(targetPos);

            if (neighborState.getFluidState().is(FluidTags.WATER)) {
                isTouchingWater = true;
                break;
            }

            if (world.isRainingAt(pos.relative(direction))) {
                temperature--;
            } else if (neighborState.is(ModTags.MAGMA_SOURCE) || world.dimension() == Level.NETHER) {
                temperature++;
            } else if (biome.is(ModTags.WET)) {
                temperature--;
            } else if (biome.is(ModTags.HOT)) {
                temperature++;
            }
        }
        boolean newState = temperature < 0 || isTouchingWater;
        if (state.getValue(SOAKED) != newState) {
            world.setBlockAndUpdate(pos, state.setValue(SOAKED, newState));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(SOAKED);
        stateManager.add(FERTILE);
    }

    @Override
    public boolean canGrow(BlockState state) {
        return state.getValue(FERTILE) && state.getValue(SOAKED);
    }
}