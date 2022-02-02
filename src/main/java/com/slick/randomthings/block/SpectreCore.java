package com.slick.randomthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SpectreCore extends BaseEntityBlock {
    protected SpectreCore() {
		super(BlockBehaviour.Properties.copy(Blocks.BEDROCK));
	 }

	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.MODEL;
	}

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        // TODO Auto-generated method stub
        System.out.println("newBlockEntity");
        return null;
    }

	


    // public boolean triggerEvent(BlockState p_49226_, Level p_49227_, BlockPos p_49228_, int p_49229_, int p_49230_) {
    //     System.out.println("newBlockEntity");
    //     super.triggerEvent(p_49226_, p_49227_, p_49228_, p_49229_, p_49230_);
    //     BlockEntity blockentity = p_49227_.getBlockEntity(p_49228_);
    //     return blockentity == null ? false : blockentity.triggerEvent(p_49229_, p_49230_);
    //  }

    // @Override
	// public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	// {
	// 	if (worldIn.provider.getDimension() == Internals.SPECTRE_ID)
	// 	{
	// 		ItemStack holding = playerIn.getHeldItem(hand);

	// 		if (holding.getItem() == ModItems.ingredients && holding.getItemDamage() == ItemIngredient.INGREDIENT.ECTO_PLASM.id)
	// 		{
	// 			if (!worldIn.isRemote)
	// 			{
	// 				SpectreCube cube = SpectreHandler.getInstance().getSpectreCubeFromPos(worldIn, pos.up());

	// 				if (cube != null)
	// 				{
	// 					holding.shrink(cube.increaseHeight(holding.getCount()));
	// 				}
	// 			}
	// 			return true;
	// 		}
	// 		else if (holding.isEmpty())
	// 		{
	// 			if (!worldIn.isRemote)
	// 			{
	// 				SpectreHandler.getInstance().teleportPlayerBack((EntityPlayerMP) playerIn);
	// 			}
	// 			return true;
	// 		}
	// 	}

	// 	return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	// }
}
