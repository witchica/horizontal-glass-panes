package com.tattyseal.hgp.handler;

import com.tattyseal.hgp.HorizontalGlassPanes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelRegistrationHandler
{
    private static final String[] models = new String[] {
            "hgp:hgp_colored_pane_white",
            "hgp:hgp_colored_pane_orange",
            "hgp:hgp_colored_pane_magenta",
            "hgp:hgp_colored_pane_lightblue",
            "hgp:hgp_colored_pane_yellow",
            "hgp:hgp_colored_pane_lime",
            "hgp:hgp_colored_pane_pink",
            "hgp:hgp_colored_pane_gray",
            "hgp:hgp_colored_pane_lightgray",
            "hgp:hgp_colored_pane_cyan",
            "hgp:hgp_colored_pane_purple",
            "hgp:hgp_colored_pane_blue",
            "hgp:hgp_colored_pane_brown",
            "hgp:hgp_colored_pane_green",
            "hgp:hgp_colored_pane_red",
            "hgp:hgp_colored_pane_black"
    };

    @SubscribeEvent
    public static void registerModels(final ModelRegistryEvent event)
    {
        /**
         * Define the resource locations
         */

        ModelResourceLocation glassLocation = new ModelResourceLocation("hgp:hgp_pane", "inventory");
        ModelResourceLocation ironLocation = new ModelResourceLocation("hgp:hgp_pane_iron", "inventory");

        ModelResourceLocation[] colors = new ModelResourceLocation[models.length];

        for(int i = 0; i < colors.length; i++)
        {
            colors[i] = new ModelResourceLocation(models[i], "inventory");
        }

        /**
         * Register the glass and iron variations
         */
        ModelBakery.registerItemVariants(HorizontalGlassPanes.ibBlockPane, glassLocation, ironLocation);

        ModelLoader.setCustomModelResourceLocation(HorizontalGlassPanes.ibBlockPane, 0, glassLocation);
        ModelLoader.setCustomModelResourceLocation(HorizontalGlassPanes.ibBlockPane, 1, ironLocation);

        /**
         * Register the colored variations
         */

        ModelBakery.registerItemVariants(HorizontalGlassPanes.ibBlockColoredPane, colors);

        for(int i = 0; i < 16; i++)
        {
            ModelLoader.setCustomModelResourceLocation(HorizontalGlassPanes.ibBlockColoredPane, i, colors[i]);
        }

        System.out.println("DONE A THING!!");
    }
}
