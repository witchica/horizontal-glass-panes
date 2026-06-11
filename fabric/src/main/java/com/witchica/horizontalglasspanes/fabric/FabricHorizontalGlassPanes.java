package com.witchica.horizontalglasspanes.fabric;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.impl.content.registry.OxidizableBlocksRegistryImpl;
import net.minecraft.world.level.block.WeatheringCopper;

public class FabricHorizontalGlassPanes implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(HorizontalGlassPanes.MOD_ID, FabricLoadContext.INSTANCE, HorizontalGlassPanes::initialize);

        OxidizableBlocksRegistry.registerNextStage(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.UNAFFECTED).asBlock(), ModBlocks.copperBars.get(WeatheringCopper.WeatherState.EXPOSED).asBlock());
        OxidizableBlocksRegistry.registerNextStage(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.EXPOSED).asBlock(), ModBlocks.copperBars.get(WeatheringCopper.WeatherState.WEATHERED).asBlock());
        OxidizableBlocksRegistry.registerNextStage(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.WEATHERED).asBlock(), ModBlocks.copperBars.get(WeatheringCopper.WeatherState.OXIDIZED).asBlock());

        for(WeatheringCopper.WeatherState state : WeatheringCopper.WeatherState.values()) {
            OxidizableBlocksRegistry.registerWaxable(ModBlocks.copperBars.get(state).asBlock(), ModBlocks.waxedCopperBars.get(state).asBlock());
        }
    }
}
