package me.tobystrong.hgp;

import me.tobystrong.hgp.block.BlockPane;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HorizontalGlassPanes implements ModInitializer {
	public static final Block PANE_GLASS = new BlockPane(FabricBlockSettings.copy(Blocks.GLASS_PANE).build());
	public static final Block PANE_IRON = new BlockPane(FabricBlockSettings.copy(Blocks.IRON_BARS).build());

	public static final Block[] COLORED_PANES = new Block[16];

	@Override
	public void onInitialize() {
		for(int i = 0; i < DyeColor.values().length; i++) {
			COLORED_PANES[i] = new BlockPane(FabricBlockSettings.copy(Blocks.GLASS_PANE).build());
			Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), COLORED_PANES[i]);
			Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), new BlockItem(COLORED_PANES[i], new Item.Settings().group(ItemGroup.DECORATIONS)));
			BlockRenderLayerMap.INSTANCE.putBlock(COLORED_PANES[i], RenderLayer.getTranslucent());
		}

		Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_glass"), PANE_GLASS);
		Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_glass"), new BlockItem(PANE_GLASS, new Item.Settings().group(ItemGroup.DECORATIONS)));
		BlockRenderLayerMap.INSTANCE.putBlock(PANE_GLASS, RenderLayer.getTranslucent());

		Registry.register(Registry.BLOCK, new Identifier("horizontal-glass-panes", "pane_iron"), PANE_IRON);
		Registry.register(Registry.ITEM, new Identifier("horizontal-glass-panes", "pane_iron"), new BlockItem(PANE_IRON, new Item.Settings().group(ItemGroup.DECORATIONS)));
		BlockRenderLayerMap.INSTANCE.putBlock(PANE_IRON, RenderLayer.getCutout());
	}
}
