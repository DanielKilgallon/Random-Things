package lumien.randomthings.tileentity;

import java.util.function.Supplier;

import lumien.randomthings.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("randomthings")
public class ModTileEntityTypes {
    @ObjectHolder("advanced_redstone_torch")
    public static TileEntityType<AdvancedRedstoneTorchTileEntity> ADVANCED_REDSTONE_TORCH;

    @ObjectHolder("blood_rose")
    public static TileEntityType<BloodRoseTileEntity> BLOOD_ROSE;

    public static EntityType<TimeAcceleratorEntity> TIME_ACCELERATOR = EntityType.Builder.<TimeAcceleratorEntity>create(TimeAcceleratorEntity::new, EntityClassification.MISC)
            .size(0.1F, 0.1F)
            .setCustomClientFactory((spawnEntity, world) -> new TimeAcceleratorEntity(ModTileEntityTypes.TIME_ACCELERATOR, world))
            .build("");

    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent) {
        IForgeRegistry<TileEntityType<?>> tileRegistry = tileEntityRegistryEvent.getRegistry();

        registerSimple(tileRegistry, "advanced_redstone_torch", AdvancedRedstoneTorchTileEntity::new, ModBlocks.ADVANCED_REDSTONE_TORCH, ModBlocks.ADVANCED_WALL_REDSTONE_TORCH);
        registerSimple(tileRegistry, "blood_rose", BloodRoseTileEntity::new, ModBlocks.BLOOD_ROSE);
    }

    public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> entityRegisterEvent) {
        IForgeRegistry<EntityType<?>> EntityRegistry = entityRegisterEvent.getRegistry();

        EntityRegistry.register(TIME_ACCELERATOR.setRegistryName("time_in_a_bottle"));
    }

    private static void registerSimple(IForgeRegistry<TileEntityType<?>> registry, String name, Supplier<? extends TileEntity> factoryIn, Block... validBlocks) {
        registry.register(TileEntityType.Builder.create(factoryIn, validBlocks).build(null).setRegistryName(name));
    }
}