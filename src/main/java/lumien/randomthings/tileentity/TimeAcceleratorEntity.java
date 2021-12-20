package lumien.randomthings.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class TimeAcceleratorEntity extends Entity implements IEntityAdditionalSpawnData {

    private int remainingTime;
    private BlockPos target;

    private static final DataParameter<Integer> TIME_RATE = EntityDataManager.createKey(TimeAcceleratorEntity.class, DataSerializers.VARINT);

    public TimeAcceleratorEntity(EntityType entityType, World worldIn) {
        super(entityType, worldIn);
        this.noClip = true;
        this.dataManager.register(TIME_RATE, 1);
    }

    public TimeAcceleratorEntity(World worldIn, BlockPos target, double posX, double posY, double posZ) {
        this(ModTileEntityTypes.TIME_ACCELERATOR, worldIn);
        this.target = target;
        this.setPosition(posX, posY, posZ);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public void tick() {
        super.tick();

        if (target == null) {
            if (!world.isRemote) {
                remove();
            }
            return;
        }

        BlockState blockState = world.getBlockState(target);
        MinecraftServer minecraftServer = world.getServer();
        if (minecraftServer != null) {
            ServerWorld serverWorld = minecraftServer.getWorld(world.getDimension().getType());
            TileEntity targetTE = world.getTileEntity(target);

            for (int i = 0; i < getTimeRate(); i++) {
                // if is tickable TileEntity (furnace, brewing stand, ...)
                if (targetTE instanceof ITickableTileEntity) {
                    ((ITickableTileEntity) targetTE).tick();
                } else {
                    if (blockState.ticksRandomly() && world.rand.nextInt(1365) == 0) {
                        blockState.randomTick(serverWorld, target, world.rand);
                    }
                }
            }
        }

        this.remainingTime -= 1;
        if (this.remainingTime <= 0 && !this.world.isRemote) {
            remove();
        }
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if (buffer != null && target != null && buffer.isWritable()) {
            buffer.writeBlockPos(target);
        }
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        if (additionalData != null && additionalData.isReadable()) {
            this.target = additionalData.readBlockPos();
        }
    }

    public int getTimeRate() {
        return this.dataManager.get(TIME_RATE);
    }

    public void setTimeRate(int timeRate) {
        this.dataManager.set(TIME_RATE, timeRate);
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public BlockPos getPos() {
        return target;
    }

    public void setPos(BlockPos target) {
        this.target = target;
    }
}
