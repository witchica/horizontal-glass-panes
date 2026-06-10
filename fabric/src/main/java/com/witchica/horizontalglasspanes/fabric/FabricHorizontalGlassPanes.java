package com.witchica.horizontalglasspanes.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;

public class FabricHorizontalGlassPanes implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(HorizontalGlassPanes.MOD_ID, FabricLoadContext.INSTANCE, HorizontalGlassPanes::initialize);
    }
}
