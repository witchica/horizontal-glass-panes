package com.tattyseal.hgp.block

import net.minecraft.block.Block
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemStack, ItemBlock}

/**
 * Created by Toby on 05/02/2015.
 */
class ItemBlockStainedBlock(block: Block) extends ItemBlock(block)
{
  setHasSubtypes(true);
  setUnlocalizedName("hgp.coloredStainedPane");

  override def getUnlocalizedName(stack: ItemStack): String =
  {
    return super.getUnlocalizedName() + "." + stack.getItemDamage();
  }

  override def getMetadata(damage: Int): Int =
  {
    return damage;
  }

  override def getItemStackDisplayName(stack: ItemStack): String = new ItemStack(Blocks.stained_glass_pane, 1, stack.getItemDamage()).getDisplayName();
}
