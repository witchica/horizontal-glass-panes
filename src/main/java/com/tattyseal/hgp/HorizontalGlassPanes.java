package com.tattyseal.hgp;

import com.tattyseal.hgp.block.BlockPane;
import com.tattyseal.hgp.block.BlockStainedPane;
import com.tattyseal.hgp.item.ItemBlockPane;
import com.tattyseal.hgp.item.ItemBlockStainedBlock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = "hgp", name = "Horizontal Glass Panes", version = "Release", dependencies = "after:glass_shards")
public class HorizontalGlassPanes 
{
	public Block blockPane;
	public Block blockColoredPane;

	@EventHandler
	public void preInit(FMLPreInitializationEvent init)
	{
		blockPane = new BlockPane();
		blockColoredPane = new BlockStainedPane();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent init)
	{
		GameRegistry.registerBlock(blockPane, ItemBlockPane.class, "hgpPane");
	    GameRegistry.registerBlock(blockColoredPane, ItemBlockStainedBlock.class, "hgpColoredPane");

	    for(int i = 0; i < 16; i++)
	    {
	      GameRegistry.addShapedRecipe(new ItemStack(blockColoredPane, 3, i), "PPP", 'P', new ItemStack(Blocks.stained_glass_pane, 1, i));
	    }

	    GameRegistry.addShapedRecipe(new ItemStack(blockPane, 3), "PPP", 'P', new ItemStack(Blocks.glass_pane, 1));

	    OreDictionary.registerOre("paneGlassColorless", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockColoredPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));

	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockPane), 0, new ModelResourceLocation("hgp:hgpPane", "inventory"));
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockPane), 1, new ModelResourceLocation("hgp:hgpPane_iron", "inventory"));
	    
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
	    
	    ModelBakery.addVariantName(Item.getItemFromBlock(blockColoredPane), colors[0], colors[1], colors[2], colors[3], colors[4], colors[5], colors[6], colors[7], colors[8], colors[9], colors[10], colors[11], colors[12], colors[13], colors[14], colors[15]);
	    ModelBakery.addVariantName(Item.getItemFromBlock(blockPane), "hgp:hgpPane", "hgp:hgpPane_iron");
	    
	    for(int i = 0; i < 16; i++)
	    {
	    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockColoredPane), i, new ModelResourceLocation(colors[i], "inventory"));
	    }
	}
}
