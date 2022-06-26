package com.ordana.immersive_weathering.registry.entities;

import com.ordana.immersive_weathering.registry.blocks.WeedsBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WeedEatGoal extends MoveToBlockGoal {
    private final Rabbit rabbit;
    private boolean wantsWeeds;
    private boolean hasTarget;
    int moreWeedsTicks;

    public WeedEatGoal(Animal rabbit) {
        super(rabbit, 0.699999988079071D, 16);
        this.rabbit = (Rabbit) rabbit;
    }

    public boolean canUse() {
        if (this.nextStartTick <= 0) {
            if (!this.rabbit.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                return false;
            }

            this.hasTarget = false;
            this.wantsWeeds = true;
        }

        return super.canUse();
    }

    public boolean canContinueToUse() {
        return this.hasTarget && super.canContinueToUse();
    }

    public void tick() {
        super.tick();
        this.rabbit.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.rabbit.getMaxHeadXRot());
        if (this.isReachedTarget()) {
            Level world = this.rabbit.level;
            BlockPos blockPos = this.blockPos.above();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();
            if (this.hasTarget && block instanceof WeedsBlock) {
                int i = blockState.getValue(WeedsBlock.AGE);
                if (i == 0) {
                    world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2);
                    world.destroyBlock(blockPos, true, this.rabbit);
                } else {
                    world.destroyBlock(blockPos, true, this.rabbit);
                    world.levelEvent(2001, blockPos, Block.getId(blockState));
                }

                this.moreWeedsTicks = 40;
            }

            this.hasTarget = false;
            this.nextStartTick = 10;
        }

    }

    protected boolean isValidTarget(LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.is(Blocks.FARMLAND) && this.wantsWeeds && !this.hasTarget) {
            blockState = world.getBlockState(pos.above());
            if (blockState.getBlock() instanceof WeedsBlock && ((WeedsBlock)blockState.getBlock()).isMaxAge(blockState)) {
                this.hasTarget = true;
                return true;
            }
        }

        return false;
    }
}