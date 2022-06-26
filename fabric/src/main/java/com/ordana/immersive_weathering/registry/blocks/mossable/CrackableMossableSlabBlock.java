package com.ordana.immersive_weathering.registry.blocks.mossable;

import com.ordana.immersive_weathering.registry.blocks.crackable.CrackSpreader;

public class CrackableMossableSlabBlock extends MossableSlabBlock implements CrackableMossable {

    private final CrackLevel crackLevel;

    public CrackableMossableSlabBlock(MossLevel mossLevel, CrackLevel crackLevel, Properties settings) {
        super(mossLevel, settings);
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
