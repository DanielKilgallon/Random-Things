package com.slick.randomthings.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class FrictionlessEffect extends MobEffect {

    private static final String CLASS_LOCATION = "net/minecraft/world/entity/LivingEntity.class";

    public FrictionlessEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

//        if (entity instanceof Player player) {
           if (!entity.isCrouching()) {
//               entity.handleRelativeFrictionAndCalculateMovement(new Vec3(10, 10, 10), 31.099F);
//               float speed = entity.getSpeed();
//               entity.setSpeed(speed * 1.099F);
               AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
               AttributeModifier SPEED_MODIFIER_BEAN = new AttributeModifier(UUID.fromString("4C179DD1-A49E-45DC-94CA-A3ED1C3F0B57"), "Frictionless Boosty Boi", 0.099F, AttributeModifier.Operation.MULTIPLY_BASE);
               assert speed != null;
               speed.addTransientModifier(SPEED_MODIFIER_BEAN);
           }
//        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
