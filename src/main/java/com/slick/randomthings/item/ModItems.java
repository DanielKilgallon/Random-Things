package com.slick.randomthings.item;


import com.slick.randomthings.block.ModBlocks;
import com.slick.randomthings.item.armor.SuperLubricentBoots;
import com.slick.randomthings.item.armor.WaterWalkingBoots;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.slick.randomthings.RandomThingsMod.RegistryEvents.GROUP;

@Mod("randomthings")
public class ModItems {

    @ObjectHolder("stable_ender_pearl")
    public static Item STABLE_ENDER_PEARL = new StableEnderPearl(new Item.Properties().tab(GROUP).stacksTo(16)).setRegistryName("stable_ender_pearl");

    @ObjectHolder("bean")
    public static Item BEAN = new ItemNameBlockItem(ModBlocks.BEAN_CROP, (new Item.Properties()).tab(GROUP)).setRegistryName("bean");

    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(STABLE_ENDER_PEARL);
        r.register(BEAN);

        r.register(new PositionFilter(new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("position_filter"));
        r.register(new SuperLubricent(new Item.Properties().tab(GROUP).stacksTo(16)).setRegistryName("super_lubricent"));
        r.register(new RedstoneActivator(new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("redstone_activator"));
    }

    public static void registerArmorItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(new SuperLubricentBoots(ArmorMaterials.IRON, EquipmentSlot.FEET, new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("super_lubricent_boots"));
        r.register(new WaterWalkingBoots(ArmorMaterials.CHAIN, EquipmentSlot.FEET, new Item.Properties().tab(GROUP).stacksTo(1)).setRegistryName("water_walking_boots"));
    }
}
