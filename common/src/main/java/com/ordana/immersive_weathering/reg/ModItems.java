package com.ordana.immersive_weathering.reg;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.blocks.IcicleBlock;
import com.ordana.immersive_weathering.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.common.ModFoods;
import com.ordana.immersive_weathering.common.blocks.LeafPileBlock;
import com.ordana.immersive_weathering.items.LeafPileBlockItem;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.Supplier;

import java.util.function.Supplier;

public class ModItems {

    public static Supplier<BlockItem> regBlockItem(String name, Supplier<? extends Block> blockSup, Item.Properties properties) {
        return RegistryPlatform.registerItem(name, () -> new BlockItem(blockSup.get(), properties));
    }

    public static <T extends Item> Supplier<T> regItem(String name, Supplier<T> itemSup) {
        return RegistryPlatform.registerItem(name, itemSup);
    }


    private static Supplier<BlockItem> regLeafPile(String name, Supplier<LeafPileBlock> oakLeafPile, Item.Properties properties) {
        return regItem(name, () -> new LeafPileBlockItem(oakLeafPile.get(), properties));
    }
    
    public static final Supplier<BlockItem> ICICLE = regItem("icicle", () -> new IcicleBlock(
            ModBlocks.ICICLE.get(), new Item.Properties().food(ModFoods.ICICLE).tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final Supplier<BlockItem> OAK_LEAF_PILE = regLeafPile(ModBlocks.OAK_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));



    public static final Supplier<BlockItem> SPRUCE_LEAF_PILE = regLeafPile(ModBlocks.SPRUCE_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> BIRCH_LEAF_PILE = regLeafPile(ModBlocks.BIRCH_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> JUNGLE_LEAF_PILE = regLeafPile(ModBlocks.JUNGLE_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> ACACIA_LEAF_PILE = regLeafPile(ModBlocks.ACACIA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> DARK_OAK_LEAF_PILE = regLeafPile(ModBlocks.DARK_OAK_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> AZALEA_LEAF_PILE = regLeafPile(ModBlocks.AZALEA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> FLOWERING_AZALEA_LEAF_PILE = regLeafPile(ModBlocks.FLOWERING_AZALEA_LEAF_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<BlockItem> AZALEA_FLOWER_PILE = regLeafPile(ModBlocks.AZALEA_FLOWER_PILE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));


    public static final Supplier<Item> STONE_BRICK = ITEMS.register("stone_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> PRISMARINE_BRICK = ITEMS.register("prismarine_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> END_STONE_BRICK = ITEMS.register("end_stone_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> BLACKSTONE_BRICK = ITEMS.register("blackstone_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> DEEPSLATE_BRICK = ITEMS.register("deepslate_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> DEEPSLATE_TILE = ITEMS.register("deepslate_tile", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final Supplier<Item> AZALEA_FLOWERS = ITEMS.register("azalea_flowers", () -> new AzaleaFlowersItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> FLOWER_CROWN = ITEMS.register("flower_crown", () -> new FlowerCrownItem(FlowerCrownMaterial.INSTANCE, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final Supplier<Item> MOSS_CLUMP = ITEMS.register("moss_clump", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(ModFoods.MOSS_CLUMP)));
    public static final Supplier<Item> GOLDEN_MOSS_CLUMP = ITEMS.register("golden_moss_clump", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(ModFoods.GOLDEN_MOSS_CLUMP)));

    public static final Supplier<Item> OAK_BARK = ITEMS.register("oak_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> BIRCH_BARK = ITEMS.register("birch_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> SPRUCE_BARK = ITEMS.register("spruce_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> JUNGLE_BARK = ITEMS.register("jungle_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> DARK_OAK_BARK = ITEMS.register("dark_oak_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> ACACIA_BARK = ITEMS.register("acacia_bark", () -> new BurnableItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 200));
    public static final Supplier<Item> CRIMSON_SCALES = ITEMS.register("crimson_scales", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final Supplier<Item> WARPED_SCALES = ITEMS.register("warped_scales", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final Supplier<Item> TALLOW = ITEMS.register("tallow", () -> new HoneycombItem(new Item.Properties().tab(CreativeModeTab.MATERIALS)));


    public static final Supplier<Item> HANGING_ROOTS_ITEM = regOverride(Items.HANGING_ROOTS, () ->
            new CeilingAndWallBlockItem(Blocks.HANGING_ROOTS, ModBlocks.HANGING_ROOTS_WALL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final Supplier<Item> ICE_SICKLE = ITEMS.register("ice_sickle", () ->
            new IceSickleItem(IcicleToolMaterial.INSTANCE, 5, -1f, new Item.Properties().food(ModFoods.ICICLE).tab(CreativeModeTab.TAB_COMBAT)));


}
