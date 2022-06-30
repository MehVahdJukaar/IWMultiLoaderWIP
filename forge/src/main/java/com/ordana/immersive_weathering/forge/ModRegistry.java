package com.ordana.immersive_weathering.forge;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.reg.ModBlocks;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ImmersiveWeathering.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ImmersiveWeathering.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ImmersiveWeathering.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ImmersiveWeathering.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ImmersiveWeathering.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ImmersiveWeathering.MOD_ID);


    //override
    public static final Supplier<Item> HANGING_ROOTS_ITEM = ITEMS.register("minecraft:hanging_roots", () ->
            new CeilingAndWallBlockItem(Blocks.HANGING_ROOTS, ModBlocks.HANGING_ROOTS_WALL.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

}
