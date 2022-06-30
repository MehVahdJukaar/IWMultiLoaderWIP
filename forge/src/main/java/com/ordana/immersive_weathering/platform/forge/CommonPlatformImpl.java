package com.ordana.immersive_weathering.platform.forge;

import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import com.ordana.immersive_weathering.forge.ForgeConfigBuilder;

import com.ordana.immersive_weathering.platform.CommonPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class CommonPlatformImpl {

    public static CommonPlatform.Platform getPlatform() {
        return CommonPlatform.Platform.FORGE;
    }

    public static boolean isModLoaded(String name) {
        return ModList.get().isLoaded(name);
    }

    @Nullable
    public static <T> Field findField(Class<? super T> clazz, String fieldName) {
        try {
            return ObfuscationReflectionHelper.findField(clazz, fieldName);
        }catch (Exception e){
            return null;
        }
    }

    @Nullable
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes){
        try{
            return ObfuscationReflectionHelper.findMethod(clazz,methodName, parameterTypes);
        }catch (Exception e){
            return null;
        }
    }

    public static void addExtraFloweryBlocks(ImmutableBiMap.Builder<Block, Block> builder) {

        Block a = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:flowering_azalea_hedge"));
        Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:azalea_hedge"));
        if (a != null && b != null) {
            builder.put(a, b);
        }
        Block c = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:flowering_azalea_leaf_carpet"));
        Block d = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:azalea_leaf_carpet"));
        if (c != null && d != null) {
            builder.put(c, d);
        }


    }

    public static void addExtraMossyBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        //if (IntegrationHandler.quark) {
        //    QuarkPlugin.addAllVerticalSlabs(builder);
       // }
    }

    public static void addExtraCrackedBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
       // if (IntegrationHandler.quark) {
        //    QuarkPlugin.addAllVerticalSlabs(builder);
       // }
    }

    public static boolean isMobGriefingOn(Level level, Entity entity) {
        return ForgeEventFactory.getMobGriefingEvent(level, entity);
    }

    public static boolean isAreaLoaded(Level level, BlockPos pos, int maxRange) {
        return level.isAreaLoaded(pos, maxRange);
    }

    public static int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return state.getFlammability(level, pos, face);
    }

    public static FlowingFluid getFlowingFluid(LiquidBlock block) {
        return block.getFluid();
    }

    public static ConfigBuilderWrapper getConfigBuilder(String name, ConfigBuilderWrapper.ConfigType type) {
        return new ForgeConfigBuilder(name,type);
    }




}
