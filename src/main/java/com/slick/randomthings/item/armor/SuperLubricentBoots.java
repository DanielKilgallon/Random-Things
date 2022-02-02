package com.slick.randomthings.item.armor;

import com.slick.randomthings.RandomThingsMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

//BUG: Does not compute friction correctly, infinite speed is not good. Please patch
public class SuperLubricentBoots extends ArmorItem {

    public SuperLubricentBoots(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "randomthings:textures/models/armor/super_lubricent_boots.png";
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.tickCount % 40 == 0) {
            player.addEffect(new MobEffectInstance(RandomThingsMod.FRICTIONLESS_MOB_EFFECT, 40, 0)); //40 ticks will prevent the ability from failing
        }
    }
}
