package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.common.*;
import com.ordana.immersive_weathering.common.blocks.LeafPilesRegistry;
import com.ordana.immersive_weathering.block_growth.rute_test.BlockSetMatchTest;
import com.ordana.immersive_weathering.block_growth.rute_test.FluidMatchTest;
import com.ordana.immersive_weathering.block_growth.rute_test.LogMatchTest;
import com.ordana.immersive_weathering.forge.ModRegistry;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathResourcePack;

import java.io.IOException;

/**
 * Author: Ordana, Keybounce, MehVahdJukaar
 */
@Mod(ImmersiveWeathering.MOD_ID)
public class ImmersiveWeatheringForge {
    public static final String MOD_ID = ImmersiveWeathering.MOD_ID;

    public ImmersiveWeatheringForge() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModRegistry.BLOCKS.register(bus);
        ModRegistry.BLOCK_ENTITIES.register(bus);
        ModRegistry.ENTITIES.register(bus);
        ModRegistry.ITEMS.register(bus);
        ModRegistry.PARTICLES.register(bus);
        ModRegistry.FEATURES.register(bus);

        /**
         * Update stuff:
         * Configs
         * sand later
         * ash layer
         * leaf layer
         */

        //TODO: fix layers texture generation
        //TODO: fix grass growth replacing double plants and add tag


        LeafPilesRegistry.registerBus(bus);


        bus.addListener(ImmersiveWeatheringForge::init);
        bus.addListener(ImmersiveWeatheringForge::addPackFinders);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ServerConfigs.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigs.SPEC);

    }

    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            ModCompostable.registerCompostable();
            ModFeatures.init();

            FluidMatchTest.init();
            LogMatchTest.init();
            BlockSetMatchTest.init();


        });
    }
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            registerBuiltinResourcePack(event, new TextComponent("Better Brick Items"), "better_brick_items");
            registerBuiltinResourcePack(event, new TextComponent("Better Brick blocks"), "better_brick_blocks");
            registerBuiltinResourcePack(event, new TextComponent("Visual Waxed Iron Items"), "visual_waxed_iron_items");
        }
    }

    private static void registerBuiltinResourcePack(AddPackFindersEvent event, MutableComponent name, String folder) {
        event.addRepositorySource((consumer, constructor) -> {
            String path = ImmersiveWeatheringForge.res(folder).toString();
            IModFile file = ModList.get().getModFileById(ImmersiveWeatheringForge.MOD_ID).getFile();
            try (PathResourcePack pack = new PathResourcePack(
                    path,
                    file.findResource("resourcepacks/" + folder));) {

                consumer.accept(constructor.create(
                        ImmersiveWeatheringForge.res(folder).toString(),
                        name,
                        false,
                        () -> pack,
                        pack.getMetadataSection(PackMetadataSection.SERIALIZER),
                        Pack.Position.TOP,
                        PackSource.BUILT_IN,
                        false));

            } catch (IOException e) {
                if (!DatagenModLoader.isRunningDataGen())
                    e.printStackTrace();
            }
        });
    }

}
