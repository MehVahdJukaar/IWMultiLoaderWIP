package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.registry.blocks.*;
import com.ordana.immersive_weathering.registry.blocks.crackable.*;
import com.ordana.immersive_weathering.registry.blocks.mossable.*;
import com.ordana.immersive_weathering.registry.blocks.rustable.Rustable;
import com.ordana.immersive_weathering.registry.blocks.rustable.RustableBarsBlock;
import com.ordana.immersive_weathering.registry.blocks.rustable.RustableDoorBlock;
import com.ordana.immersive_weathering.registry.blocks.rustable.RustableTrapdoorBlock;
import com.ordana.immersive_weathering.registry.blocks.test.SoilBlock;
import net.minecraft.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.HangingRootsBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    /*
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SnowBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=snow"
                                            )
                            )
            )
    private static SnowBlock snow(AbstractBlock.Settings settings)
    {
        return new ModSnowLayerBlock(settings);
    }*/

    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/HangingRootsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=hanging_roots"
                                            )
                            )
            )
    private static HangingRootsBlock hangingRoots(BlockBehaviour.Properties settings)
    {
        return new ModHangingRootsBlock(settings);
    }

    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/GrassBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=grass_block"
                                            )
                            )
            )
    private static GrassBlock grassBlock(BlockBehaviour.Properties settings)
    {
        return new ModGrassBlock(settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SnowyBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=podzol"
                                            )
                            )
            )
    private static SnowyDirtBlock podzol(BlockBehaviour.Properties settings)
    {
        return new SoilBlock(settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/MyceliumBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mycelium"
                                            )
                            )
            )
    private static MyceliumBlock mycelium(BlockBehaviour.Properties settings)
    {
        return new ModMyceliumBlock (settings);
    }

    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=polished_blackstone_bricks"
                                            )
                            )
            )
    private static Block polishedBlackstoneBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=polished_blackstone_brick_slab"
                                            )
                            )
            )
    private static SlabBlock polishedBlackstoneBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Shadow @Final public static Block POLISHED_BLACKSTONE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=polished_blackstone_brick_stairs"
                                            )
                            )
            )
    private static StairBlock polishedBlackstoneBricksStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, POLISHED_BLACKSTONE_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=polished_blackstone_brick_wall"
                                            )
                            )
            )
    private static WallBlock polishedBlackstoneBrickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableWallBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=prismarine_bricks"
                                            )
                            )
            )
    private static Block prismarineBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=prismarine_brick_slab"
                                            )
                            )
            )
    private static SlabBlock prismarineBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Shadow @Final public static Block PRISMARINE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=prismarine_brick_stairs"
                                            )
                            )
            )
    private static StairBlock prismarineBricksStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, PRISMARINE_BRICKS.defaultBlockState(), settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone"
                                            )
                            )
            )
    private static Block stone(BlockBehaviour.Properties settings)
    {
        return new MossableBlock(Mossable.MossLevel.MOSSABLE, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_slab"
                                            )
                            )
            )
    private static SlabBlock stoneSlab(BlockBehaviour.Properties settings)
    {
        return new MossableSlabBlock(Mossable.MossLevel.MOSSABLE, settings);
    }
    @Shadow @Final public static Block STONE;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_stairs"
                                            )
                            )
            )
    private static StairBlock stoneStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new MossableStairsBlock(Mossable.MossLevel.MOSSABLE, STONE.defaultBlockState(), settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=end_stone_bricks"
                                            )
                            )
            )
    private static Block endStoneBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=end_stone_brick_slab"
                                            )
                            )
            )
    private static SlabBlock endStoneBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Shadow @Final public static Block END_STONE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=end_stone_brick_stairs"
                                            )
                            )
            )
    private static StairBlock endStoneBricksStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, END_STONE_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=end_stone_brick_wall"
                                            )
                            )
            )
    private static WallBlock endStoneBrickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableWallBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_bricks"
                                            )
                            )
            )
    private static Block deepslateBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_brick_slab"
                                            )
                            )
            )
    private static SlabBlock deepslateBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Shadow @Final public static Block DEEPSLATE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_brick_stairs"
                                            )
                            )
            )
    private static StairBlock deepslateBrickStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, DEEPSLATE_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_brick_wall"
                                            )
                            )
            )
    private static WallBlock deepslateBrickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableWallBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }



    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_tiles"
                                            )
                            )
            )
    private static Block deepslateTiles(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_tile_slab"
                                            )
                            )
            )
    private static SlabBlock deepslateTileSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Shadow @Final public static Block DEEPSLATE_TILES;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_tile_stairs"
                                            )
                            )
            )
    private static StairBlock deepslateTileStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, DEEPSLATE_TILES.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=deepslate_tile_wall"
                                            )
                            )
            )
    private static WallBlock deepslateTileWall(BlockBehaviour.Properties settings)
    {
        return new CrackableWallBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }



    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=nether_bricks"
                                            )
                            )
            )
    private static Block netherBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=nether_brick_slab"
                                            )
                            )
            )
    private static SlabBlock netherBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableSlabBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }
    @Shadow @Final public static Block NETHER_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=nether_brick_stairs"
                                            )
                            )
            )
    private static StairBlock netherBrickStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableStairsBlock(Crackable.CrackLevel.UNCRACKED, NETHER_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=nether_brick_wall"
                                            )
                            )
            )
    private static WallBlock netherBrickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableWallBlock(Crackable.CrackLevel.UNCRACKED,settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=bricks"
                                            )
                            )
            )
    private static Block bricks(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=brick_slab"
                                            )
                            )
            )
    private static SlabBlock brickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableSlabBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Shadow
    @Final
    public static Block BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=brick_stairs"
                                            )
                            )
            )
    private static StairBlock brickStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableMossableStairsBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=brick_wall"
                                            )
                            )
            )
    private static WallBlock brickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableWallBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED,settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_bricks"
                                            )
                            )
            )
    private static Block stoneBricks(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_brick_slab"
                                            )
                            )
            )
    private static SlabBlock stoneBrickSlab(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableSlabBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, settings);
    }
    @Shadow @Final public static Block STONE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_brick_stairs"
                                            )
                            )
            )
    private static StairBlock stoneBricksStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new CrackableMossableStairsBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED, STONE_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=stone_brick_wall"
                                            )
                            )
            )
    private static WallBlock stoneBrickWall(BlockBehaviour.Properties settings)
    {
        return new CrackableMossableWallBlock(Mossable.MossLevel.MOSSABLE, Crackable.CrackLevel.UNCRACKED,settings);
    }



    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_stone_bricks"
                                            )
                            )
            )
    private static Block mossyStoneBricks(BlockBehaviour.Properties settings)
    {
        return new MossyBlock(Mossable.MossLevel.MOSSY, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_stone_brick_slab"
                                            )
                            )
            )
    private static SlabBlock mossyStoneBrickSlab(BlockBehaviour.Properties settings)
    {
        return new MossySlabBlock(Mossable.MossLevel.MOSSY, settings);
    }
    @Shadow @Final public static Block MOSSY_STONE_BRICKS;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_stone_brick_stairs"
                                            )
                            )
            )
    private static StairBlock mossyStoneBrickStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new MossyStairsBlock(Mossable.MossLevel.MOSSY, MOSSY_STONE_BRICKS.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_stone_brick_wall"
                                            )
                            )
            )
    private static WallBlock mossyStoneBrickWall(BlockBehaviour.Properties settings)
    {
        return new MossyWallBlock(Mossable.MossLevel.MOSSY, settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=cobblestone"
                                            )
                            )
            )
    private static Block cobblestone(BlockBehaviour.Properties settings)
    {
        return new MossableBlock(Mossable.MossLevel.MOSSABLE, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=cobblestone_slab"
                                            )
                            )
            )
    private static SlabBlock cobblestoneSlab(BlockBehaviour.Properties settings)
    {
        return new MossableSlabBlock(Mossable.MossLevel.MOSSABLE, settings);
    }
    @Shadow @Final public static Block COBBLESTONE;
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=cobblestone_stairs"
                                            )
                            )
            )
    private static StairBlock cobblestoneStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new MossableStairsBlock(Mossable.MossLevel.MOSSABLE, COBBLESTONE.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=cobblestone_wall"
                                            )
                            )
            )
    private static WallBlock cobblestoneWall(BlockBehaviour.Properties settings)
    {
        return new MossableWallBlock(Mossable.MossLevel.MOSSABLE, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/Block",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_cobblestone"
                                            )
                            )
            )
    private static Block mossyCobblestone(BlockBehaviour.Properties settings)
    {
        return new MossyBlock(Mossable.MossLevel.MOSSY, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/SlabBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_cobblestone_slab"
                                            )
                            )
            )
    private static SlabBlock mossyCobblestoneSlab(BlockBehaviour.Properties settings)
    {
        return new MossySlabBlock(Mossable.MossLevel.MOSSY, settings);
    }
    @Shadow @Final public static Block MOSSY_COBBLESTONE;

    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/StairsBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_cobblestone_stairs"
                                            )
                            )
            )
    private static StairBlock mossyCobblestoneStairs(BlockState baseBlockState, BlockBehaviour.Properties settings)
    {
        return new MossyStairsBlock(Mossable.MossLevel.MOSSY, MOSSY_COBBLESTONE.defaultBlockState(), settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/WallBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=mossy_cobblestone_wall"
                                            )
                            )
            )
    private static WallBlock mossyCobblestoneWall(BlockBehaviour.Properties settings)
    {
        return new MossyWallBlock(Mossable.MossLevel.MOSSY, settings);
    }


    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/PaneBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=iron_bars"
                                            )
                            )
            )
    private static IronBarsBlock ironBars(BlockBehaviour.Properties settings)
    {
        return new RustableBarsBlock(Rustable.RustLevel.UNAFFECTED, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/DoorBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=iron_door"
                                            )
                            )
            )
    private static DoorBlock ironDoor(BlockBehaviour.Properties settings)
    {
        return new RustableDoorBlock(Rustable.RustLevel.UNAFFECTED, settings);
    }
    @Redirect
            (
                    method = "<clinit>",
                    at = @At
                            (
                                    value = "NEW",
                                    target = "net/minecraft/block/TrapdoorBlock",
                                    ordinal = 0
                            ),
                    slice = @Slice
                            (
                                    from = @At
                                            (
                                                    value = "CONSTANT",
                                                    args="stringValue=iron_trapdoor"
                                            )
                            )
            )
    private static TrapDoorBlock ironTrapdoor(BlockBehaviour.Properties settings)
    {
        return new RustableTrapdoorBlock(Rustable.RustLevel.UNAFFECTED, settings);
    }
}