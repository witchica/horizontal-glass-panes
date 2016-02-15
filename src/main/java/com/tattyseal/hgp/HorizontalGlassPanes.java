package com.tattyseal.hgp;

import com.tattyseal.hgp.block.BlockPane;
import com.tattyseal.hgp.block.BlockStainedPane;
import com.tattyseal.hgp.item.ItemBlockPane;
import com.tattyseal.hgp.item.ItemBlockStainedBlock;
import com.tattyseal.hgp.proxy.IProxy;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = "hgp", name = "Horizontal Glass Panes", version = "Release", dependencies = "after:glass_shards")
public class HorizontalGlassPanes 
{
	public static Block blockPane;
	public static Block blockColoredPane;
	
	@Instance("hgp")
	public static HorizontalGlassPanes instance;
	
	@SidedProxy(modId = "hgp", clientSide = "com.tattyseal.hgp.proxy.ClientProxy", serverSide = "com.tattyseal.hgp.proxy.CommonProxy")
	public static IProxy proxy;

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

	    GameRegistry.addShapedRecipe(new ItemStack(blockPane, 3, 0), "PPP", 'P', new ItemStack(Blocks.glass_pane, 1));
	    GameRegistry.addShapedRecipe(new ItemStack(blockPane, 3, 1), "PPP", 'P', new ItemStack(Blocks.iron_bars, 1));

	    OreDictionary.registerOre("paneGlassColorless", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockColoredPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
	    
	    proxy.init();
	}
}
