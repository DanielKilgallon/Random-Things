package com.slick.randomthings.item;

import com.slick.randomthings.RandomThingsMod;
import com.slick.randomthings.handler.SimpleTeleporter;
import com.slick.randomthings.handler.SpectreDimensionHandler;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SpectreKey extends Item {
    public SpectreKey(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        System.out.println("DONE");
        return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int p_41415_) {
        if (entity instanceof Player player) {
            SpectreDimensionHandler spectreDimensionHandler;
            if ((spectreDimensionHandler = SpectreDimensionHandler.getInstance()) != null) {
                spectreDimensionHandler.teleportPlayerToSpectreCube(level, player);
            }
        }
    }
}
