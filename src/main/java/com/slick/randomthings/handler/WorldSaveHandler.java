package com.slick.randomthings.handler;

import com.mojang.datafixers.DataFixer;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = "randomthings")
public class WorldSaveHandler extends WorldEvent  {

    public WorldSaveHandler(LevelAccessor world) {
        super(world);
    }

    @SubscribeEvent
        public static void onWorldSave(WorldEvent.Save event) {

            LevelAccessor levelaccessor = event.getWorld();
            MinecraftServer server = levelaccessor.getServer();
            DataFixer datafixer = server.getFixerUpper();
            // datafixer.
        }

    @SubscribeEvent
        public static void onWorldLoad(WorldEvent.Load event) {

            System.out.println("tomato on world load");
        }
}
