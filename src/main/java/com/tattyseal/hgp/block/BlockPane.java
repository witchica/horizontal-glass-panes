package com.tattyseal.hgp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "ljfa.glassshards.api.IShatterableGlass", modid = "glass_shards")
public class BlockPane extends Block
{
	public BlockPane()
	{
		super(Material.glass);
		setUnlocalizedName("hgp.pane");
		setBlockBounds(0, 0.4375f, 0, 1, 0.5625f, 1);
		setHardness(1f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(Block.soundTypeGlass);
	}
	
	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	@Override
	public boolean canSilkHarvest() 
	{
		return true;
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) 
	{
		return 0;
	}
	
	public String getUnlocalizedName()
	{
		return Blocks.glass_pane.getUnlocalizedName();
	}
}
