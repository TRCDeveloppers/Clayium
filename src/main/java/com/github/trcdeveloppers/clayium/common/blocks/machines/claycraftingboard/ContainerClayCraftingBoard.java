package com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerClayCraftingBoard extends Container {

    private final TileClayCraftingBoard te;

    public ContainerClayCraftingBoard(IInventory playerInv, TileClayCraftingBoard te) {
        this.te = te;
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
}
