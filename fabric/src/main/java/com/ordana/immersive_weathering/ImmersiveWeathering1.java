package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.block_growth.BlockGrowthHandler;
import com.ordana.immersive_weathering.block_growth.rule_test.BlockSetMatchTest;
import com.ordana.immersive_weathering.block_growth.rule_test.FluidMatchTest;
import com.ordana.immersive_weathering.block_growth.rule_test.LogMatchTest;
import com.ordana.immersive_weathering.config.ServerConfig;
import com.ordana.immersive_weathering.registry.*;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.entities.ModEntities;
import com.ordana.immersive_weathering.registry.items.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class ImmersiveWeathering1 implements ModInitializer {

    public static final String MOD_ID = "immersive_weathering";

    public static final Logger LOGGER = LogManager.getLogger();

    private static final Supplier<ServerConfig> CONFIG = Util.make(() -> {
        AutoConfig.register(ServerConfig.class, JanksonConfigSerializer::new);
        return AutoConfig.getConfigHolder(ServerConfig.class);
    });

    public static ServerConfig getConfig() {
        return CONFIG.get();
    }

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new BlockGrowthHandler());

        ModEntities.registerEntities();
        ModLootTables.registerLootTables();
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModFlammableBlocks.registerFlammable();
        ModWaxable.registerWaxable();
        ModCompostable.registerCompostable();
        ModFuel.registerFuel();

        ModParticles.registerParticles();
        ModEvents.registerEvents();
        ModFeatures.registerFeatures();

        FluidMatchTest.init();
        LogMatchTest.init();
        BlockSetMatchTest.init();

        FabricLoader.getInstance().getModContainer(ImmersiveWeathering1.MOD_ID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("immersive_weathering:better_brick_items"), modContainer, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("immersive_weathering:better_brick_blocks"), modContainer, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("immersive_weathering:visual_waxed_iron_items"), modContainer, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("immersive_weathering:biome_tinted_mossy_blocks"), modContainer, ResourcePackActivationType.NORMAL);
        });
    }
}
