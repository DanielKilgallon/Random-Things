package com.slick.randomthings.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FrictionlessEffect extends MobEffect {

    public FrictionlessEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
         if (entity instanceof Player player && !player.isShiftKeyDown()) {
             player.setDiscardFriction(true);
         }

         super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }

    @Override
    public Component getDisplayName() {
        return super.getDisplayName();
    }
}
