package com.tattyseal.hgp.block

import com.google.common.collect.Lists
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

import scala.collection.JavaConversions

/**
 * Created by Toby on 04/02/2015.
 */
class BlockStainedPane extends BlockPane
{
  setBlockName("hgp.paneStained");

  override def getIcon(meta: Int, side: Int): IIcon =
  {
    return net.minecraft.init.Blocks.stained_glass_pane.getIcon(meta, side);
  }

  override def getSubBlocks(item: Item, tab: CreativeTabs, list: java.util.List[_]): Unit =
  {
    val realList = list.asInstanceOf[java.util.List[ItemStack]];

    for(i <- 0 until 16)
    {
      realList.add(new ItemStack(item, 1, i));
    }
  }
}
