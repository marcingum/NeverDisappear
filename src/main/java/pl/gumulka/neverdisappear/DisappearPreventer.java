/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gumulka.neverdisappear;

//import buildcraft.api.transport.IInjectable;
//import buildcraft.lib.inventory.ItemTransactorHelper;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 *
 * @author Marcin
 */
public class DisappearPreventer {

  private static final Logger LOGGER = LogManager.getLogger();

  public static BlockPos targetPos = new BlockPos(0.5, 70, 0.5);
  private static Random random = new Random();
  private double SCATTER = 2;

  void preventDisappear(ItemExpireEvent e) {
    BlockPos oldPosition = e.getEntity().getPosition();
    try {
      if (!sendToPipe(e)) {
        moveItem(e);
      }
    } catch (Exception ex) {
      LOGGER.warn("Error on sendToPipe", ex);
      moveItem(e);
    }
    LOGGER.debug("NeverDisappearMod Prevent disappear {} - from {} to {}", e.getEntity().getName().getFormattedText(), oldPosition, e.getEntity().getPosition());
  }

  private boolean sendToPipe(ItemExpireEvent e) {
//    TileEntity pipeTile = e.getEntity().getEntityWorld().getTileEntity(targetPos);
//    IInjectable injectable = ItemTransactorHelper.getInjectable(pipeTile, EnumFacing.UP);
//    if (injectable.canInjectItems(EnumFacing.UP)) {
//      ItemStack leftItems = injectable.injectItem(e.getEntityItem().getItem(), true, EnumFacing.UP, null, 0);
//      if (leftItems.isEmpty()) {
//        return true;
//      }
//    }
    return false;
  }

  private void moveItem(ItemExpireEvent e) {
    e.setCanceled(true);
    e.setExtraLife(1000 * 5);
    BlockPos position = getScatteredPosition(targetPos);
    e.getEntity().setPosition(position.getX(), position.getY(), position.getZ());
  }

  private BlockPos getScatteredPosition(BlockPos pos) {
    return new BlockPos(pos.getX() + random.nextGaussian() * SCATTER, pos.up(5).getY(), pos.getZ() + random.nextGaussian() * SCATTER);
  }
}
