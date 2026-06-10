package com.witchica.horizontalglasspanes.forge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.forge.platform.runtime.ForgeLoadContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import com.witchica.horizontalglasspanes.client.HorizontalGlassPanesClient;

@Mod(HorizontalGlassPanes.MOD_ID)
public class ForgeHorizontalGlassPanes {

    public ForgeHorizontalGlassPanes(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModBusGroup());
        Balm.initializeMod(HorizontalGlassPanes.MOD_ID, loadContext, HorizontalGlassPanes::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(HorizontalGlassPanes.MOD_ID, loadContext, HorizontalGlassPanesClient::initialize);
        }
    }

}
