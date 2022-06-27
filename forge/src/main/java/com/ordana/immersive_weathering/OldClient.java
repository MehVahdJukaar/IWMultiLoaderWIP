package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.common.ModBlocks;

import com.ordana.immersive_weathering.common.blocks.LeafPilesRegistry;
import com.ordana.immersive_weathering.mixin.BlockColorAccessor;
import com.ordana.immersive_weathering.mixin.ItemColorAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.Map;

@Mod.EventBusSubscriber(modid = ImmersiveWeathering.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OldClient {


    @Mod.EventBusSubscriber(modid = ImmersiveWeathering.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientTicker {

        private static boolean clientTicked = false;

        @SubscribeEvent
        public static void firstClientTick(TickEvent.ClientTickEvent event) {
            if (!clientTicked && event.phase == TickEvent.Phase.END) {
                // if(ModList.get().isLoaded("quark")) QuarkPlugin.onFirstClientTick();

                try {
                    //TODO: we dont need this
                    BlockColors bc = Minecraft.getInstance().getBlockColors();
                    Map<IRegistryDelegate<Block>, BlockColor> blockColorMap = ((BlockColorAccessor) bc).getBlockColors();

                    LeafPilesRegistry.LEAF_PILES.get().forEach((key, value) -> {
                        BlockState leafState = key.defaultBlockState();
                        BlockColor color = blockColorMap.get(key.delegate);
                        if (color != null) {
                            BlockColor newBc = (s, t, p, i) -> color.getColor(leafState, t, p, i);
                            bc.register(newBc, value);
                        }
                    });

                } catch (Exception ignored) {
                }

                try {
                    ItemColors ic = Minecraft.getInstance().getItemColors();
                    Map<IRegistryDelegate<Item>, ItemColor> itemColorMap = ((ItemColorAccessor) ic).getItemColors();

                    LeafPilesRegistry.LEAF_PILES.get().forEach((key, value) -> {
                        ItemStack leafItem = new ItemStack(key);
                        ItemColor baseColor = itemColorMap.get(leafItem.getItem().delegate);
                        if (baseColor != null) {
                            ItemColor newIc = (s, i) -> baseColor.getColor(leafItem, i);
                            ic.register(newIc, value);
                        }
                    });

                } catch (Exception ignored) {
                }

                clientTicked = true;
            }

        }
    }


}
