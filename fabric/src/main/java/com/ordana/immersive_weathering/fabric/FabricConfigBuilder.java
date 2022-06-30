package com.ordana.immersive_weathering.fabric;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FabricConfigBuilder extends ConfigBuilderWrapper {

    public static Supplier<Screen> screenSupplier;

    //stores values instances
    private final ImmutableMap.Builder<String, List<BiConsumer<ConfigBuilder, ConfigCategory>>> categoryBuilder = new ImmutableMap.Builder<>();

    private Pair<String, List<BiConsumer<ConfigBuilder, ConfigCategory>>> currentCategory = null;


    public FabricConfigBuilder(String name, ConfigBuilderWrapper.ConfigType type) {
        super(name, type);
    }

    @Override
    public void buildAndRegister() {
        assert currentCategory == null;
        ConfigSpec.COMMON_INSTANCE = new ConfigSpec(this.getName(), categoryBuilder.build());
    }

    @Override
    public FabricConfigBuilder push(String translation) {
        assert currentCategory == null;
        currentCategory = Pair.of(translation, new ArrayList<>());
        return this;
    }

    @Override
    public FabricConfigBuilder pop() {
        assert currentCategory != null;
        categoryBuilder.put(currentCategory.getFirst(), currentCategory.getSecond());
        this.currentCategory = null;
        return this;
    }

    @Override
    public Supplier<Boolean> define(String name, boolean defaultValue) {
        assert currentCategory != null;
        Wrapper<Boolean> value = new Wrapper<>();

        var list = currentCategory.getSecond();
        list.add((b, c) -> c.addEntry(b.entryBuilder()
                .startBooleanToggle(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build())); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<Double> define(String name, double defaultValue, double min, double max) {
        assert currentCategory != null;
        Wrapper<Double> value = new Wrapper<>();
        var list = currentCategory.getSecond();
        list.add((b, c) -> c.addEntry(b.entryBuilder()
                .startDoubleField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build())); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<Integer> define(String name, int defaultValue, int min, int max) {
        assert currentCategory != null;
        Wrapper<Integer> value = new Wrapper<>();

        var list = currentCategory.getSecond();
        list.add((b, c) -> c.addEntry(b.entryBuilder()
                .startIntField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build())); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public Supplier<String> define(String name, String defaultValue) {
        assert currentCategory != null;
        Wrapper<String> value = new Wrapper<>();

        var list = currentCategory.getSecond();
        list.add((b, c) -> c.addEntry(b.entryBuilder()
                .startStrField(description(name), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build())); // Builds the option entry for cloth config

        return value;
    }

    @Override
    public <V extends Enum<V>> Supplier<V> define(String name, V defaultValue) {
        assert currentCategory != null;
        Wrapper<V> value = new Wrapper<>();

        var list = currentCategory.getSecond();
        list.add((b, c) -> c.addEntry(b.entryBuilder()
                .startEnumSelector(description(name), defaultValue.getDeclaringClass(), defaultValue)
                .setDefaultValue(defaultValue) // Recommended: Used when user click "Reset"
                .setTooltip(tooltip(name)) // Optional: Shown when the user hover over this option
                .setSaveConsumer(value::set) // Recommended: Called when user save the config
                .build())); // Builds the option entry for cloth config

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
