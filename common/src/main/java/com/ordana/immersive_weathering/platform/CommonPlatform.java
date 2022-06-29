package com.ordana.immersive_weathering.platform;

import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class CommonPlatform {

    @Nullable
    @ExpectPlatform
    public static <T> Field findField(Class<? super T> clazz, String fieldName) {
        throw new AssertionError();
    }

    @Nullable
    @ExpectPlatform
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        throw new AssertionError();
    }


    @ExpectPlatform
    public static boolean isModLoaded(String name) {
        throw new AssertionError();
    }


    @ExpectPlatform
    public static void addExtraFloweryBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addExtraMossyBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addExtraCrackedBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isMobGriefingOn(Level level, Entity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isAreaLoaded(Level level, BlockPos pos, int maxRange) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static FlowingFluid getFlowingFluid(LiquidBlock block){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<LeafPileBlock> getLeafPiles(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ConfigBuilderWrapper getConfigBuilder(String name) {
        throw new AssertionError();
    }
}
