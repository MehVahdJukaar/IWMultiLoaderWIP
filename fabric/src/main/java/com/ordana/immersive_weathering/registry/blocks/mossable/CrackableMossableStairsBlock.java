package com.ordana.immersive_weathering.registry.blocks.mossable;

import com.ordana.immersive_weathering.registry.blocks.crackable.CrackSpreader;
import net.minecraft.world.level.block.state.BlockState;

public class CrackableMossableStairsBlock extends MossableStairsBlock implements CrackableMossable {

    private final CrackLevel crackLevel;

    public CrackableMossableStairsBlock(MossLevel mossLevel, CrackLevel crackLevel, BlockState baseBlockState, Properties settings) {
        super(mossLevel, baseBlockState, settings);
        this.crackLevel = crackLevel;
    }

    @Override
    public CrackSpreader getCrackSpreader() {
        return CrackSpreader.INSTANCE;
    }

    @Override
    public CrackLevel getCrackLevel() {
        return crackLevel;
    }

}
