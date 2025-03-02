package com.slick.randomthings.item.armor;

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
        player.setDiscardFriction(!player.isShiftKeyDown());
        super.onArmorTick(stack, world, player);
    }


}
