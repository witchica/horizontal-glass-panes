package com.tattyseal.hgp.item;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		return new ItemStack(Blocks.stained_glass_pane, 1, stack.getItemDamage()).getDisplayName();
	}
}
