package com.ordana.immersive_weathering.platform.forge;

import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.integration.IntegrationHandler;
import com.ordana.immersive_weathering.integration.QuarkPlugin;
import com.ordana.immersive_weathering.platform.CommonPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class CommonPlatformImpl {

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
        if (IntegrationHandler.quark) {
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

    }

    public static void addExtraMossyBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        if (IntegrationHandler.quark) {
            QuarkPlugin.addAllVerticalSlabs(builder);
        }
    }

    public static void addExtraCrackedBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        if (IntegrationHandler.quark) {
            QuarkPlugin.addAllVerticalSlabs(builder);
        }
    }

    public static boolean isMobGriefingOn(Level level, Entity entity) {
        return ForgeEventFactory.getMobGriefingEvent(level, entity);
    }


}
