package me.tobystrong.hgp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.DyeColor;

public class HorizontalGlassPanesClient implements ClientModInitializer {
    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {

        for(int i = 0; i < DyeColor.values().length; i++) {
            BlockRenderLayerMap.INSTANCE.putBlock(HorizontalGlassPanes.COLORED_PANES[i], RenderLayer.getTranslucent());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(HorizontalGlassPanes.PANE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(HorizontalGlassPanes.PANE_IRON, RenderLayer.getCutout());
    }
}
