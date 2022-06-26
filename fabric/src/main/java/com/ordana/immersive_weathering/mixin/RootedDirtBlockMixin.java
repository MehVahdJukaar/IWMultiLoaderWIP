package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.registry.blocks.ModHangingRootsBlock;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(RootedDirtBlock.class)
public abstract class RootedDirtBlockMixin extends Block implements BonemealableBlock {

    public RootedDirtBlockMixin(Properties settings) {
        super(settings);
    }

    @Shadow
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Shadow
    public abstract boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state);

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        Direction rootDir = Direction.values()[1 + random.nextInt(5)].getOpposite();
        BlockPos rootPos = pos.relative(rootDir);
        BlockState targetState = world.getBlockState(rootPos);
        BlockState toPlace = Blocks.HANGING_ROOTS.defaultBlockState();
        if(targetState.is(Blocks.WATER)) {
            toPlace = toPlace.setValue(ModHangingRootsBlock.WATERLOGGED, true);
        }
        else if(!targetState.isAir())return;
        if (rootDir == Direction.DOWN) {
            world.setBlock(rootPos, toPlace.setValue(ModHangingRootsBlock.HANGING, true), 3);
        }
        else {
            world.setBlock(rootPos, toPlace.setValue(ModHangingRootsBlock.FACING, (rootDir)).setValue(ModHangingRootsBlock.HANGING, Boolean.FALSE), 3);
        }
    }
}
