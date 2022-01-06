package com.slick.randomthings.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RedstoneActivator extends Item {

    private static final String TICK_TAG = "tickTag";
    private static final String TICK_SETTING = "tickSetting";

    public RedstoneActivator(Properties props) {
        super(props);
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        BlockEntity blockEntity = context.getPlayer().getLevel().getBlockEntity(blockPos);
        System.out.println(blockEntity.toString());
        return InteractionResult.PASS;
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);
        CompoundTag tickTag = item.getOrCreateTagElement(TICK_TAG);
        tickTag.putInt(TICK_SETTING, 1);
        item.save(tickTag);
        return InteractionResultHolder.success(item);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompoundTag tickTag = itemStack.getOrCreateTagElement(TICK_TAG);
        int tickSetting = tickTag.getInt(TICK_SETTING);
        if (tickSetting != 0) {
            tooltip.add(new TranslatableComponent(I18n.get("item.randomthings.redstone_activator_setting", tickSetting)).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
}
