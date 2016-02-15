package com.tattyseal.hgp;

import com.tattyseal.hgp.block.BlockPane;
import com.tattyseal.hgp.block.BlockStainedPane;
import com.tattyseal.hgp.item.ItemBlockStainedBlock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
		GameRegistry.registerBlock(blockPane, "hgpPane");
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
	}
}
