package com.slick.randomthings.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class PositionFilter extends Item {

    public static final String BLOCK_POS_TAG = "blockPosTag";

    public static final String BLOCK_POS_X = "blockPosX";
    public static final String BLOCK_POS_Y = "blockPosY";
    public static final String BLOCK_POS_Z = "blockPosZ";
    public static final String HAS_POSITION = "hasPosition";

    public PositionFilter(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack item = context.getItemInHand();
        CompoundTag positionTag = item.getOrCreateTagElement(BLOCK_POS_TAG);
        BlockPos position = context.getClickedPos();
        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        positionTag.putBoolean(HAS_POSITION, true);
        positionTag.putInt(BLOCK_POS_X, x);
        positionTag.putInt(BLOCK_POS_Y, y);
        positionTag.putInt(BLOCK_POS_Z, z);
        item.save(positionTag);

//        RenderResizableCuboid.INSTANCE.renderCube(position);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompoundTag positionTag = itemStack.getOrCreateTagElement(BLOCK_POS_TAG);
        if (positionTag.getBoolean(HAS_POSITION)) {
            int x = positionTag.getInt(BLOCK_POS_X);
            int y = positionTag.getInt(BLOCK_POS_Y);
            int z = positionTag.getInt(BLOCK_POS_Z);
            tooltip.add(new TranslatableComponent(I18n.get("item.randomthings.position_filter_coordinates", x, y, z)).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }

    public BlockPos getPosition(ItemStack positionFilter) {
        CompoundTag positionTag = positionFilter.getOrCreateTagElement(BLOCK_POS_TAG);
        if (positionTag.getBoolean(HAS_POSITION)) {
            int x = positionTag.getInt(BLOCK_POS_X);
            int y = positionTag.getInt(BLOCK_POS_Y);
            int z = positionTag.getInt(BLOCK_POS_Z);
            return new BlockPos(x, y, z);
        }
        return null;
    }
}