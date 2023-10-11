package com.github.trcdeveloppers.clayium.common.blocks;

import com.github.trcdeveloppers.clayium.common.annotation.CBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;

import static com.github.trcdeveloppers.clayium.common.creativetab.ClayiumCreativeTab.CLAYIUM;

@CBlock(registryName = "clay_crafting_board")
public class BlockClayCraftingBoard extends BlockContainer {
    private final AxisAlignedBB BOUND_BOX = new AxisAlignedBB(0f,0f,0f,1f,0.25f,1f);

    protected BlockClayCraftingBoard(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(CLAYIUM);
        this.setHardness(1f);
        this.setResistance(4f);
        this.setHarvestLevel("shovel",0);
    }

    @SuppressWarnings("unused")
    public BlockClayCraftingBoard() {
        this(Material.CLAY);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        addCollisionBoxToList(pos,entityBox,collidingBoxes,BOUND_BOX);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUND_BOX;
    }

}
