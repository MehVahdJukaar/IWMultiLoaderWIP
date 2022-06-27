package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.ordana.immersive_weathering.block_growth.BlockGrowthHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

@Mixin(ServerLevel.class)
public abstract class ServerWorldMixin {

	@Inject(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;hasRandomTicks()Z", shift = Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private void tickBlocks(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci, ChunkPos chunkPos, boolean bl, int i, int j, ProfilerFiller profiler, LevelChunkSection var8[], int var9, int var10, LevelChunkSection chunkSection, int k, int l, BlockPos blockPos3) {
		if(ImmersiveWeathering1.getConfig().blockGrowthConfig.blockGrowth) {
			BlockGrowthHandler.tickBlock(blockPos3, (ServerLevel) (Object) this);
		}
	}
}
