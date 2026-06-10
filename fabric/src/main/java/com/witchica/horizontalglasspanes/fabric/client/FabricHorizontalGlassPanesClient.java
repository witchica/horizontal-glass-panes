package com.witchica.horizontalglasspanes.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import com.witchica.horizontalglasspanes.client.HorizontalGlassPanesClient;

public class FabricHorizontalGlassPanesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(HorizontalGlassPanes.MOD_ID, FabricLoadContext.INSTANCE, HorizontalGlassPanesClient::initialize);
    }
}
