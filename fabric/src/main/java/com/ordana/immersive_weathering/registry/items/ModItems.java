package com.ordana.immersive_weathering.registry.items;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.registry.ModFoods;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {

    public static final BlockItem ICICLE = new BlockItem(ModBlocks.ICICLE, new Item.Properties().food(ModFoods.ICICLE).tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Item ICE_SICKLE = new IceSickleItem(IcicleToolMaterial.INSTANCE, 5, -1f, new Item.Properties().food(ModFoods.ICICLE).tab(CreativeModeTab.TAB_COMBAT));
    public static final BlockItem FROST = new BlockItem(ModBlocks.FROST, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FROSTY_GRASS = new BlockItem(ModBlocks.FROSTY_GRASS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FROSTY_FERN = new BlockItem(ModBlocks.FROSTY_FERN, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FROSTY_GLASS = new BlockItem(ModBlocks.FROSTY_GLASS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FROSTY_GLASS_PANE = new BlockItem(ModBlocks.FROSTY_GLASS_PANE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem THIN_ICE = new BlockItem(ModBlocks.THIN_ICE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem FULGURITE = new BlockItem(ModBlocks.FULGURITE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem VITRIFIED_SAND = new BlockItem(ModBlocks.VITRIFIED_SAND, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final Item POND_WATER = new PondWaterItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.POND_WATER).stacksTo(16));
    public static final Item AZALEA_FLOWERS = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(ModFoods.AZALEA_FLOWER));
    public static final Item FLOWER_CROWN = new FlowerCrownItem(FlowerCrownMaterial.INSTANCE, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
    public static final Item MOSS_CLUMP = new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.MOSS_CLUMP));
    public static final Item GOLDEN_MOSS_CLUMP = new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.GOLDEN_MOSS_CLUMP));
    public static final Item ENCHANTED_GOLDEN_MOSS_CLUMP = new EnchantedGoldenMossClumpItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).rarity(Rarity.EPIC).food(ModFoods.ENCHANTED_GOLDEN_MOSS_CLUMP));

    public static final BlockItem OAK_LEAF_PILE = new LeafPileBlockItem(ModBlocks.OAK_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem SPRUCE_LEAF_PILE = new LeafPileBlockItem(ModBlocks.SPRUCE_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem BIRCH_LEAF_PILE = new LeafPileBlockItem(ModBlocks.BIRCH_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem JUNGLE_LEAF_PILE = new LeafPileBlockItem(ModBlocks.JUNGLE_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem ACACIA_LEAF_PILE = new LeafPileBlockItem(ModBlocks.ACACIA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem DARK_OAK_LEAF_PILE = new LeafPileBlockItem(ModBlocks.DARK_OAK_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem AZALEA_LEAF_PILE = new LeafPileBlockItem(ModBlocks.AZALEA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FLOWERING_AZALEA_LEAF_PILE = new LeafPileBlockItem(ModBlocks.FLOWERING_AZALEA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem AZALEA_FLOWER_PILE = new LeafPileBlockItem(ModBlocks.AZALEA_FLOWER_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final Item OAK_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item BIRCH_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item SPRUCE_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item JUNGLE_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item DARK_OAK_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item ACACIA_BARK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item CRIMSON_SCALES = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item WARPED_SCALES = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));

    public static final BlockItem IVY = new BlockItem(ModBlocks.IVY, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem WEEDS = new BlockItem(ModBlocks.WEEDS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem SAND_LAYER_BLOCK = new BlockItem(ModBlocks.SAND_LAYER_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem RED_SAND_LAYER_BLOCK = new BlockItem(ModBlocks.RED_SAND_LAYER_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem ASH_LAYER_BLOCK = new BlockItem(ModBlocks.ASH_LAYER_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem ASH_BLOCK = new BlockItem(ModBlocks.ASH_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem SOOT = new BlockItem(ModBlocks.SOOT, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Item TALLOW = new HoneycombItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));

    public static final BlockItem MULCH_BLOCK = new BlockItem(ModBlocks.MULCH_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem NULCH_BLOCK = new BlockItem(ModBlocks.NULCH_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem HUMUS = new BlockItem(ModBlocks.HUMUS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem FLUVISOL = new BlockItem(ModBlocks.FLUVISOL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem SILT = new BlockItem(ModBlocks.SILT, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem VERTISOL = new BlockItem(ModBlocks.VERTISOL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem CRACKED_MUD = new BlockItem(ModBlocks.CRACKED_MUD, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem CRYOSOL = new BlockItem(ModBlocks.CRYOSOL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem PERMAFROST = new BlockItem(ModBlocks.PERMAFROST, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem ROOTED_GRASS_BLOCK = new BlockItem(ModBlocks.ROOTED_GRASS_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CHARRED_LOG = new BlockItem(ModBlocks.CHARRED_LOG, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CHARRED_PLANKS = new BlockItem(ModBlocks.CHARRED_PLANKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CHARRED_SLAB = new BlockItem(ModBlocks.CHARRED_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CHARRED_STAIRS = new BlockItem(ModBlocks.CHARRED_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CHARRED_FENCE = new BlockItem(ModBlocks.CHARRED_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem CHARRED_FENCE_GATE = new BlockItem(ModBlocks.CHARRED_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem CHISELED_PRISMARINE_BRICKS = new BlockItem(ModBlocks.CHISELED_PRISMARINE_BRICKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem ROTTEN_LOG = new BlockItem(ModBlocks.ROTTEN_LOG, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem ROTTEN_PLANKS = new BlockItem(ModBlocks.ROTTEN_PLANKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem ROTTEN_SLAB = new BlockItem(ModBlocks.ROTTEN_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem ROTTEN_STAIRS = new BlockItem(ModBlocks.ROTTEN_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem ROTTEN_FENCE = new BlockItem(ModBlocks.ROTTEN_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem ROTTEN_FENCE_GATE = new BlockItem(ModBlocks.ROTTEN_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem MOSSY_BRICKS = new BlockItem(ModBlocks.MOSSY_BRICKS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_BRICK_STAIRS = new BlockItem(ModBlocks.MOSSY_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_BRICK_SLAB = new BlockItem(ModBlocks.MOSSY_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_BRICK_WALL = new BlockItem(ModBlocks.MOSSY_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem MOSSY_STONE = new BlockItem(ModBlocks.MOSSY_STONE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_STONE_STAIRS = new BlockItem(ModBlocks.MOSSY_STONE_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_STONE_SLAB = new BlockItem(ModBlocks.MOSSY_STONE_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem MOSSY_STONE_WALL = new BlockItem(ModBlocks.MOSSY_STONE_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem STONE_WALL = new BlockItem(ModBlocks.STONE_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_BRICKS = new BlockItem(ModBlocks.CRACKED_BRICKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_PRISMARINE_BRICKS = new BlockItem(ModBlocks.CRACKED_PRISMARINE_BRICKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_PRISMARINE_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_PRISMARINE_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_PRISMARINE_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_PRISMARINE_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_PRISMARINE_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_PRISMARINE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem PRISMARINE_BRICK_WALL = new BlockItem(ModBlocks.PRISMARINE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem DARK_PRISMARINE_WALL = new BlockItem(ModBlocks.DARK_PRISMARINE_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_END_STONE_BRICKS = new BlockItem(ModBlocks.CRACKED_END_STONE_BRICKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_END_STONE_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_END_STONE_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_END_STONE_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_END_STONE_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_END_STONE_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_END_STONE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_STONE_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_STONE_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_STONE_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_STONE_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_STONE_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_STONE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_POLISHED_BLACKSTONE_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_NETHER_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_NETHER_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_NETHER_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_NETHER_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_NETHER_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_NETHER_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_DEEPSLATE_BRICK_STAIRS = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_DEEPSLATE_BRICK_SLAB = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_DEEPSLATE_BRICK_WALL = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_BRICK_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem CRACKED_DEEPSLATE_TILE_STAIRS = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_DEEPSLATE_TILE_SLAB = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_TILE_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem CRACKED_DEEPSLATE_TILE_WALL = new BlockItem(ModBlocks.CRACKED_DEEPSLATE_TILE_WALL, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final Item STONE_BRICK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item PRISMARINE_BRICK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item DEEPSLATE_BRICK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item DEEPSLATE_TILE = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item BLACKSTONE_BRICK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item END_STONE_BRICK = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));

    public static final Item STEEL_WOOL = new Item(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_TOOLS));

    //cut iron
    public static final BlockItem CUT_IRON = new BlockItem(ModBlocks.CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_CUT_IRON = new BlockItem(ModBlocks.EXPOSED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_CUT_IRON = new BlockItem(ModBlocks.WEATHERED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_CUT_IRON = new BlockItem(ModBlocks.RUSTED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem CUT_IRON_STAIRS = new BlockItem(ModBlocks.CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.EXPOSED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.WEATHERED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.RUSTED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem CUT_IRON_SLAB = new BlockItem(ModBlocks.CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_CUT_IRON_SLAB = new BlockItem(ModBlocks.EXPOSED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_CUT_IRON_SLAB = new BlockItem(ModBlocks.WEATHERED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_CUT_IRON_SLAB = new BlockItem(ModBlocks.RUSTED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_CUT_IRON = new BlockItem(ModBlocks.WAXED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_CUT_IRON = new BlockItem(ModBlocks.WAXED_EXPOSED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_CUT_IRON = new BlockItem(ModBlocks.WAXED_WEATHERED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_CUT_IRON = new BlockItem(ModBlocks.WAXED_RUSTED_CUT_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_EXPOSED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_WEATHERED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_CUT_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_RUSTED_CUT_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_CUT_IRON_SLAB = new BlockItem(ModBlocks.WAXED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_CUT_IRON_SLAB = new BlockItem(ModBlocks.WAXED_EXPOSED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_CUT_IRON_SLAB = new BlockItem(ModBlocks.WAXED_WEATHERED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_CUT_IRON_SLAB = new BlockItem(ModBlocks.WAXED_RUSTED_CUT_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    //plate iron
    public static final BlockItem PLATE_IRON = new BlockItem(ModBlocks.PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_PLATE_IRON = new BlockItem(ModBlocks.EXPOSED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_PLATE_IRON = new BlockItem(ModBlocks.WEATHERED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_PLATE_IRON = new BlockItem(ModBlocks.RUSTED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem PLATE_IRON_STAIRS = new BlockItem(ModBlocks.PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.EXPOSED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.WEATHERED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.RUSTED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem PLATE_IRON_SLAB = new BlockItem(ModBlocks.PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.EXPOSED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.WEATHERED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem RUSTED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.RUSTED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_PLATE_IRON = new BlockItem(ModBlocks.WAXED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_PLATE_IRON = new BlockItem(ModBlocks.WAXED_EXPOSED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_PLATE_IRON = new BlockItem(ModBlocks.WAXED_WEATHERED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_PLATE_IRON = new BlockItem(ModBlocks.WAXED_RUSTED_PLATE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_EXPOSED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_WEATHERED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_PLATE_IRON_STAIRS = new BlockItem(ModBlocks.WAXED_RUSTED_PLATE_IRON_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem WAXED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.WAXED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.WAXED_EXPOSED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.WAXED_WEATHERED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final BlockItem WAXED_RUSTED_PLATE_IRON_SLAB = new BlockItem(ModBlocks.WAXED_RUSTED_PLATE_IRON_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final BlockItem EXPOSED_IRON_DOOR = new BlockItem(ModBlocks.EXPOSED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WEATHERED_IRON_DOOR = new BlockItem(ModBlocks.WEATHERED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem RUSTED_IRON_DOOR = new BlockItem(ModBlocks.RUSTED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem EXPOSED_IRON_TRAPDOOR = new BlockItem(ModBlocks.EXPOSED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WEATHERED_IRON_TRAPDOOR = new BlockItem(ModBlocks.WEATHERED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem RUSTED_IRON_TRAPDOOR = new BlockItem(ModBlocks.RUSTED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem EXPOSED_IRON_BARS = new BlockItem(ModBlocks.EXPOSED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem WEATHERED_IRON_BARS = new BlockItem(ModBlocks.WEATHERED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem RUSTED_IRON_BARS = new BlockItem(ModBlocks.RUSTED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final BlockItem WAXED_IRON_DOOR = new BlockItem(ModBlocks.WAXED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_EXPOSED_IRON_DOOR = new BlockItem(ModBlocks.WAXED_EXPOSED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_WEATHERED_IRON_DOOR = new BlockItem(ModBlocks.WAXED_WEATHERED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_RUSTED_IRON_DOOR = new BlockItem(ModBlocks.WAXED_RUSTED_IRON_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem WAXED_IRON_TRAPDOOR = new BlockItem(ModBlocks.WAXED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_EXPOSED_IRON_TRAPDOOR = new BlockItem(ModBlocks.WAXED_EXPOSED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_WEATHERED_IRON_TRAPDOOR = new BlockItem(ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final BlockItem WAXED_RUSTED_IRON_TRAPDOOR = new BlockItem(ModBlocks.WAXED_RUSTED_IRON_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static final BlockItem WAXED_IRON_BARS = new BlockItem(ModBlocks.WAXED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem WAXED_EXPOSED_IRON_BARS = new BlockItem(ModBlocks.WAXED_EXPOSED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem WAXED_WEATHERED_IRON_BARS = new BlockItem(ModBlocks.WAXED_WEATHERED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final BlockItem WAXED_RUSTED_IRON_BARS = new BlockItem(ModBlocks.WAXED_RUSTED_IRON_BARS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static void registerItems() {

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "icicle"), ICICLE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "ice_sickle"), ICE_SICKLE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "frost"), FROST);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "frosty_grass"), FROSTY_GRASS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "frosty_fern"), FROSTY_FERN);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "frosty_glass"), FROSTY_GLASS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "frosty_glass_pane"), FROSTY_GLASS_PANE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "thin_ice"), THIN_ICE);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "vitrified_sand"), VITRIFIED_SAND);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "fulgurite"), FULGURITE);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "pond_water"), POND_WATER);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "azalea_flowers"), AZALEA_FLOWERS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "flower_crown"), FLOWER_CROWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "moss_clump"), MOSS_CLUMP);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "golden_moss_clump"), GOLDEN_MOSS_CLUMP);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "enchanted_golden_moss_clump"), ENCHANTED_GOLDEN_MOSS_CLUMP);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "oak_leaf_pile"), OAK_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "spruce_leaf_pile"), SPRUCE_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "birch_leaf_pile"), BIRCH_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "jungle_leaf_pile"), JUNGLE_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "acacia_leaf_pile"), ACACIA_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "dark_oak_leaf_pile"), DARK_OAK_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "azalea_leaf_pile"), AZALEA_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "flowering_azalea_leaf_pile"), FLOWERING_AZALEA_LEAF_PILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "azalea_flower_pile"), AZALEA_FLOWER_PILE);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "oak_bark"), OAK_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "spruce_bark"), SPRUCE_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "birch_bark"), BIRCH_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "jungle_bark"), JUNGLE_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "acacia_bark"), ACACIA_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "dark_oak_bark"), DARK_OAK_BARK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "crimson_scales"), CRIMSON_SCALES);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "warped_scales"), WARPED_SCALES);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "ivy"), IVY);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weeds"), WEEDS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "sand_layer_block"), SAND_LAYER_BLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "red_sand_layer_block"), RED_SAND_LAYER_BLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "soot"), SOOT);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "ash_layer_block"), ASH_LAYER_BLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "ash_block"), ASH_BLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "tallow"), TALLOW);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mulch_block"), MULCH_BLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "nulch_block"), NULCH_BLOCK);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "humus"), HUMUS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "fluvisol"), FLUVISOL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "silt"), SILT);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "vertisol"), VERTISOL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_mud"), CRACKED_MUD);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cryosol"), CRYOSOL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "permafrost"), PERMAFROST);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rooted_grass_block"), ROOTED_GRASS_BLOCK);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_log"), CHARRED_LOG);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_planks"), CHARRED_PLANKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_slab"), CHARRED_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_stairs"), CHARRED_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_fence"), CHARRED_FENCE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "charred_fence_gate"), CHARRED_FENCE_GATE);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "chiseled_prismarine_bricks"), CHISELED_PRISMARINE_BRICKS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_log"), ROTTEN_LOG);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_planks"), ROTTEN_PLANKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_slab"), ROTTEN_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_stairs"), ROTTEN_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_fence"), ROTTEN_FENCE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rotten_fence_gate"), ROTTEN_FENCE_GATE);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_bricks"), MOSSY_BRICKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_brick_stairs"), MOSSY_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_brick_slab"), MOSSY_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_brick_wall"), MOSSY_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_stone"), MOSSY_STONE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_stone_stairs"), MOSSY_STONE_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_stone_slab"), MOSSY_STONE_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "mossy_stone_wall"), MOSSY_STONE_WALL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "stone_wall"), STONE_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_bricks"), CRACKED_BRICKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_brick_stairs"), CRACKED_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_brick_slab"), CRACKED_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_brick_wall"), CRACKED_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_prismarine_bricks"), CRACKED_PRISMARINE_BRICKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_prismarine_brick_stairs"), CRACKED_PRISMARINE_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_prismarine_brick_slab"), CRACKED_PRISMARINE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_prismarine_brick_wall"), CRACKED_PRISMARINE_BRICK_WALL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "prismarine_brick_wall"), PRISMARINE_BRICK_WALL);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "dark_prismarine_wall"), DARK_PRISMARINE_WALL);


        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_stone_brick_stairs"), CRACKED_STONE_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_stone_brick_slab"), CRACKED_STONE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_stone_brick_wall"), CRACKED_STONE_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_polished_blackstone_brick_stairs"), CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_polished_blackstone_brick_slab"), CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_polished_blackstone_brick_wall"), CRACKED_POLISHED_BLACKSTONE_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_nether_brick_stairs"), CRACKED_NETHER_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_nether_brick_slab"), CRACKED_NETHER_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_nether_brick_wall"), CRACKED_NETHER_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_brick_stairs"), CRACKED_DEEPSLATE_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_brick_slab"), CRACKED_DEEPSLATE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_brick_wall"), CRACKED_DEEPSLATE_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_tile_stairs"), CRACKED_DEEPSLATE_TILE_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_tile_slab"), CRACKED_DEEPSLATE_TILE_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_deepslate_tile_wall"), CRACKED_DEEPSLATE_TILE_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_end_stone_bricks"), CRACKED_END_STONE_BRICKS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_end_stone_brick_stairs"), CRACKED_END_STONE_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_end_stone_brick_slab"), CRACKED_END_STONE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cracked_end_stone_brick_wall"), CRACKED_END_STONE_BRICK_WALL);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "stone_brick"), STONE_BRICK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "prismarine_brick"), PRISMARINE_BRICK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "deepslate_brick"), DEEPSLATE_BRICK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "deepslate_tile"), DEEPSLATE_TILE);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "blackstone_brick"), BLACKSTONE_BRICK);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "end_stone_brick"), END_STONE_BRICK);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "steel_wool"), STEEL_WOOL);

        //plate iron
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "plate_iron"), PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_plate_iron"), EXPOSED_PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_plate_iron"), WEATHERED_PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_plate_iron"), RUSTED_PLATE_IRON);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "plate_iron_stairs"), PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_plate_iron_stairs"), EXPOSED_PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_plate_iron_stairs"), WEATHERED_PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_plate_iron_stairs"), RUSTED_PLATE_IRON_STAIRS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "plate_iron_slab"), PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_plate_iron_slab"), EXPOSED_PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_plate_iron_slab"), WEATHERED_PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_plate_iron_slab"), RUSTED_PLATE_IRON_SLAB);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_plate_iron"), WAXED_PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_plate_iron"), WAXED_EXPOSED_PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_plate_iron"), WAXED_WEATHERED_PLATE_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_plate_iron"), WAXED_RUSTED_PLATE_IRON);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_plate_iron_stairs"), WAXED_PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_plate_iron_stairs"), WAXED_EXPOSED_PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_plate_iron_stairs"), WAXED_WEATHERED_PLATE_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_plate_iron_stairs"), WAXED_RUSTED_PLATE_IRON_STAIRS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_plate_iron_slab"), WAXED_PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_plate_iron_slab"), WAXED_EXPOSED_PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_plate_iron_slab"), WAXED_WEATHERED_PLATE_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_plate_iron_slab"), WAXED_RUSTED_PLATE_IRON_SLAB);

        //cut iron
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cut_iron"), CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_cut_iron"), EXPOSED_CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_cut_iron"), WEATHERED_CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_cut_iron"), RUSTED_CUT_IRON);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cut_iron_stairs"), CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_cut_iron_stairs"), EXPOSED_CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_cut_iron_stairs"), WEATHERED_CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_cut_iron_stairs"), RUSTED_CUT_IRON_STAIRS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "cut_iron_slab"), CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_cut_iron_slab"), EXPOSED_CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_cut_iron_slab"), WEATHERED_CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_cut_iron_slab"), RUSTED_CUT_IRON_SLAB);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_cut_iron"), WAXED_CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_cut_iron"), WAXED_EXPOSED_CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_cut_iron"), WAXED_WEATHERED_CUT_IRON);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_cut_iron"), WAXED_RUSTED_CUT_IRON);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_cut_iron_stairs"), WAXED_CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_cut_iron_stairs"), WAXED_EXPOSED_CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_cut_iron_stairs"), WAXED_WEATHERED_CUT_IRON_STAIRS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_cut_iron_stairs"), WAXED_RUSTED_CUT_IRON_STAIRS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_cut_iron_slab"), WAXED_CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_cut_iron_slab"), WAXED_EXPOSED_CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_cut_iron_slab"), WAXED_WEATHERED_CUT_IRON_SLAB);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_cut_iron_slab"), WAXED_RUSTED_CUT_IRON_SLAB);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_iron_door"), EXPOSED_IRON_DOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_iron_door"), WEATHERED_IRON_DOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_iron_door"), RUSTED_IRON_DOOR);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_iron_trapdoor"), EXPOSED_IRON_TRAPDOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_iron_trapdoor"), WEATHERED_IRON_TRAPDOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_iron_trapdoor"), RUSTED_IRON_TRAPDOOR);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "exposed_iron_bars"), EXPOSED_IRON_BARS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "weathered_iron_bars"), WEATHERED_IRON_BARS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "rusted_iron_bars"), RUSTED_IRON_BARS);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_iron_door"), WAXED_IRON_DOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_iron_door"), WAXED_EXPOSED_IRON_DOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_iron_door"), WAXED_WEATHERED_IRON_DOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_iron_door"), WAXED_RUSTED_IRON_DOOR);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_iron_trapdoor"), WAXED_IRON_TRAPDOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_iron_trapdoor"), WAXED_EXPOSED_IRON_TRAPDOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_iron_trapdoor"), WAXED_WEATHERED_IRON_TRAPDOOR);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_iron_trapdoor"), WAXED_RUSTED_IRON_TRAPDOOR);

        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_iron_bars"), WAXED_IRON_BARS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_exposed_iron_bars"), WAXED_EXPOSED_IRON_BARS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_weathered_iron_bars"), WAXED_WEATHERED_IRON_BARS);
        Registry.register(Registry.ITEM, new ResourceLocation(ImmersiveWeathering.MOD_ID, "waxed_rusted_iron_bars"), WAXED_RUSTED_IRON_BARS);
    }
}
