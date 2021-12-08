package lumien.randomthings.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class TimeAcceleratorEntity extends Entity implements ITickableTileEntity {

    //CompoundNBT
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
