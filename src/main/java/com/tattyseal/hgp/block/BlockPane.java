package com.tattyseal.hgp.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

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
		super(Material.GLASS);
		setUnlocalizedName("hgp.pane");
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.GLASS);
		setState();
	}

	@Override
	public Material getMaterial(IBlockState state)
	{
		if(state.getPropertyKeys().contains(TYPE))
		{
			EnumType type = (EnumType) state.getValue(TYPE);

			switch(type)
			{
				case IRON:
				{
					return Material.IRON;
				}
				default:
				{
					return Material.GLASS;
				}
			}
		}
		else
		{
			return Material.GLASS;
		}
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
	{
		if(!blockState.getPropertyKeys().contains(TYPE))
		{
			return 1f;
		}

		EnumType type = (EnumType) blockState.getValue(TYPE);

		switch(type)
		{
			case IRON:
			{
				return 5f;
			}
			default:
			{
				return 1f;
			}
		}
	}

	@Override
	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity)
	{
		if(!state.getPropertyKeys().contains(TYPE))
		{
			return SoundType.GLASS;
		}

		EnumType type = (EnumType) state.getValue(TYPE);

		switch(type)
		{
			case IRON:
			{
				return SoundType.STONE;
			}
			default:
			{
				return SoundType.GLASS;
			}
		}
	}

	public void setState()
	{
	    this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.GLASS));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				TYPE
		});
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0.4375f, 0, 1, 0.5625f, 1);
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
	public boolean canSilkHarvest() 
	{
		return true;
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) 
	{
		return state.getPropertyKeys().contains(TYPE) ? (((EnumType) state.getValue(TYPE)) == EnumType.IRON ? 1 : 0) : 0;
	}

	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
	{
		for(int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	public String getUnlocalizedName()
	{
		return Blocks.GLASS_PANE.getUnlocalizedName();
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
