package com.slick.randomthings.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class StableEnderPearl extends Item {

    private static final String PLAYER_TAG = "playerTag";
    private static final String ASSIGNED_PLAYER_KEY = "assignedPlayer";
    private static final int TELEPORT_WAIT_TICKS = 140;

    public StableEnderPearl(Properties props) {
        super(props);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);
        CompoundTag playerTag = item.getOrCreateTagElement(PLAYER_TAG);
        playerTag.putString(ASSIGNED_PLAYER_KEY, player.getUUID().toString());
        item.save(playerTag);
        return InteractionResultHolder.success(item);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompoundTag playerTag = itemStack.getOrCreateTagElement(PLAYER_TAG);
        String assignedPlayer = playerTag.getString(ASSIGNED_PLAYER_KEY);
        if (!assignedPlayer.isEmpty()) {
            Player player = level.getPlayerByUUID(UUID.fromString(assignedPlayer));
            if (player != null) {
                String playerName = player.getName().getString();
                tooltip.add(new TranslatableComponent(I18n.get("item.randomthings.stable_ender_pearl.boundto", playerName)).withStyle(ChatFormatting.DARK_PURPLE));
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity itemEntity) {
        CompoundTag playerTag = itemStack.getOrCreateTagElement(PLAYER_TAG);
        if (itemEntity.getAge() > TELEPORT_WAIT_TICKS) {
            String assignedPlayer = playerTag.getString(ASSIGNED_PLAYER_KEY);
            if (!assignedPlayer.isEmpty()) {
                Level world = itemEntity.getCommandSenderWorld();
                Player player = world.getPlayerByUUID(UUID.fromString(assignedPlayer));
                if (player != null) {
                    player.playSound(SoundEvents.ENDERMAN_TELEPORT, 100, 100);

                    if (world.dimension().equals(player.getCommandSenderWorld().dimension())) {
                        player.moveTo(itemEntity.position());
                    }
                }
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
        return false;
    }
}