package com.slick.randomthings.handler;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = "randomthings")
public class WorldSaveHandler extends WorldEvent {

    private static SpectreDimensionHandler spectreDimInstance;

    public WorldSaveHandler(LevelAccessor world) {
        super(world);
    }

    public static SpectreDimensionHandler getSpectreDimensionHandler() {
        if (spectreDimInstance == null) {
            spectreDimInstance = new SpectreDimensionHandler(0);
        }
        return spectreDimInstance;
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) {
        System.out.println("tomato save");
        LevelAccessor levelaccessor = event.getWorld();
        MinecraftServer server = levelaccessor.getServer();
        if (server != null) {
            server.overworld().getDataStorage().set("spectre_dim_data", getSpectreDimensionHandler());
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        System.out.println("tomato load");
        LevelAccessor levelaccessor = event.getWorld();
        MinecraftServer server = levelaccessor.getServer();
        if (server != null) {
            spectreDimInstance = server.overworld().getDataStorage().get(SpectreDimensionHandler::load, "spectre_dim_data");
        }
    }
}
