package com.ordana.immersive_weathering.platform;

import com.google.common.collect.ImmutableBiMap;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class CommonPlatform {

    @Nullable
    @ExpectPlatform
    public static <T> Field findField(Class<? super T> clazz, String fieldName){
        throw new AssertionError();
    }

    @Nullable
    @ExpectPlatform
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes){
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
}
