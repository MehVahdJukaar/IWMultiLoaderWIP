package com.ordana.immersive_weathering.platform.fabric;

import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.platform.CommonPlatform;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class CommonPlatformImpl {

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


}
