package com.github.trcdeveloppers.clayium.common.blocks.machines.claycraftingboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TileClayCraftingBoard extends TileEntity {

    private final World world;
    @Nullable
    private final TileEntityChest linkedChestTE;

    public TileClayCraftingBoard(World worldIn) {
        super();
        this.world = worldIn;
        this.linkedChestTE = this.searchNeighborChest();
    }

    @Nullable
    private TileEntityChest searchNeighborChest() {
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos pos = this.pos.offset(facing);
            TileEntity tileEntity = this.world.getTileEntity(pos);
            if (tileEntity instanceof TileEntityChest) {
                return (TileEntityChest) tileEntity;
            }
        }
        return null;
    }

}
