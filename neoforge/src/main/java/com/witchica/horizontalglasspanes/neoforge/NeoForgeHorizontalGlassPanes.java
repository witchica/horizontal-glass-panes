package com.witchica.horizontalglasspanes.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.minecraft.world.level.block.WeatheringCopper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import net.neoforged.neoforge.common.DataMapHooks;

@Mod(HorizontalGlassPanes.MOD_ID)
public class NeoForgeHorizontalGlassPanes {

    public NeoForgeHorizontalGlassPanes(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(HorizontalGlassPanes.MOD_ID, context, HorizontalGlassPanes::initialize);
    }
}
