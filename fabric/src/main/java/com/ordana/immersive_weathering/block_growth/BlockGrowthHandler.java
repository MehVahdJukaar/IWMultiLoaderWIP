package com.ordana.immersive_weathering.block_growth;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.block_growth.hardcoded.HardcodedGrowths;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.io.FileWriter;
import java.util.*;

import static com.ordana.immersive_weathering.block_growth.BlockGrowthConfiguration.CODEC;

public class BlockGrowthHandler extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(); //json object that will write stuff

    private static ImmutableSet<Block> TICKING_BLOCKS = ImmutableSet.of();
    private static final Map<Block, Set<IBlockGrowth>> GROWTH_FOR_BLOCK = new HashMap<>();
    private static final Map<ResourceLocation, JsonElement> PENDING_JSONS = new HashMap<>();

    public BlockGrowthHandler() {
        super(GSON, "block_growths");
    }

    public static Optional<Set<IBlockGrowth>> getBlockGrowth(Block block) {
        return Optional.ofNullable(GROWTH_FOR_BLOCK.get(block));
    }

    public static void tickBlock(BlockPos pos, ServerLevel world) {
        BlockState state = world.getBlockState(pos);
        if (!TICKING_BLOCKS.contains(state.getBlock())) return;
        var growth = getBlockGrowth(state.getBlock());
        if (growth.isPresent()) {
            //TODO: move this line to datapack self predicate
            if (state.getBlock() instanceof IConditionalGrowingBlock cb && !cb.canGrow(state)) return;
            Holder<Biome> biome = world.getBiome(pos);

            for (var config : growth.get()) {
                config.tryGrowing(pos, state, world, biome);
            }
        }
    }

    /*
    public static void tickBlock(BlockState state, ServerWorld world, BlockPos pos) {
        if (!TICKING_BLOCKS.contains(state.getBlock())) return;
        var growth = getBlockGrowth(state.getBlock());
        if (growth.isPresent()) {
            //TODO: move this line to datapack self predicate
            if (state.getBlock() instanceof IConditionalGrowingBlock cb && !cb.canGrow(state)) return;
            RegistryEntry<Biome> biome = world.getBiome(pos);

            for (var config : growth.get()) {
                config.tryGrowing(pos, state, world, biome);
            }
        }
    }*/

    public void writeToFile(final BlockGrowthConfiguration obj, FileWriter writer) {
        var r = CODEC.encodeStart(JsonOps.INSTANCE, obj);
        r.result().ifPresent(a -> GSON.toJson(sortJson(a.getAsJsonObject()), writer));
    }

    private JsonObject sortJson(JsonObject jsonObject) {
        try {
            Map<String, JsonElement> joToMap = new TreeMap<>();
            jsonObject.entrySet().forEach(e -> {
                var j = e.getValue();
                if (j instanceof JsonObject jo) j = sortJson(jo);
                joToMap.put(e.getKey(), j);
            });
            JsonObject sortedJSON = new JsonObject();
            joToMap.forEach(sortedJSON::add);
            return sortedJSON;
        } catch (Exception ignored) {
        }
        return jsonObject;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager manager, ProfilerFiller profile) {
        PENDING_JSONS.clear();
        for (var e : jsons.entrySet()) {
            PENDING_JSONS.put(e.getKey(),e.getValue().deepCopy());
        }
    }


    public static void rebuild(RegistryAccess registryAccess) {
        HardcodedGrowths.getHardcoded().forEach(h->{
            h.getOwners().forEach(b -> GROWTH_FOR_BLOCK.computeIfAbsent(b, k -> new HashSet<>()).add(h));
        });
        for(var e : PENDING_JSONS.entrySet()){
            var result = CODEC.parse(RegistryOps.create(JsonOps.INSTANCE, registryAccess),
                    e.getValue());
            var o = result.resultOrPartial(error -> ImmersiveWeathering.LOGGER.error("Failed to read block growth JSON object for {} : {}", e.getKey(), error));
            if(o.isPresent()) {
                BlockGrowthConfiguration config = o.get();
                config.getOwners().forEach(b -> GROWTH_FOR_BLOCK.computeIfAbsent(b, k -> new HashSet<>()).add(config));
            }
        }
        ImmutableSet.Builder<Block> b = ImmutableSet.builder();
        TICKING_BLOCKS = b.addAll(GROWTH_FOR_BLOCK.keySet()).build();
        PENDING_JSONS.clear();
    }



    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(ImmersiveWeathering.MOD_ID,"block_growths");
    }
}