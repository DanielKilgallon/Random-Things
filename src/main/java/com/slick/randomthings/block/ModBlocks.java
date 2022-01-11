package com.slick.randomthings.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod("randomthings")
public class ModBlocks {

    @ObjectHolder("bean_crop")
    public static Block BEAN_CROP = new BeanCrop().setRegistryName("bean_crop");

    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();

        r.register(BEAN_CROP);
    }
}
