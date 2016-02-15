package com.tattyseal.hgp.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "ljfa.glassshards.api.IShatterableGlass", modid = "glass_shards")
public class BlockPane extends Block
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockPane.EnumType.class);

	public enum EnumType implements IStringSerializable 
	{
    	GLASS(0, "glass"),
    	IRON(1, "iron");

	    private int ID;
	    private String name;
	    
	    private EnumType(int ID, String name) 
	    {
	        this.ID = ID;
	        this.name = name;
	    }
	    
	    @Override
	    public String getName() 
	    {
	        return name;
	    }

	    public int getID() 
	    {
	        return ID;
	    }
	    
	    @Override
	    public String toString() 
	    {
	        return getName();
	    }
	}
	
	public BlockPane()
	{
		super(Material.glass);
		setUnlocalizedName("hgp.pane");
		setBlockBounds(0, 0.4375f, 0, 1, 0.5625f, 1);
		setHardness(1f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(Block.soundTypeGlass);
		setState();
	}
	
	public void setState()
	{
	    this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.GLASS));
	}

	@Override
	protected BlockState createBlockState() 
	{
	    return new BlockState(this, new IProperty[] {
	    	TYPE 
	    });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
	    return getDefaultState().withProperty(TYPE, EnumType.values()[meta < 2 ? meta : 0]);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
	    EnumType type = (EnumType) state.getValue(TYPE);
	    return type.getID();
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
	    return getMetaFromState(state);
	}
	
	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	@Override
	public boolean isSolidFullCube() 
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

	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for(int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	public String getUnlocalizedName()
	{
		return Blocks.glass_pane.getUnlocalizedName();
	}
	
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
}
