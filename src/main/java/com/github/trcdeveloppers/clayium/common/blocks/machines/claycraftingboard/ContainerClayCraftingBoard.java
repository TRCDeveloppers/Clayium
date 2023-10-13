package com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class ContainerClayCraftingBoard extends Container {

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private final World world;
    private final BlockPos pos;
    private final EntityPlayer player;
    private final TileClayCraftingBoard te;

    public ContainerClayCraftingBoard(InventoryPlayer playerInventory, World worldIn, BlockPos posIn, TileClayCraftingBoard te) {
        this.world = worldIn;
        this.pos = posIn;
        Optional<IItemHandler> handler = Optional.empty();
        for (EnumFacing facing : EnumFacing.values()) {
            TileEntity tileEntityFound = worldIn.getTileEntity(posIn.offset(facing));
            if (tileEntityFound instanceof TileEntityChest) {
                handler = Optional.of(((TileEntityChest) tileEntityFound).getSingleChestHandler());
            }
        }
        this.player = playerInventory.player;
        this.te = te;

        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        handler.ifPresent(h -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    this.addSlotToContainer(new SlotItemHandler(h, i * 9 + j, 8 + j * 18, 84 + i * 18));
                }
            }
        });

        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 36, 8 + i1 * 18, 156 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18 + 27, 214));
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote) {
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

}

