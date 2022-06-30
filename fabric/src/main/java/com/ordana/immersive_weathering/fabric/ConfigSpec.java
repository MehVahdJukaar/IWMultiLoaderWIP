package com.ordana.immersive_weathering.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ordana.immersive_weathering.ImmersiveWeathering;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ConfigSpec {

    public static ConfigSpec CLIENT_INSTANCE;
    public static ConfigSpec COMMON_INSTANCE;

    private final Map<String, List<BiConsumer<ConfigBuilder, ConfigCategory>>> screenCategoryBuilders;
    private final String name;

    public ConfigSpec(String name, Map<String, List<BiConsumer<ConfigBuilder, ConfigCategory>>> builder) {
        this.screenCategoryBuilders = builder;
        this.name = name;
    }

    public Screen makeScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableComponent(name));

        builder.setDefaultBackgroundTexture(ImmersiveWeathering.res("textures/block/cracked_bricks"));

        for (var e : screenCategoryBuilders.entrySet()) {
            ConfigCategory category = builder.getOrCreateCategory(new TranslatableComponent(e.getKey()));
            for (var entryAdder : e.getValue()) {
                entryAdder.accept(builder, category);
            }
        }
        return builder.build();
    }

    public static class Configuration {
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        public int ticksUntilFrameDrop = 600; //Defaulted to thirty seconds.
        public int frameDropMaximumFrames = 10; //Must be an interval of 10.

        public boolean useMouseActivity = true;
        public boolean useMovementActivity = true;
        public boolean useHurtActivity = true;
        public boolean useHandSwingActivity = false;
        public static Configuration loadConfig(File file) {
            Configuration config;

            if (file.exists() && file.isFile()) {
                try (
                        FileInputStream fileInputStream = new FileInputStream(file);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
                ) {
                    config = GSON.fromJson(bufferedReader, Configuration.class);
                } catch (IOException e) {
                    throw new RuntimeException("[HTM] Failed to load config", e);
                }
            } else {
                config = new Configuration();
            }

            config.saveConfig(file);

            return config;
        }

        public void saveConfig(File config) {
            try (
                    FileOutputStream stream = new FileOutputStream(config);
                    Writer writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)
            ) {
                GSON.toJson(this, writer);
            } catch (IOException e) {

            }
        }
    }

}
