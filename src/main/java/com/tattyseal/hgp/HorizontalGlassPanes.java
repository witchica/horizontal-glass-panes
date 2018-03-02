package com.tattyseal.hgp;

import com.tattyseal.hgp.block.BlockPane;
import com.tattyseal.hgp.block.BlockStainedPane;
import com.tattyseal.hgp.item.ItemBlockPane;
import com.tattyseal.hgp.item.ItemBlockStainedBlock;
import com.tattyseal.hgp.proxy.IProxy;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = "hgp", name = "Horizontal Glass Panes", version = "Release", dependencies = "after:glass_shards")
public class HorizontalGlassPanes 
{
	public static Block blockPane;
	public static Block blockColoredPane;

	public static ItemBlock ibBlockPane;
	public static ItemBlock ibBlockColoredPane;
	
	@Instance("hgp")
	public static HorizontalGlassPanes instance;
	
	@SidedProxy(modId = "hgp", clientSide = "com.tattyseal.hgp.proxy.ClientProxy", serverSide = "com.tattyseal.hgp.proxy.CommonProxy")
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent init)
	{
		blockPane = new BlockPane();
		blockPane.setRegistryName("hgppane");

		ibBlockPane = new ItemBlockPane(blockPane);
		ibBlockPane.setRegistryName("hgppane");

		blockColoredPane = new BlockStainedPane();
		blockColoredPane.setRegistryName("hgpcoloredpane");

		ibBlockColoredPane = new ItemBlockStainedBlock(blockColoredPane);
		ibBlockColoredPane.setRegistryName("hgpcoloredpane");

		ForgeRegistries.BLOCKS.register(blockPane);
		ForgeRegistries.BLOCKS.register(blockColoredPane);

		ForgeRegistries.ITEMS.register(ibBlockPane);
		ForgeRegistries.ITEMS.register(ibBlockColoredPane);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent init)
	{
		proxy.init();

	    for(int i = 0; i < 16; i++)
	    {
	        addShapedRecipe(new ItemStack(blockColoredPane, 3, i), "PPP", 'P', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, i));
	    }

	    addShapedRecipe(new ItemStack(blockPane, 3, 0), "PPP", 'P', new ItemStack(Blocks.GLASS_PANE, 1));
	    addShapedRecipe(new ItemStack(blockPane, 3, 1), "PPP", 'P', new ItemStack(Blocks.IRON_BARS, 1));

	    OreDictionary.registerOre("paneGlassColorless", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockColoredPane, 1, OreDictionary.WILDCARD_VALUE));
	    OreDictionary.registerOre("paneGlass", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void addShapedRecipe(ItemStack output, Object... params)
	{
		ResourceLocation recipeResourceLocation = new ResourceLocation(output.getItem().getRegistryName().getResourceDomain(), output.getItem().getRegistryName().getResourcePath() + output.getItemDamage());
		GameRegistry.addShapedRecipe(recipeResourceLocation, null, output, params);
	}
}
