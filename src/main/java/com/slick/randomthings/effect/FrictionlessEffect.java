package com.slick.randomthings.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FrictionlessEffect extends MobEffect {
    public FrictionlessEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.noPhysics = true;
            /*entity.getCommandSenderWorld().explode(
                null, entity.getX(), entity.getY(), entity.getZ(), amplifier+1, Explosion.Mode.NONE);
        super.applyEffectTick(entity, amplifier);*/
            // this.slipperiness = 1F / 0.98F;
        }
    }
}
