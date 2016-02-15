package com.tattyseal.hgp.proxy;

import com.tattyseal.hgp.HorizontalGlassPanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), 0, new ModelResourceLocation("hgp:hgpPane", "inventory"));
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), 1, new ModelResourceLocation("hgp:hgpPane_iron", "inventory"));

	    
	    String[] colors = new String[] {
	    		"hgp:hgpColoredPane_white", 
	    		"hgp:hgpColoredPane_orange", 
	    		"hgp:hgpColoredPane_magenta", 
	    		"hgp:hgpColoredPane_lightblue", 
	    		"hgp:hgpColoredPane_yellow", 
	    		"hgp:hgpColoredPane_lime", 
	    		"hgp:hgpColoredPane_pink", 
	    		"hgp:hgpColoredPane_gray", 
	    		"hgp:hgpColoredPane_lightgray", 
	    		"hgp:hgpColoredPane_cyan", 
	    		"hgp:hgpColoredPane_purple", 
	    		"hgp:hgpColoredPane_blue", 
	    		"hgp:hgpColoredPane_brown", 
	    		"hgp:hgpColoredPane_green", 
	    		"hgp:hgpColoredPane_red", 
	    		"hgp:hgpColoredPane_black"};
	    
	    ModelBakery.addVariantName(Item.getItemFromBlock(HorizontalGlassPanes.blockColoredPane), colors[0], colors[1], colors[2], colors[3], colors[4], colors[5], colors[6], colors[7], colors[8], colors[9], colors[10], colors[11], colors[12], colors[13], colors[14], colors[15]);
	    ModelBakery.addVariantName(Item.getItemFromBlock(HorizontalGlassPanes.blockPane), "hgp:hgpPane", "hgp:hgpPane_iron");
	    
	    for(int i = 0; i < 16; i++)
	    {
	    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(HorizontalGlassPanes.blockColoredPane), i, new ModelResourceLocation(colors[i], "inventory"));
	    }
	}
}
