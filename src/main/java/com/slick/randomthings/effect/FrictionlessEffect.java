package com.slick.randomthings.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FrictionlessEffect extends MobEffect {

    private static final String CLASS_LOCATION = "net/minecraft/world/entity/LivingEntity.class";

    public FrictionlessEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.setDiscardFriction(!player.isCrouching());
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
