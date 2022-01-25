package com.slick.randomthings;

import com.mojang.serialization.Codec;
import com.slick.randomthings.block.ModBlocks;
import com.slick.randomthings.effect.FrictionlessEffect;
import com.slick.randomthings.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("randomthings")
public class RandomThingsMod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static MobEffect FRICTIONLESS_MOB_EFFECT = new FrictionlessEffect(MobEffectCategory.BENEFICIAL, 0xffffff).setRegistryName("frictionless_mob_effect");
    public static ResourceKey<Level> SPECTRE_DIMENSION;

    public RandomThingsMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupModInjects);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setupModInjects(final FMLConstructModEvent event) {
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("randomthings", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        public static final CreativeModeTab GROUP = new CreativeModeTab("randomthings") {

            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("itemGroup.randomthings");
            }

            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.STABLE_ENDER_PEARL);
            }
        };

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            ModBlocks.registerBlocks(blockRegistryEvent);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            ModItems.registerItems(itemRegistryEvent);
        }

        @SubscribeEvent
        public static void onEffectsRegistry(final RegistryEvent.Register<MobEffect> itemRegistryEvent) {
            IForgeRegistry<MobEffect> r = itemRegistryEvent.getRegistry();
            r.register(FRICTIONLESS_MOB_EFFECT);
        }

        public static int SPECTRE_ID = -343800852;
        public static ResourceKey<DimensionType> SPECTRE_TYPE;
        public static ResourceKey<LevelStem> SPECTRE_STEM;
        public static ResourceKey<Codec<? extends ChunkGenerator>> SPECTRE_CHUNK_GENERATOR;
        public static ResourceKey<Biome> SPECTRE_BIOME;
        // /execute in randomthings:spectre_dimension run tp @s 100 100 100
        @SubscribeEvent
        public static void onDimensionRegistry(RegistryEvent.Register<Item> event) {
//            Registry.register(Registry.CHUNK_GENERATOR, "spectre", SpectreChunkProvider.CODEC);
            SPECTRE_BIOME = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("randomthings","worldgen/biome"));
            SPECTRE_CHUNK_GENERATOR = ResourceKey.create(Registry.CHUNK_GENERATOR_REGISTRY, new ResourceLocation("randomthings","worldgen/spectre_chunk_generator"));
            SPECTRE_STEM = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation("randomthings","spectre_level_stem"));
            SPECTRE_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation("randomthings","spectre_dimension_type"));
            SPECTRE_DIMENSION = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("randomthings","spectre_dimension"));
        }
    }
}
