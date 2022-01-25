package com.slick.randomthings.item;

import com.slick.randomthings.RandomThingsMod;
import com.slick.randomthings.handler.SimpleTeleporter;
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
//            SpectreDimensionHandler spectreDimensionHandler;
//            level.getServer().getPlayerList().getPlayer(player.getUUID()).teleportTo(level.getServer().getLevel(RandomThingsMod.SPECTRE_DIMENSION), player.getX(),player.getY(), player.getZ(), 0.0F, 0.0F);
            MinecraftServer minecraftServer = level.getServer();
            if (minecraftServer != null) {
                ServerLevel spectreLevel = minecraftServer.getLevel(RandomThingsMod.SPECTRE_DIMENSION);
                if (spectreLevel != null) {
                    player.changeDimension(spectreLevel, new SimpleTeleporter());
                } else {
                    player.displayClientMessage(new TranslatableComponent("Cannot find Spectre Dimension..."), true);
                }
            }
//            if ((spectreDimensionHandler = SpectreDimensionHandler.getInstance()) != null) {
//                spectreDimensionHandler.teleportPlayerToSpectreCube(level, player);
//            }

//            level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
//            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }
}
