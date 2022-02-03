package com.slick.randomthings.block;

import java.io.Serializable;

import com.slick.randomthings.handler.SpectreCube;
import com.slick.randomthings.handler.WorldSaveHandler;
import com.slick.randomthings.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

public class SpectreCore extends Block {
	
	private Property<Rotation> FACING;
	private ORIENTATION orientation;
	
	enum ORIENTATION implements Serializable
	{
		NW("nw"), NE("ne"), ES("es"), SW("sw");

		String value;

		private ORIENTATION(String value)
		{
			this.value = value;
		}

		public String toString()
		{
			return value;
		}

		public String getName()
		{
			return value;
		}
	}

	protected SpectreCore() {
		super(BlockBehaviour.Properties.copy(Blocks.BEDROCK));
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
		if (itemStack.is(ModItems.ECTOPLASM)) {
			SpectreCube cube = WorldSaveHandler.getSpectreDimensionHandler().getCube(player.getUUID());
			// stops client side editing of blocks
			if (cube != null && !level.isClientSide()) {
				int count = itemStack.getCount();
				itemStack.shrink(cube.addHeight(level, count));
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

	//TODO: Rotate block does not work, need to find better method to rotate
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		System.out.println(blockPlaceContext.getClickedPos());
		Direction direction = blockPlaceContext.getHorizontalDirection();
		if (direction == Direction.NORTH) {
			System.out.println("NORTH tomato ");
			orientation = ORIENTATION.NW;
			return defaultBlockState().setValue(FACING, Rotation.CLOCKWISE_90);
		} else if (direction == Direction.SOUTH){
			System.out.println("SOUTH tomato ");
			orientation = ORIENTATION.SW;
			return defaultBlockState().setValue(FACING, Rotation.CLOCKWISE_180);
		} else if (direction == Direction.EAST) {
			System.out.println("EAST tomato ");
			orientation = ORIENTATION.NE;
			return defaultBlockState().setValue(FACING, Rotation.COUNTERCLOCKWISE_90);
		}
		System.out.println("WEST tomato ");
		orientation = ORIENTATION.ES;
		return defaultBlockState().setValue(FACING, Rotation.NONE);
	}
}
