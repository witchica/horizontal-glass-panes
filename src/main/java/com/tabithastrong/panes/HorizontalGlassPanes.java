package com.tabithastrong.panes;

import com.google.common.collect.Maps;
import com.tabithastrong.panes.block.HorizontalPaneBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HorizontalGlassPanes implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("horizontal-glass-panes");
	public static final Block PANE_GLASS = new HorizontalPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));
	public static final Block PANE_IRON = new HorizontalPaneBlock(FabricBlockSettings.copy(Blocks.IRON_BARS));

	public static final Block[] COLORED_PANES = new Block[16];

	public static final Map<Integer, Block> HORIZONTAL_ID_TO_BLOCK = new HashMap<Integer, Block>();

	static {
		HORIZONTAL_ID_TO_BLOCK.put(0, Blocks.WHITE_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(1, Blocks.ORANGE_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(2, Blocks.MAGENTA_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(3, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(4, Blocks.YELLOW_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(5, Blocks.LIME_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(6, Blocks.PINK_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(7, Blocks.GRAY_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(8, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(9, Blocks.CYAN_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(10, Blocks.PURPLE_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(11, Blocks.BLUE_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(12, Blocks.BROWN_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(13, Blocks.GREEN_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(14, Blocks.RED_STAINED_GLASS_PANE);
		HORIZONTAL_ID_TO_BLOCK.put(15, Blocks.BLACK_STAINED_GLASS_PANE);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		for(int i = 0; i < DyeColor.values().length; i++) {
			COLORED_PANES[i] = new HorizontalPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE));
			Registry.register(Registries.BLOCK, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), COLORED_PANES[i]);
			Registry.register(Registries.ITEM, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), new BlockItem(COLORED_PANES[i], new Item.Settings()));
		}

		Registry.register(Registries.BLOCK, new Identifier("horizontal-glass-panes", "pane_glass"), PANE_GLASS);
		Registry.register(Registries.ITEM, new Identifier("horizontal-glass-panes", "pane_glass"), new BlockItem(PANE_GLASS, new Item.Settings()));

		Registry.register(Registries.BLOCK, new Identifier("horizontal-glass-panes", "pane_iron"), PANE_IRON);
		Registry.register(Registries.ITEM, new Identifier("horizontal-glass-panes", "pane_iron"), new BlockItem(PANE_IRON, new Item.Settings()));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
			content.add(PANE_IRON);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
			content.addAfter(Blocks.GLASS_PANE, PANE_GLASS);
			for(int i = 0; i < 16; i++) {
				content.addAfter(HORIZONTAL_ID_TO_BLOCK.get(i), COLORED_PANES[i]);
			}
		});
	}
}
