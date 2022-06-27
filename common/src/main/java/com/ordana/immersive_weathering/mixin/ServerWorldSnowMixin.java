package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.blocks.WeatheringHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = {"net.minecraft.server.world.ServerWorld"})
public abstract class ServerWorldSnowMixin {

	@ModifyArg(method = {"tickChunk"},
			require = 0,
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/block/Block;precipitationTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/biome/Biome$Precipitation;)V"))
	private BlockState spawnIcicles(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation) {
		if(ImmersiveWeathering1.getConfig().fireAndIceConfig.iciclePlacement) {
			WeatheringHelper.tryPlacingIcicle(state, level, pos, precipitation);
			return state;
		}
		return state;
	}
}
	/*
	@ModifyArg(method = "tick",
			require = 0,
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/server/world/ServerWorld;tickWeather()V"))
	private static BlockState spawnIciclesSRM(BlockState state, World level, BlockPos pos, Biome.Precipitation precipitation) {
		WeatheringHelper.tryPlacingIcicle(state, level, pos, precipitation);
		return state;
	}*/
