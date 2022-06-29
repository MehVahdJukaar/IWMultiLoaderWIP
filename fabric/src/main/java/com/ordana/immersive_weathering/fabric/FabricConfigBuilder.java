package com.ordana.immersive_weathering.fabric;

import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.function.Supplier;

public class FabricConfigBuilder extends ConfigBuilderWrapper {

    private final ConfigBuilder builder;
    private final ConfigEntryBuilder entryBuilder;

    private ConfigCategory currentCategory = null;

    public FabricConfigBuilder(String name) {
        super(name);
        this.builder = ConfigBuilder.create()
                .setParentScreen(null)
                .setTitle(new TranslatableComponent(name));
        this.entryBuilder = builder.entryBuilder();
    }

    @Override
    public FabricConfigBuilder push(String translation) {
        this.currentCategory = builder.getOrCreateCategory(new TranslatableComponent(translation));
        return this;
    }

    @Override
    public FabricConfigBuilder pop() {
        this.currentCategory = null;
        return this;
    }

    @Override
    public Supplier<Boolean> define(String name, boolean defaultValue) {
        assert currentCategory != null;
        Wrapper<Boolean> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startBooleanToggle(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<Double> define(String name, double defaultValue, double min, double max) {
        assert currentCategory != null;
        Wrapper<Double> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startDoubleField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<Integer> define(String name, int defaultValue, int min, int max) {
        assert currentCategory != null;
        Wrapper<Integer> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startIntField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<String> define(String name, String defaultValue) {
        assert currentCategory != null;
        Wrapper<String> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startStrField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public <V extends Enum<V>> Supplier<V> define(String name, String tooltip, V defaultValue) {
        assert currentCategory != null;
        Wrapper<V> value = new Wrapper<>();

        currentCategory.addEntry(entryBuilder.startEnumSelector(description(name), defaultValue.getDeclaringClass(), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return value;
    }


    public static class Wrapper<T> implements Supplier<T> {

        private T value;

        public void set(T newValue) {
            this.value = newValue;
        }

        @Override
        public T get() {
            return value;
        }
    }
}
