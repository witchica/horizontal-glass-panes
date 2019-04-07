package com.tattyseal.hgp;

import com.tattyseal.hgp.block.BlockPane;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("hgp")
public class HorizontalGlassPanes 
{
	private static final Logger LOGGER = LogManager.getLogger();

	public HorizontalGlassPanes() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		paneBlocks = new BlockPane[paneTypes.length];

		for(int i = 0; i < paneTypes.length; i++) {
			String paneType = paneTypes[i];

			Block.Properties p = Block.Properties.create(i == 0 ? Material.IRON : Material.GLASS).
					             hardnessAndResistance(i == 0 ? 2f : 1f).
					             sound(i == 0 ? SoundType.METAL : SoundType.GLASS).variableOpacity();

			paneBlocks[i] = new BlockPane(p);
			paneBlocks[i].setRegistryName("hgp:pane_" + paneType);
		}
	}

	public static String[] paneTypes = {
			"iron",
			"glass",
			"white",
			"orange",
			"magenta",
			"light_blue",
			"yellow",
			"lime",
			"pink",
			"gray",
			"light_gray",
			"cyan",
			"purple",
			"blue",
			"brown",
			"green",
			"red",
			"black"
	};

	public static BlockPane[] paneBlocks;
	
//	@Instance("hgp")
//	public static HorizontalGlassPanes instance;
//
//	@SidedProxy(modId = "hgp", clientSide = "com.tattyseal.hgp.proxy.ClientProxy", serverSide = "com.tattyseal.hgp.proxy.CommonProxy")
//	public static IProxy proxy;

	//pre init
	private void setup(final FMLCommonSetupEvent event)
	{
	}

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			for (int i = 0; i < HorizontalGlassPanes.paneBlocks.length; i++) {
				BlockPane block = HorizontalGlassPanes.paneBlocks[i];
				blockRegistryEvent.getRegistry().register(block);

				LOGGER.info(HorizontalGlassPanes.paneBlocks[i] + " registered block");
			}
		}
		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegisterEvent) {
			for (int i = 0; i < HorizontalGlassPanes.paneBlocks.length; i++) {
				BlockPane block = HorizontalGlassPanes.paneBlocks[i];
				ItemBlock ib = new ItemBlock(block, new Item.Properties().group(ItemGroup.DECORATIONS));

				ib.setRegistryName(block.getRegistryName());
				itemRegisterEvent.getRegistry().register(ib);

				LOGGER.info(HorizontalGlassPanes.paneBlocks[i] + " registered item");
			}
		}
	}
	
//	@EventHandler
//	public void init(FMLInitializationEvent init)
//	{
//		proxy.init();
//
//	    for(int i = 0; i < 16; i++)
//	    {
//	        addShapedRecipe(new ItemStack(blockColoredPane, 3, i), "PPP", 'P', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, i));
//	    }
//
//	    addShapedRecipe(new ItemStack(blockPane, 3, 0), "PPP", 'P', new ItemStack(Blocks.GLASS_PANE, 1));
//	    addShapedRecipe(new ItemStack(blockPane, 3, 1), "PPP", 'P', new ItemStack(Blocks.IRON_BARS, 1));
//
//	    OreDictionary.registerOre("paneGlassColorless", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
//	    OreDictionary.registerOre("paneGlass", new ItemStack(blockColoredPane, 1, OreDictionary.WILDCARD_VALUE));
//	    OreDictionary.registerOre("paneGlass", new ItemStack(blockPane, 1, OreDictionary.WILDCARD_VALUE));
//	}

//	public static void addShapedRecipe(ItemStack output, Object... params)
//	{
//		ResourceLocation recipeResourceLocation = new ResourceLocation(output.getItem().getRegistryName().getResourceDomain(), output.getItem().getRegistryName().getResourcePath() + output.getItemDamage());
//		GameRegistry.addShapedRecipe(recipeResourceLocation, null, output, params);
//	}
}
