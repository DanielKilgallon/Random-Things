package com.slick.randomthings.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class FrictionlessEffect extends MobEffect {

    private static final String CLASS_LOCATION = "net/minecraft/world/entity/LivingEntity.class";

    public FrictionlessEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
//        AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
//        if (entity instanceof Player player) {
        if (!entity.isCrouching()) {
//               float speed = entity.getSpeed();
//               entity.setSpeed(speed * 1.099F);
            //{AttributeModifier:[{AttributeName:"generic.movementSpeed",Name:"generic.movementSpeed",Amount:0.5,Operation:0,UUIDLeast:8000,UUIDMost:4000}]}

//            AttributeModifier SPEED_MODIFIER_BEAN = new AttributeModifier(UUID.fromString("4C179DD1-A49E-45DC-94CA-A3ED1C3F0B57"), "Frictionless Boosty Boi", 1.099F, AttributeModifier.Operation.ADDITION);
//            assert speed != null;
//            speed.removeModifiers();
//            speed.addTransientModifier(SPEED_MODIFIER_BEAN);
        }
//        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
