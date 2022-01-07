package com.slick.randomthings.item;


import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.slick.randomthings.RandomThingsMod.RegistryEvents.GROUP;

@Mod("randomthings")
public class ModItems {

    @ObjectHolder("stable_ender_pearl")
    public static Item STABLE_ENDER_PEARL = new StableEnderPearl(new Item.Properties());

    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(new StableEnderPearl(new Item.Properties().tab(GROUP).stacksTo(16)).setRegistryName("randomthings", "stable_ender_pearl"));
        r.register(new PositionFilter(new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("randomthings", "position_filter"));
        r.register(new SuperLubricent(new Item.Properties().tab(GROUP).stacksTo(16)).setRegistryName("randomthings", "super_lubricent"));
        r.register(new RedstoneActivator(new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("randomthings", "redstone_activator"));
    }

    public static void registerArmorItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(new SuperLubricentBoots(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
                return 100;
            }

            @Override
            public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
                return 2;
            }

            @Override
            public int getEnchantmentValue() {
                return 0;
            }

            @Override
            public SoundEvent getEquipSound() {
                return null;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }

            @Override
            public String getName() {
                return "super_lubricent_boots";
            }

            @Override
            public float getToughness() {
                return 0;
            }

            @Override
            public float getKnockbackResistance() {
                return 0;
            }
        }, EquipmentSlot.FEET, new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("randomthings", "super_lubricent_boots"));
    }
}
