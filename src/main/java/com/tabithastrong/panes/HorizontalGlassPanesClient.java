package com.tabithastrong.panes;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class HorizontalGlassPanesClient {


    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {

        for(int i = 0; i < DyeColor.values().length; i++) {
            ItemBlockRenderTypes.setRenderLayer(HorizontalGlassPanes.COLORED_PANES[i], RenderType.translucent());
        }

        ItemBlockRenderTypes.setRenderLayer(HorizontalGlassPanes.PANE_GLASS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(HorizontalGlassPanes.PANE_IRON, RenderType.cutout());
    }
}
