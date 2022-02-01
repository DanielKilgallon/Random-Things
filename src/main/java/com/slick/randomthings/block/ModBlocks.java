package com.slick.randomthings.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

@Mod("randomthings")
public class ModBlocks {

    @ObjectHolder("bean_crop")
    public static Block BEAN_CROP = new BeanCrop().setRegistryName("bean_crop");
    @ObjectHolder("super_lubricent_stone")
    public static Block SUPER_LUBRICENT_STONE = new SuperLubricentStone().setRegistryName("super_lubricent_stone");
    @ObjectHolder("spectre_block")
    public static Block SPECTRE_BLOCK = new SpectreBlock().setRegistryName("spectre_block");
    // @ObjectHolder("spectre_core")
    // public static Block SPECTRE_CORE = new SpectreCore().setRegistryName("spectre_core");

    public static List<Block> BLOCKS = Arrays.asList(BEAN_CROP, SUPER_LUBRICENT_STONE, SPECTRE_BLOCK);

    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();

        for (Block block : BLOCKS) {
            r.register(block);
        }
    }
}
