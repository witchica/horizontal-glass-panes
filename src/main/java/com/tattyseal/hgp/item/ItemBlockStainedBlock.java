package com.tattyseal.hgp.item;

import com.tattyseal.hgp.HorizontalGlassPanes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockStainedBlock extends ItemBlock
{
	public ItemBlockStainedBlock(Block b)
	{
		super(b);
		setHasSubtypes(true);
		setUnlocalizedName("hgp.coloredStainedPane");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		return super.getUnlocalizedName() + "." + stack.getItemDamage();
	}
	
	@Override
	public int getMetadata(int damage) 
	{
		return damage;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		return HorizontalGlassPanes.proxy.getHorizontal() + " " + new ItemStack(Blocks.STAINED_GLASS_PANE, 1, stack.getItemDamage()).getDisplayName();
	}
}
