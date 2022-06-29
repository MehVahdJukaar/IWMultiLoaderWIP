package com.ordana.immersive_weathering.platform.fabric;

import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.fabric.FabricConfigBuilder;
import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import com.ordana.immersive_weathering.platform.CommonPlatform;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommonPlatformImpl {

    public CommonPlatform.Platform getPlatform(){
        return CommonPlatform.Platform.FABRIC;
    }

    public static boolean isModLoaded(String name) {

    }

    public static void addExtraFloweryBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
    }

    @Nullable
    public static <T> Field findField(Class<? super T> clazz, String fieldName) {
        return null;
    }

    @Nullable
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        return null;
    }

    public static void addExtraMossyBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
    }

    public static void addExtraCrackedBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
    }

    public static boolean isMobGriefingOn(Level level, Entity entity) {
        return level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
    }

    public static boolean isAreaLoaded(Level level, BlockPos pos, int maxRange) {
        //crappy version for fabric :(
        return level.isLoaded(pos);
    }

    public static int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return FlammableBlockRegistry.getDefaultInstance().get(state.getBlock()).getBurnChance();
    }

    public static FlowingFluid getFlowingFluid(LiquidBlock block) {
        return null;
    }

    public static ConfigBuilderWrapper getConfigBuilder(String name, ConfigBuilderWrapper.ConfigType type) {
        return new FabricConfigBuilder(name,type);
    }


}
