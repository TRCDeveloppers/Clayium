package com.github.trcdeveloppers.clayium.client.gui;

import com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard.ContainerClayCraftingBoard;
import com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard.TileClayCraftingBoard;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiClayCraftingBoard extends GuiContainer {

    public GuiClayCraftingBoard(IInventory playerInv, TileClayCraftingBoard te) {
        super(new ContainerClayCraftingBoard(playerInv, te));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
