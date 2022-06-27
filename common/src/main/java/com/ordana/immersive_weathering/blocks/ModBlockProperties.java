package com.ordana.immersive_weathering.blocks;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

    public static final BooleanProperty SOAKED = BooleanProperty.create("soaked");
    public static final BooleanProperty FERTILE = BooleanProperty.create("fertile");
    public static final BooleanProperty MOLTEN = BooleanProperty.create("molten");
    public static final IntegerProperty CRACKED = IntegerProperty.create("cracked", 0,3);
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 0, 8);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 10);
}
