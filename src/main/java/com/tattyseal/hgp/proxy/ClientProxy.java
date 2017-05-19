package com.tattyseal.hgp.proxy;

import com.tattyseal.hgp.HorizontalGlassPanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), 0, new ModelResourceLocation("hgp:hgp_pane", "inventory"));
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), 1, new ModelResourceLocation("hgp:hgp_pane_iron", "inventory"));

	    
	    String[] cs = new String[] {
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
	    		"hgp:hgp_colored_pane_black"};

		ModelResourceLocation[] colors = new ModelResourceLocation[cs.length];

		for(int i = 0; i < colors.length; i++)
		{
			System.out.println(cs[i]);
			colors[i] = new ModelResourceLocation(cs[i], "inventory");
		}
	    
	    ModelBakery.registerItemVariants(Item.getItemFromBlock(HorizontalGlassPanes.blockColoredPane), colors[0], colors[1], colors[2], colors[3], colors[4], colors[5], colors[6], colors[7], colors[8], colors[9], colors[10], colors[11], colors[12], colors[13], colors[14], colors[15]);
	    ModelBakery.registerItemVariants(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), new ModelResourceLocation("hgp:hgp_pane", "inventory"), new ModelResourceLocation("hgp:hgp_pane_iron", "inventory"));
	    
	    for(int i = 0; i < 16; i++)
	    {
	    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockColoredPane), i, colors[i]);
	    }
	}

	@Override
	public boolean getMeme() {
		return Minecraft.getMinecraft().player.isSneaking();
	}

	@Override
	public String getHorizontal()
	{
		return I18n.format("misc.horizontal");
	}
}
