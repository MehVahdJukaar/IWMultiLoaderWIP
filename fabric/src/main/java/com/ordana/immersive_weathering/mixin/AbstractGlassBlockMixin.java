package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.FrostyGrassBlock;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AbstractGlassBlock.class)
public class AbstractGlassBlockMixin extends Block {

    public AbstractGlassBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if(ImmersiveWeathering1.getConfig().fireAndIceConfig.glassFrosting) {
            if (state.is(Blocks.GLASS)) {
                if ((world.isRaining() || world.isNight()) && world.getBiome(pos).is(ModTags.ICY)) {
                    world.setBlockAndUpdate(pos, ModBlocks.FROSTY_GLASS.defaultBlockState().setValue(FrostyGrassBlock.NATURAL, true));
                }
            }
        }
    }
}
