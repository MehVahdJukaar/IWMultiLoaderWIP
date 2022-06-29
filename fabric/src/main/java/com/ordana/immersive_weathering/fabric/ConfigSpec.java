package com.ordana.immersive_weathering.fabric;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

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


}
