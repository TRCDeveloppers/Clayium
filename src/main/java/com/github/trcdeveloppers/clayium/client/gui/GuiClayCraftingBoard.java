package com.github.trcdeveloppers.clayium.client.gui;

import com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard.ContainerClayCraftingBoard;
import com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard.TileClayCraftingBoard;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.github.trcdeveloppers.clayium.Clayium.MOD_ID;

@SideOnly(Side.CLIENT)
public class GuiClayCraftingBoard extends GuiContainer {
    private final ResourceLocation SLOT = new ResourceLocation(MOD_ID, "textures/gui/slot.png");
    private final ResourceLocation BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/back.png");

    public GuiClayCraftingBoard(InventoryPlayer playerInv, World world, BlockPos pos, TileClayCraftingBoard te) {
        super(new ContainerClayCraftingBoard(playerInv, world, pos, te));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();

        GlStateManager.color(1f, 1f, 1f, 1f);

        this.mc.getTextureManager().bindTexture(BACKGROUND);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        this.mc.getTextureManager().bindTexture(SLOT);
        int slotSideLength = 18;
        int offsetX = (this.width - this.xSize) / 2 - (slotSideLength - 16) / 2;
        int offsetY = (this.height - this.ySize) / 2 - (slotSideLength - 16) / 2;
        for (Slot slot : this.inventorySlots.inventorySlots) {
            this.drawTexturedModalRect(offsetX + slot.xPos, offsetY + slot.yPos, 0, 0, 18, 18);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
