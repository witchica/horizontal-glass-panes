package com.tattyseal.hgp.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
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
public class BlockStainedPane extends BlockPane
{
	public static final PropertyEnum COLOR = PropertyEnum.create("color", BlockStainedPane.EnumType.class);
	
	public enum EnumType implements IStringSerializable 
	{
    	WHITE(0, "white"),
    	ORANGE(1, "orange"),
    	MAGENTA(2, "magenta"),
    	LIGHT_BLUE(3, "lightblue"),
    	YELLOW(4, "yellow"),
    	LIME(5, "lime"),
    	PINK(6, "pink"),
    	GRAY(7, "gray"),
    	LIGHT_GRAY(8, "lightgray"),
    	CYAN(9, "cyan"),
    	PURPLE(10, "purple"),
    	BLUE(11, "blue"),
    	BROWN(12, "brown"),
    	GREEN(13, "green"),
    	RED(14, "red"),
    	BLACK(15, "black");

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
	
	public BlockStainedPane()
	{
		super();
		setUnlocalizedName("hgp.paneStained");
		setBlockBounds(0, 0.4375f, 0, 1, 0.5625f, 1);
		setHardness(1f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(Block.soundTypeGlass);
	}
	
	@Override
	public void setState() 
	{
	    this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumType.WHITE));
	}
	
	@Override
	protected BlockState createBlockState() 
	{
	    return new BlockState(this, new IProperty[] {
	    	COLOR 
	    });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
	    return getDefaultState().withProperty(COLOR, EnumType.values()[meta < 16 ? meta : 0]);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
	    EnumType type = (EnumType) state.getValue(COLOR);
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
	public String getUnlocalizedName()
	{
		return Blocks.glass_pane.getUnlocalizedName();
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for(int i = 0; i < 16; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
}
