package com.witchica.horizontalglasspanes;

import net.blay09.mods.balm.Balm;
import net.minecraft.resources.Identifier;
import net.blay09.mods.balm.core.BalmRegistrars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.item.ModItems;

public class HorizontalGlassPanes {

    public static final Logger logger = LoggerFactory.getLogger(HorizontalGlassPanes.class);

    public static final String MOD_ID = "horizontalglasspanes";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static HorizontalGlassPanesConfig config() {
        return Balm.config().getActiveConfig(HorizontalGlassPanesConfig.class);
    }

    public static void initialize(BalmRegistrars registrars) {
        Balm.config().registerConfig(HorizontalGlassPanesConfig.class);

        registrars.blocks(ModBlocks::initialize);
        registrars.items(ModItems::initialize);
        registrars.creativeModeTabs(ModItems::initialize);
    }

}
