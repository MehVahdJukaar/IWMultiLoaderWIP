package com.ordana.immersive_weathering.config;

import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class FabricConfigBuilder extends ConfigBuilderWrapper {

    private final ConfigBuilder builder;
    private final ConfigEntryBuilder entryBuilder;

    private ConfigCategory currentCategory = null;

    public FabricConfigBuilder(String name){
        super(name);
        this.builder = ConfigBuilder.create()
                .setParentScreen(null)
                .setTitle(new TranslatableComponent(name));
        this.entryBuilder = builder.entryBuilder();
    }

    @Override
    public FabricConfigBuilder push(String translation) {
        this.currentCategory = builder.getOrCreateCategory(new TranslatableComponent( translation));
        return this;
    }

    @Override
    public FabricConfigBuilder pop() {
        this.currentCategory = null;
        return this;
    }

    @Override
    public Supplier<Boolean> define(String name, String tooltip, boolean defaultValue) {
        assert currentCategory != null;
        Wrapper<Boolean> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableComponent(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableComponent(tooltip)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }


    public static class Wrapper<T> implements Supplier<T> {

        private T value;

        public void set(T newValue){
            this.value = newValue;
        }

        @Override
        public T get() {
            return value;
        }
    }
}
