package com.witchica.horizontalglasspanes.neoforge.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import com.witchica.horizontalglasspanes.client.HorizontalGlassPanesClient;

@Mod(value = HorizontalGlassPanes.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeHorizontalGlassPanesClient {

    public NeoForgeHorizontalGlassPanesClient(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        BalmClient.initializeMod(HorizontalGlassPanes.MOD_ID, context, HorizontalGlassPanesClient::initialize);
    }
}
