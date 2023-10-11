package com.github.trcdeveloppers.clayium.common.blocks.machines.clayworktable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ContainerClayWorktable extends Container {

    private final TileClayWorkTable tile;

    private int lastCraftingProgress = 0;
    private int lastRequiredProgress = 0;

    public ContainerClayWorktable(IInventory playerInv, TileClayWorkTable te) {
        this.tile = te;
        IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        // Input
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 17, 30) {
            @Override
            public void onSlotChanged() {
                te.resetRecipeIfEmptyInput();
                te.markDirty();
            }
        });
        // Tool
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 1, 80, 17) {
            @Override
            public void onSlotChanged() {
                te.markDirty();
            }
        });
        // Primary Output
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 2, 143, 30) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }

            @Override
            public void onSlotChanged() {
                te.markDirty();
            }
        });
        // Secondary Output
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 3, 143, 55) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }

            @Override
            public void onSlotChanged() {
                te.markDirty();
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i*9 + 9, 8 + j*18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, this.tile.craftingProgress);
        listener.sendWindowProperty(this, 1, this.tile.requiredProgress);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {
            if (this.lastCraftingProgress != this.tile.craftingProgress) {
                listener.sendWindowProperty(this, 0, this.tile.craftingProgress);
            }
            if (this.lastRequiredProgress != this.tile.requiredProgress) {
                listener.sendWindowProperty(this, 1, this.tile.requiredProgress);
            }
        }

        this.lastCraftingProgress = this.tile.craftingProgress;
        this.lastRequiredProgress = this.tile.requiredProgress;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        switch (id) {
            case 0:
                this.tile.craftingProgress = data;
                break;
            case 1:
                this.tile.requiredProgress = data;
                break;
        }
    }

    @Override
    public boolean enchantItem(EntityPlayer playerIn, int id) {
        this.tile.pushButton(id);
        return true;
    }
}
