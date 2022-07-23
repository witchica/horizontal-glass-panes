package com.tabithastrong.panes;

import com.tabithastrong.panes.block.HorizontalPaneBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HorizontalGlassPanes implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("horizontal-glass-panes");
	public static final Block PANE_GLASS = new HorizontalPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));
	public static final Block PANE_IRON = new HorizontalPaneBlock(FabricBlockSettings.copy(Blocks.IRON_BARS));

	public static final Block[] COLORED_PANES = new Block[16];

	public static final Block[] VANILLA_COLORED_PANES = new Block[] {
			Blocks.WHITE_STAINED_GLASS_PANE,
			Blocks.ORANGE_STAINED_GLASS_PANE,
			Blocks.MAGENTA_STAINED_GLASS_PANE,
			Blocks.LIGHT_BLUE_STAINED_GLASS_PANE,
			Blocks.YELLOW_STAINED_GLASS_PANE,
			Blocks.LIME_STAINED_GLASS_PANE,
			Blocks.PINK_STAINED_GLASS_PANE,
			Blocks.GRAY_STAINED_GLASS_PANE,
			Blocks.LIGHT_GRAY_STAINED_GLASS_PANE,
			Blocks.CYAN_STAINED_GLASS_PANE,
			Blocks.PURPLE_STAINED_GLASS_PANE,
			Blocks.BLUE_STAINED_GLASS_PANE,
			Blocks.BROWN_STAINED_GLASS_PANE,
			Blocks.GREEN_STAINED_GLASS_PANE,
			Blocks.RED_STAINED_GLASS_PANE,
			Blocks.BLACK_STAINED_GLASS_PANE
	};

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		for(int i = 0; i < DyeColor.values().length; i++) {
			COLORED_PANES[i] = new HorizontalPaneBlock(FabricBlockSettings.copy(VANILLA_COLORED_PANES[i]));
			Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), COLORED_PANES[i]);
			Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), new BlockItem(COLORED_PANES[i], new Item.Settings().group(ItemGroup.DECORATIONS)));
		}

		Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_glass"), PANE_GLASS);
		Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_glass"), new BlockItem(PANE_GLASS, new Item.Settings().group(ItemGroup.DECORATIONS)));

		Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_iron"), PANE_IRON);
		Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_iron"), new BlockItem(PANE_IRON, new Item.Settings().group(ItemGroup.DECORATIONS)));

	}
}
