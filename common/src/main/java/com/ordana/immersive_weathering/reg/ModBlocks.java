package com.ordana.immersive_weathering.reg;

import com.ordana.immersive_weathering.blocks.*;
import com.ordana.immersive_weathering.blocks.charred.*;
import com.ordana.immersive_weathering.blocks.crackable.*;
import com.ordana.immersive_weathering.blocks.frostable.FrostBlock;
import com.ordana.immersive_weathering.blocks.frostable.FrostyGlassBlock;
import com.ordana.immersive_weathering.blocks.frostable.FrostyGlassPaneBlock;
import com.ordana.immersive_weathering.blocks.frostable.FrostyGrassBlock;
import com.ordana.immersive_weathering.blocks.mossable.*;
import com.ordana.immersive_weathering.blocks.rustable.Rustable;
import com.ordana.immersive_weathering.blocks.soil.*;
import com.ordana.immersive_weathering.platform.CommonPlatform;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import com.ordana.immersive_weathering.platform.RegistryPlatform.BlockType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public class ModBlocks {

    public static <T extends Block> Supplier<T> regBlock(String name, Supplier<T> block) {
        return RegistryPlatform.registerBlock(name, block);
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> block) {
        return regWithItem(name, block, CreativeModeTab.TAB_BUILDING_BLOCKS);
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> block, CreativeModeTab tab) {
        Supplier<T> toReturn = regBlock(name, block);
        ModItems.regBlockItem(name, block, new Item.Properties().tab(tab));
        return toReturn;
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> block, String requiredMod) {
        CreativeModeTab tab = CommonPlatform.isModLoaded(requiredMod) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null;
        return regWithItem(name, block, tab);
    }

    //predicates

    private static ToIntFunction<BlockState> litLightLevel(int litLevel) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? litLevel : 0;
    }

    private static ToIntFunction<BlockState> moltenLightLevel(int litLevel) {
        return (state) -> state.getValue(ModBlockProperties.MOLTEN) ? litLevel : 0;
    }

    private static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> CAN_SPAWN_ON_LEAVES = (a, b, c, t) ->
            t == EntityType.OCELOT || t == EntityType.PARROT;

    private static final BlockBehaviour.StatePredicate NEVER = (s, w, p) -> false;


    //leaves piles
    public static final Supplier<LeafPileBlock> OAK_LEAF_PILE = regBlock("oak_leaf_pile", () ->
            new LeafPileBlock(Properties.of(Material.REPLACEABLE_PLANT)
                    .randomTicks().instabreak().sound(SoundType.GRASS)
                    .noOcclusion().isValidSpawn(CAN_SPAWN_ON_LEAVES)
                    .isSuffocating(NEVER).isViewBlocking(NEVER),
                    false, false, true, List.of(ModParticles.OAK_LEAF)));

    public static final Supplier<LeafPileBlock> BIRCH_LEAF_PILE = regBlock("birch_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()), false, false, true,
                    List.of(ModParticles.BIRCH_LEAF)));

    public static final Supplier<LeafPileBlock> SPRUCE_LEAF_PILE = regBlock("spruce_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()), false, true, false,
                    List.of(ModParticles.SPRUCE_LEAF)));

    public static final Supplier<LeafPileBlock> JUNGLE_LEAF_PILE = regBlock("jungle_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()), false, false, true,
                    List.of(ModParticles.JUNGLE_LEAF)));

    public static final Supplier<LeafPileBlock> ACACIA_LEAF_PILE = regBlock("acacia_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()), false, false, false,
                    List.of(ModParticles.ACACIA_LEAF)));

    public static final Supplier<LeafPileBlock> DARK_OAK_LEAF_PILE = regBlock("dark_oak_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()), false, false, true,
                    List.of(ModParticles.DARK_OAK_LEAF)));

    public static final Supplier<LeafPileBlock> AZALEA_LEAF_PILE = regBlock("azalea_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(OAK_LEAF_PILE.get()).sound(SoundType.AZALEA_LEAVES), false, false, false,
                    List.of(ModParticles.AZALEA_LEAF)));

    public static final Supplier<LeafPileBlock> FLOWERING_AZALEA_LEAF_PILE = regBlock("flowering_azalea_leaf_pile", () ->
            new LeafPileBlock(Properties.copy(AZALEA_LEAF_PILE.get()), true, false, false,
                    List.of(ModParticles.AZALEA_LEAF, ModParticles.AZALEA_FLOWER)));

    public static final Supplier<LeafPileBlock> AZALEA_FLOWER_PILE = regBlock("azalea_flower_pile", () ->
            new LeafPileBlock(Properties.copy(AZALEA_LEAF_PILE.get()), true, false, false,
                    List.of(ModParticles.AZALEA_FLOWER)));


    //vegetation

    public static final Supplier<Block> WEEDS = regWithItem("weeds", () ->
                    new WeedsBlock(Properties.of(Material.PLANT).noCollission()
                            .instabreak().sound(SoundType.GRASS)),
            CreativeModeTab.TAB_DECORATIONS);

    public static final Supplier<Block> HANGING_ROOTS_WALL = regBlock("hanging_roots_wall", () ->
            new WallRootsBlock(Properties.copy(Blocks.HANGING_ROOTS)));

    public static final Supplier<Block> IVY = regBlock("ivy_block", () ->
            new IvyBlock(Properties.of(Material.PLANT).noCollission().strength(0.2f)
                    .sound(SoundType.AZALEA_LEAVES)));


    //mossy bricks stuff

    public static final Supplier<Block> MOSSY_BRICKS = regWithItem("mossy_bricks", () ->
            new MossyBlock(Mossable.MossLevel.MOSSY, Properties.of(Material.STONE, MaterialColor.COLOR_RED)
                    .requiresCorrectToolForDrops().strength(2f, 6f)));
    public static final Supplier<Block> MOSSY_BRICK_STAIRS = regWithItem("mossy_brick_stairs", () ->
            new MossyStairsBlock(Mossable.MossLevel.MOSSY, MOSSY_BRICKS, Properties.copy(MOSSY_BRICKS.get())));
    public static final Supplier<Block> MOSSY_BRICK_SLAB = regWithItem("mossy_brick_slab", () ->
            new MossySlabBlock(Mossable.MossLevel.MOSSY, Properties.copy(MOSSY_BRICKS.get())));
    public static final Supplier<Block> MOSSY_BRICK_WALL = regWithItem("mossy_brick_wall", () ->
            new MossyWallBlock(Mossable.MossLevel.MOSSY, Properties.copy(MOSSY_BRICKS.get())));
    public static final Supplier<Block> MOSSY_BRICK_VERTICAL_SLAB = regWithItem("mossy_brick_vertical_slab", () ->
            new MossyVerticalSlabBlock(Mossable.MossLevel.MOSSY, Properties.copy(MOSSY_BRICKS.get())), "quark");

    //mossy stone

    public static final Supplier<Block> MOSSY_STONE = regWithItem("mossy_stone", () ->
            new MossyBlock(Mossable.MossLevel.MOSSY, Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5f, 6f)));
    public static final Supplier<Block> MOSSY_STONE_STAIRS = regWithItem("mossy_stone_stairs", () ->
            new MossyStairsBlock(Mossable.MossLevel.MOSSY, MOSSY_STONE, Properties.copy(MOSSY_STONE.get())));
    public static final Supplier<Block> MOSSY_STONE_SLAB = regWithItem("mossy_stone_slab", () ->
            new MossySlabBlock(Mossable.MossLevel.MOSSY, Properties.copy(MOSSY_STONE.get())));
    public static final Supplier<Block> MOSSY_STONE_VERTICAL_SLAB = regWithItem("mossy_stone_vertical_slab", () ->
            new MossyVerticalSlabBlock(Mossable.MossLevel.MOSSY, Properties.copy(MOSSY_STONE_STAIRS.get())), "quark");

    //cracked bricks

    public static final Supplier<Block> CRACKED_BRICKS = regWithItem("cracked_bricks", () ->
            new CrackedBlock(Crackable.CrackLevel.CRACKED, () -> Items.BRICK,
                    Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2f, 6f)));
    public static final Supplier<Block> CRACKED_BRICK_STAIRS = regWithItem("cracked_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, CRACKED_BRICKS, () -> Items.BRICK,
                    Properties.copy(CRACKED_BRICKS.get())));
    public static final Supplier<Block> CRACKED_BRICK_SLAB = regWithItem("cracked_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, () -> Items.BRICK,
                    Properties.copy(CRACKED_BRICKS.get())));
    public static final Supplier<Block> CRACKED_BRICK_WALL = regWithItem("cracked_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, () -> Items.BRICK,
                    Properties.copy(CRACKED_BRICKS.get())));
    public static final Supplier<Block> CRACKED_STONE_VERTICAL_SLAB = regWithItem("cracked_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, () -> Items.BRICK,
                    Properties.copy(CRACKED_BRICK_SLAB.get())), "quark");

    //cracked stone bricks

    public static final Supplier<Block> CRACKED_STONE_BRICK_STAIRS = regWithItem("cracked_stone_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, () -> Blocks.CRACKED_STONE_BRICKS, ModItems.STONE_BRICK,
                    Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final Supplier<Block> CRACKED_STONE_BRICK_SLAB = regWithItem("cracked_stone_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.STONE_BRICK,
                    Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final Supplier<Block> CRACKED_STONE_BRICK_WALL = regWithItem("cracked_stone_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.STONE_BRICK,
                    Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final Supplier<Block> CRACKED_STONE_BRICK_VERTICAL_SLAB = regWithItem("cracked_stone_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.STONE_BRICK,
                    Properties.copy(Blocks.CRACKED_STONE_BRICKS)), "quark");

    //cracked blackstone

    public static final Supplier<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS = regWithItem("cracked_polished_blackstone_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, () -> Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, ModItems.BLACKSTONE_BRICK,
                    Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)));
    public static final Supplier<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB = regWithItem("cracked_polished_blackstone_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.BLACKSTONE_BRICK,
                    Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)));
    public static final Supplier<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_WALL = regWithItem("cracked_polished_blackstone_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.BLACKSTONE_BRICK,
                    Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)));
    public static final Supplier<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = regWithItem("cracked_polished_blackstone_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.BLACKSTONE_BRICK,
                    Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)), "quark");

    //cracked nether bricks

    public static final Supplier<Block> CRACKED_NETHER_BRICK_STAIRS = regWithItem("cracked_nether_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, () -> Blocks.CRACKED_NETHER_BRICKS, () -> Items.NETHER_BRICK,
                    Properties.copy(Blocks.NETHER_BRICKS)));
    public static final Supplier<Block> CRACKED_NETHER_BRICK_SLAB = regWithItem("cracked_nether_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, () -> Items.NETHER_BRICK,
                    Properties.copy(Blocks.NETHER_BRICKS)));
    public static final Supplier<Block> CRACKED_NETHER_BRICK_WALL = regWithItem("cracked_nether_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, () -> Items.NETHER_BRICK,
                    Properties.copy(Blocks.NETHER_BRICKS)));
    public static final Supplier<Block> CRACKED_NETHER_BRICK_VERTICAL_SLAB = regWithItem("cracked_nether_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, () -> Items.NETHER_BRICK,
                    Properties.copy(Blocks.NETHER_BRICKS)), "quark");

    //cracked deepslate

    public static final Supplier<Block> CRACKED_DEEPSLATE_BRICK_STAIRS = regWithItem("cracked_deepslate_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, () -> Blocks.CRACKED_DEEPSLATE_BRICKS, ModItems.DEEPSLATE_BRICK,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_BRICK_SLAB = regWithItem("cracked_deepslate_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_BRICK,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_BRICK_WALL = regWithItem("cracked_deepslate_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_BRICK,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_BRICK_VERTICAL_SLAB = regWithItem("cracked_deepslate_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_BRICK,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS)), "quark");

    //cracked deepslate tile

    public static final Supplier<Block> CRACKED_DEEPSLATE_TILE_STAIRS = regWithItem("cracked_deepslate_tile_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, () -> Blocks.CRACKED_DEEPSLATE_TILES, ModItems.DEEPSLATE_TILE,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_TILE_SLAB = regWithItem("cracked_deepslate_tile_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_TILE,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_TILE_WALL = regWithItem("cracked_deepslate_tile_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_TILE,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES)));
    public static final Supplier<Block> CRACKED_DEEPSLATE_TILE_VERTICAL_SLAB = regWithItem("cracked_deepslate_tile_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.DEEPSLATE_TILE,
                    Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES)), "quark");

    //mulch

    //TODO: remove particles
    public static final Supplier<Block> MULCH_BLOCK = regWithItem("mulch_block", () ->
            RegistryPlatform.createSpecialBlock(BlockType.MULCH,
                    Properties.of(Material.WOOD).strength(1f, 1f)
                            .sound(SoundType.ROOTED_DIRT).randomTicks()));
    public static final Supplier<Block> NULCH_BLOCK = regWithItem("nulch_block", () ->
            new NulchBlock(Properties.of(Material.NETHER_WOOD).strength(1f, 1f)
                    .sound(SoundType.NETHER_WART).lightLevel(moltenLightLevel(10)).randomTicks()));

    //cut iron

    public static final Supplier<Block> CUT_IRON = regWithItem("cut_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(Blocks.IRON_DOOR).sound(SoundType.COPPER),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> EXPOSED_CUT_IRON = regWithItem("exposed_cut_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WEATHERED_CUT_IRON = regWithItem("weathered_cut_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> RUSTED_CUT_IRON = regWithItem("rusted_cut_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED));

    //cut iron stairs

    public static final Supplier<Block> CUT_IRON_STAIRS = regWithItem("cut_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED, CUT_IRON));
    public static final Supplier<Block> EXPOSED_CUT_IRON_STAIRS = regWithItem("exposed_cut_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED, EXPOSED_CUT_IRON));
    public static final Supplier<Block> WEATHERED_CUT_IRON_STAIRS = regWithItem("weathered_cut_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED, WEATHERED_CUT_IRON));
    public static final Supplier<Block> RUSTED_CUT_IRON_STAIRS = regWithItem("rusted_cut_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED, RUSTED_CUT_IRON));

    //cur iron slabs

    public static final Supplier<Block> CUT_IRON_SLAB = regWithItem("cut_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> EXPOSED_CUT_IRON_SLAB = regWithItem("exposed_cut_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WEATHERED_CUT_IRON_SLAB = regWithItem("weathered_cut_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> RUSTED_CUT_IRON_SLAB = regWithItem("rusted_cut_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED));

    //waxed cut iron

    public static final Supplier<Block> WAXED_CUT_IRON = regWithItem("waxed_cut_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_CUT_IRON = regWithItem("waxed_exposed_cut_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON = regWithItem("waxed_weathered_cut_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON = regWithItem("waxed_rusted_cut_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));

    //waxed cut iron stairs

    public static final Supplier<Block> WAXED_CUT_IRON_STAIRS = regWithItem("waxed_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_CUT_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_CUT_IRON_STAIRS = regWithItem("waxed_exposed_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_EXPOSED_CUT_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON_STAIRS = regWithItem("waxed_weathered_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_WEATHERED_CUT_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON_STAIRS = regWithItem("waxed_rusted_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_RUSTED_CUT_IRON, Properties.copy(CUT_IRON.get())));

    //waxed cut iron slabs

    public static final Supplier<Block> WAXED_CUT_IRON_SLAB = regWithItem("waxed_cut_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_CUT_IRON_SLAB = regWithItem("waxed_exposed_cut_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON_SLAB = regWithItem("waxed_weathered_cut_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON_SLAB = regWithItem("waxed_rusted_cut_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));


    //plate iron
    public static final Supplier<Block> PLATE_IRON = regWithItem("plate_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> EXPOSED_PLATE_IRON = regWithItem("exposed_plate_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WEATHERED_PLATE_IRON = regWithItem("weathered_plate_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> RUSTED_PLATE_IRON = regWithItem("rusted_plate_iron", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BLOCK, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED));

    //plate iron stairs

    public static final Supplier<Block> PLATE_IRON_STAIRS = regWithItem("plate_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED, PLATE_IRON));
    public static final Supplier<Block> EXPOSED_PLATE_IRON_STAIRS = regWithItem("exposed_plate_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.EXPOSED, EXPOSED_PLATE_IRON));
    public static final Supplier<Block> WEATHERED_PLATE_IRON_STAIRS = regWithItem("weathered_plate_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.WEATHERED, WEATHERED_PLATE_IRON));
    public static final Supplier<Block> RUSTED_PLATE_IRON_STAIRS = regWithItem("rusted_plate_iron_stairs", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_STAIRS, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.RUSTED, RUSTED_PLATE_IRON));

    //plate iron slab

    public static final Supplier<Block> PLATE_IRON_SLAB = regWithItem("plate_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> EXPOSED_PLATE_IRON_SLAB = regWithItem("exposed_plate_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WEATHERED_PLATE_IRON_SLAB = regWithItem("weathered_plate_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> RUSTED_PLATE_IRON_SLAB = regWithItem("rusted_plate_iron_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_SLAB, Properties.copy(PLATE_IRON.get()),
                    Rustable.RustLevel.RUSTED));

    //waxed plate iron

    public static final Supplier<Block> WAXED_PLATE_IRON = regWithItem("waxed_plate_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_PLATE_IRON = regWithItem("waxed_exposed_plate_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON = regWithItem("waxed_weathered_plate_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON = regWithItem("waxed_rusted_plate_iron", () ->
            new Block(Properties.copy(CUT_IRON.get())));

    //waxed plate iron stairs

    public static final Supplier<Block> WAXED_PLATE_IRON_STAIRS = regWithItem("waxed_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_PLATE_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_PLATE_IRON_STAIRS = regWithItem("waxed_exposed_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_EXPOSED_PLATE_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON_STAIRS = regWithItem("waxed_weathered_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_WEATHERED_PLATE_IRON, Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON_STAIRS = regWithItem("waxed_rusted_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_RUSTED_PLATE_IRON, Properties.copy(CUT_IRON.get())));

    //waxed plate iron slab

    public static final Supplier<Block> WAXED_PLATE_IRON_SLAB = regWithItem("waxed_plate_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_EXPOSED_PLATE_IRON_SLAB = regWithItem("waxed_exposed_plate_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON_SLAB = regWithItem("waxed_weathered_plate_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON_SLAB = regWithItem("waxed_rusted_plate_iron_slab", () ->
            new SlabBlock(Properties.copy(CUT_IRON.get())));

    //iron door

    public static final Supplier<Block> EXPOSED_IRON_DOOR = regWithItem("exposed_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(Blocks.IRON_DOOR).sound(SoundType.COPPER),
                    Rustable.RustLevel.EXPOSED), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> WEATHERED_IRON_DOOR = regWithItem("weathered_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.WEATHERED), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> RUSTED_IRON_DOOR = regWithItem("rusted_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.RUSTED), CreativeModeTab.TAB_DECORATIONS);

    //iron trapdoor

    public static final Supplier<Block> EXPOSED_IRON_TRAPDOOR = regWithItem("exposed_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(Blocks.IRON_TRAPDOOR).sound(SoundType.COPPER),
                    Rustable.RustLevel.EXPOSED), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> WEATHERED_IRON_TRAPDOOR = regWithItem("weathered_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(EXPOSED_IRON_TRAPDOOR.get()),
                    Rustable.RustLevel.WEATHERED), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> RUSTED_IRON_TRAPDOOR = regWithItem("rusted_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(EXPOSED_IRON_TRAPDOOR.get()),
                    Rustable.RustLevel.RUSTED), CreativeModeTab.TAB_DECORATIONS);

    //iron bars

    public static final Supplier<Block> EXPOSED_IRON_BARS = regWithItem("exposed_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS, Properties.copy(Blocks.IRON_BARS).sound(SoundType.COPPER),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WEATHERED_IRON_BARS = regWithItem("weathered_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS, Properties.copy(EXPOSED_IRON_BARS.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> RUSTED_IRON_BARS = regWithItem("rusted_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS, Properties.copy(EXPOSED_IRON_BARS.get()),
                    Rustable.RustLevel.RUSTED));

    //waxed iron door

    public static final Supplier<Block> WAXED_IRON_DOOR = regWithItem("waxed_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> WAXED_EXPOSED_IRON_DOOR = regWithItem("waxed_exposed_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WAXED_WEATHERED_IRON_DOOR = regWithItem("waxed_weathered_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> WAXED_RUSTED_IRON_DOOR = regWithItem("waxed_rusted_iron_door", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_DOOR, Properties.copy(EXPOSED_IRON_DOOR.get()),
                    Rustable.RustLevel.RUSTED));

    //waxed trapdoor

    public static final Supplier<Block> WAXED_IRON_TRAPDOOR = regWithItem("waxed_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(Blocks.IRON_TRAPDOOR).sound(SoundType.COPPER),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> WAXED_EXPOSED_IRON_TRAPDOOR = regWithItem("waxed_exposed_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(WAXED_IRON_TRAPDOOR.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WAXED_WEATHERED_IRON_TRAPDOOR = regWithItem("waxed_weathered_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(WAXED_IRON_TRAPDOOR.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> WAXED_RUSTED_IRON_TRAPDOOR = regWithItem("waxed_rusted_iron_trapdoor", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_TRAPDOOR, Properties.copy(WAXED_IRON_TRAPDOOR.get()),
                    Rustable.RustLevel.EXPOSED));

    //waxed iron bars

    //TODO: rethink sound since its diff that iron bars
    public static final Supplier<Block> WAXED_IRON_BARS = regWithItem("waxed_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS, Properties.copy(Blocks.IRON_BARS).sound(SoundType.COPPER),
                    Rustable.RustLevel.UNAFFECTED));
    public static final Supplier<Block> WAXED_EXPOSED_IRON_BARS = regWithItem("waxed_exposed_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS,Properties.copy(WAXED_IRON_BARS.get()),
                    Rustable.RustLevel.EXPOSED));
    public static final Supplier<Block> WAXED_WEATHERED_IRON_BARS = regWithItem("waxed_weathered_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS,Properties.copy(WAXED_IRON_BARS.get()),
                    Rustable.RustLevel.WEATHERED));
    public static final Supplier<Block> WAXED_RUSTED_IRON_BARS = regWithItem("waxed_rusted_iron_bars", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_BARS,Properties.copy(WAXED_IRON_BARS.get()),
                    Rustable.RustLevel.RUSTED));

    //cracked end stone

    public static final Supplier<Block> CRACKED_END_STONE_BRICKS = regWithItem("cracked_end_stone_bricks", () ->
            new CrackedBlock(Crackable.CrackLevel.CRACKED, ModItems.END_STONE_BRICK, Properties.copy(Blocks.END_STONE)));
    public static final Supplier<Block> CRACKED_END_STONE_BRICK_STAIRS = regWithItem("cracked_end_stone_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, CRACKED_END_STONE_BRICKS, ModItems.END_STONE_BRICK, Properties.copy(Blocks.END_STONE)));
    public static final Supplier<Block> CRACKED_END_STONE_BRICK_SLAB = regWithItem("cracked_end_stone_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.END_STONE_BRICK, Properties.copy(Blocks.END_STONE)));
    public static final Supplier<Block> CRACKED_END_STONE_BRICK_WALL = regWithItem("cracked_end_stone_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.END_STONE_BRICK, Properties.copy(Blocks.END_STONE)));
    public static final Supplier<Block> CRACKED_END_STONE_BRICK_VERTICAL_SLAB = regWithItem("cracked_end_stone_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.END_STONE_BRICK,
                    Properties.copy(CRACKED_END_STONE_BRICK_SLAB.get())), "quark");

    //cracked prismarine

    public static final Supplier<Block> CRACKED_PRISMARINE_BRICKS = regWithItem("cracked_prismarine_bricks", () ->
            new CrackedBlock(Crackable.CrackLevel.CRACKED, ModItems.PRISMARINE_BRICK, Properties.copy(Blocks.PRISMARINE)));
    public static final Supplier<Block> CRACKED_PRISMARINE_BRICK_STAIRS = regWithItem("cracked_prismarine_brick_stairs", () ->
            new CrackedStairsBlock(Crackable.CrackLevel.CRACKED, CRACKED_END_STONE_BRICKS, ModItems.PRISMARINE_BRICK, Properties.copy(Blocks.PRISMARINE)));
    public static final Supplier<Block> CRACKED_PRISMARINE_BRICK_SLAB = regWithItem("cracked_prismarine_brick_slab", () ->
            new CrackedSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.PRISMARINE_BRICK, Properties.copy(Blocks.PRISMARINE)));
    public static final Supplier<Block> CRACKED_PRISMARINE_BRICK_WALL = regWithItem("cracked_prismarine_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.CRACKED, ModItems.PRISMARINE_BRICK, Properties.copy(Blocks.PRISMARINE)));
    public static final Supplier<Block> CRACKED_PRISMARINE_BRICK_VERTICAL_SLAB = regWithItem("cracked_prismarine_brick_vertical_slab", () ->
            new CrackedVerticalSlabBlock(Crackable.CrackLevel.CRACKED, ModItems.PRISMARINE_BRICK,
                    Properties.copy(CRACKED_PRISMARINE_BRICK_SLAB.get())), "quark");

    //prismarine brick wall

    public static final Supplier<Block> PRISMARINE_BRICK_WALL = regWithItem("prismarine_brick_wall", () ->
            new CrackedWallBlock(Crackable.CrackLevel.UNCRACKED, ModItems.PRISMARINE_BRICK, Properties.copy(Blocks.PRISMARINE)));

    //vitrified sand

    public static final Supplier<Block> FULGURITE = regWithItem("fulgurite", () ->
            new FulguriteBlock(7, 3, Properties.copy(Blocks.GLASS)
                    .instabreak().lightLevel((s) -> s.getValue(FulguriteBlock.POWERED) ? 5 : 0)
                    .dynamicShape().requiresCorrectToolForDrops()));

    public static final Supplier<Block> VITRIFIED_SAND = regWithItem("vitrified_sand", () ->
            new GlassBlock(Properties.of(Material.GLASS, MaterialColor.TERRACOTTA_YELLOW)
                    .strength(2f, 6f).sound(SoundType.TUFF)
                    .requiresCorrectToolForDrops().noOcclusion().isViewBlocking((s, l, p) -> false)));

    //soil blocks

    public static final Supplier<Block> HUMUS = regWithItem("humus", () ->
            new SoilBlock(Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_GREEN)
                    .strength(0.5f).sound(SoundType.GRAVEL)));
    public static final Supplier<Block> FLUVISOL = regWithItem("fluvisol", () ->
            new FluvisolBlock(Properties.of(Material.DIRT, MaterialColor.DEEPSLATE)
                    .strength(0.5F).sound(SoundType.WART_BLOCK).randomTicks()));
    public static final Supplier<Block> SILT = regWithItem("silt", () ->
            new SiltBlock(Properties.of(Material.DIRT, MaterialColor.DEEPSLATE)
                    .strength(0.5F).sound(SoundType.WART_BLOCK).randomTicks()));

    public static final Supplier<Block> VERTISOL = regWithItem("vertisol", () ->
            new CrackedMudBlock(Properties.of(Material.DIRT, MaterialColor.DIRT)
                    .strength(0.5F).sound(SoundType.BASALT).randomTicks()));

    public static final Supplier<Block> CRACKED_MUD = regWithItem("cracked_mud", () ->
            new CrackedMudBlock(Properties.of(Material.DIRT, MaterialColor.DIRT)
                    .strength(2.5F).sound(SoundType.BASALT).randomTicks()));
    public static final Supplier<Block> CRYOSOL = regWithItem("cryosol", () ->
            new SoilBlock(Properties.of(Material.DIRT, MaterialColor.SNOW)
                    .strength(0.5F).sound(SoundType.TUFF).randomTicks()));
    public static final Supplier<Block> PERMAFROST = regWithItem("permafrost", () ->
            new PermafrostBlock(Properties.of(Material.ICE_SOLID, MaterialColor.CLAY)
                    .strength(3F).friction(1F).sound(SoundType.TUFF).randomTicks()));

    //vertical slab

    public static final Supplier<Block> CUT_IRON_VERTICAL_SLAB = regWithItem("cut_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED), "quark");
    public static final Supplier<Block> EXPOSED_CUT_IRON_VERTICAL_SLAB = regWithItem("exposed_cut_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED), "quark");
    public static final Supplier<Block> WEATHERED_CUT_IRON_VERTICAL_SLAB = regWithItem("weathered_cut_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED), "quark");
    public static final Supplier<Block> RUSTED_CUT_IRON_VERTICAL_SLAB = regWithItem("rusted_cut_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED), "quark");

    public static final Supplier<Block> WAXED_CUT_IRON_VERTICAL_SLAB = regWithItem("waxed_cut_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_EXPOSED_CUT_IRON_VERTICAL_SLAB = regWithItem("waxed_exposed_cut_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON_VERTICAL_SLAB = regWithItem("waxed_weathered_cut_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON_VERTICAL_SLAB = regWithItem("waxed_rusted_cut_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");

    public static final Supplier<Block> PLATE_IRON_VERTICAL_SLAB = regWithItem("plate_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.UNAFFECTED), "quark");
    public static final Supplier<Block> EXPOSED_PLATE_IRON_VERTICAL_SLAB = regWithItem("exposed_plate_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.EXPOSED), "quark");
    public static final Supplier<Block> WEATHERED_PLATE_IRON_VERTICAL_SLAB = regWithItem("weathered_plate_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.WEATHERED), "quark");
    public static final Supplier<Block> RUSTED_PLATE_IRON_VERTICAL_SLAB = regWithItem("rusted_plate_iron_vertical_slab", () ->
            RegistryPlatform.createSpecialBlock(BlockType.RUSTABLE_VERTICAL_SLAB, Properties.copy(CUT_IRON.get()),
                    Rustable.RustLevel.RUSTED), "quark");

    public static final Supplier<Block> WAXED_PLATE_IRON_VERTICAL_SLAB = regWithItem("waxed_plate_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_EXPOSED_PLATE_IRON_VERTICAL_SLAB = regWithItem("waxed_exposed_plate_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON_VERTICAL_SLAB = regWithItem("waxed_weathered_plate_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON_VERTICAL_SLAB = regWithItem("waxed_rusted_plate_iron_vertical_slab", () ->
            new VerticalSlabBlock(Properties.copy(CUT_IRON.get())), "quark");

    //frost

    public static final Supplier<Block> ICICLE = regBlock("icicle", () ->
            new IcicleBlock(Properties.of(Material.ICE).randomTicks().instabreak()
                    .sound(SoundType.GLASS).noOcclusion().dynamicShape()));

    public static final Supplier<Block> FROST = regWithItem("frost", () ->
            new FrostBlock(Properties.of(Material.TOP_SNOW)
                    .randomTicks().instabreak().sound(SoundType.POWDER_SNOW).noOcclusion().noCollission()));

    public static final Supplier<Block> FROSTY_GRASS = regWithItem("frosty_grass", () ->
            new FrostyGrassBlock(Properties.copy(Blocks.GRASS).color(MaterialColor.SNOW)
                    .randomTicks().sound(SoundType.POWDER_SNOW)));

    public static final Supplier<Block> FROSTY_FERN = regWithItem("frosty_fern", () ->
            new FrostyGrassBlock(Properties.copy(FROSTY_GRASS.get())));

    public static final Supplier<Block> FROSTY_GLASS = regWithItem("frosty_glass", () ->
            new FrostyGlassBlock(Properties.copy(Blocks.GLASS).randomTicks()));

    public static final Supplier<Block> FROSTY_GLASS_PANE = regWithItem("frosty_glass_pane", () ->
            new FrostyGlassPaneBlock(Properties.copy(Blocks.GLASS_PANE).randomTicks()));

    public static final Supplier<Block> THIN_ICE = regWithItem("thin_ice", () ->
            new ThinIceBlock(Properties.copy(Blocks.ICE)
                    .isViewBlocking(NEVER).isSuffocating(NEVER).isViewBlocking(NEVER)));


    //charred blocks

    public static final Supplier<Block> CHARRED_LOG = regWithItem("charred_log", () ->
            new CharredPillarBlock(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .strength(1.5f, 0.5f).sound(SoundType.BASALT)
                    .lightLevel(litLightLevel(5)).randomTicks()));

    public static final Supplier<Block> CHARRED_PLANKS = regWithItem("charred_planks", () ->
            new CharredBlock(Properties.copy(CHARRED_LOG.get())));

    public static final Supplier<Block> CHARRED_SLAB = regWithItem("charred_slab", () ->
            new CharredSlabBlock(Properties.copy(CHARRED_LOG.get())));

    public static final Supplier<Block> CHARRED_STAIRS = regWithItem("charred_stairs", () ->
            new CharredStairsBlock(CHARRED_PLANKS, Properties.copy(CHARRED_LOG.get())));

    public static final Supplier<Block> CHARRED_FENCE = regWithItem("charred_fence", () ->
            new CharredFenceBlock(Properties.copy(CHARRED_LOG.get())));

    public static final Supplier<Block> CHARRED_FENCE_GATE = regWithItem("charred_fence_gate", () ->
            new CharredFenceGateBlock(Properties.copy(CHARRED_LOG.get())));

    //ash

    public static final Supplier<Block> ASH_BLOCK = regWithItem("soot_block", () ->
            new AshBlock(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.5f)
                    .sound(SoundType.SNOW).lightLevel(litLightLevel(6)).randomTicks());

    public static final Supplier<Block> SOOT = regWithItem("soot", () ->
            new SootLayerBlock(Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.COLOR_BLACK)
                    .noCollission().requiresCorrectToolForDrops().instabreak().sound(SoundType.SNOW)
                    .lightLevel(litLightLevel(5)).randomTicks()));


}
