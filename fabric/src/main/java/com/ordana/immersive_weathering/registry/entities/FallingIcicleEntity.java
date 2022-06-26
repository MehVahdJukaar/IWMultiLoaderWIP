package com.ordana.immersive_weathering.registry.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FallingIcicleEntity extends FallingBlockEntity {

    public FallingIcicleEntity(EntityType<? extends FallingBlockEntity> type, Level world) {
        super(type, world);
    }

    public FallingIcicleEntity(Level world, double x, double y, double z, BlockState state) {
        this(ModEntities.FALLING_ICICLE, world);
        this.setBlockState(state);
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }

    //workaround
    public void setBlockState(BlockState state) {
        if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
            state = state.setValue(BlockStateProperties.WATERLOGGED, false);
        }
        CompoundTag tag = new CompoundTag();
        tag.put("BlockState", NbtUtils.writeBlockState(state));
        tag.putInt("Time", this.time);
        this.readAdditionalSaveData(tag);
    }

    public static FallingBlockEntity fall (Level world, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingblockentity = new FallingIcicleEntity(world,
                pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, false) : state);
        world.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        world.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }

    //so much for this... ty mojang
    @Nullable
    @Override
    public ItemEntity spawnAtLocation(ItemLike item) {
        return null;
    }
}