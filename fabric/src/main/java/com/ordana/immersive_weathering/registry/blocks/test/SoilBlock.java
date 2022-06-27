package com.ordana.immersive_weathering.registry.blocks.test;

import com.ordana.immersive_weathering.block_growth.IConditionalGrowingBlock;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import java.util.Random;

public class SoilBlock extends SnowyDirtBlock implements BonemealableBlock, IConditionalGrowingBlock {
    public static final BooleanProperty FERTILE = BooleanProperty.create("fertile");


    public SoilBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FERTILE, true).setValue(SNOWY, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FERTILE);
        builder.add(SNOWY);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.hasProperty(FERTILE) && state.getValue(FERTILE);
    }

    @Override
    public boolean canGrow(BlockState state) {
        return state.getValue(FERTILE);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        world.setBlockAndUpdate(pos, state.getBlock().defaultBlockState().setValue(FERTILE, true));
    }
}
