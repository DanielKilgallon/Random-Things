package lumien.randomthings.item;

import lumien.randomthings.tileentity.TimeAcceleratorEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TimeInABottle extends Item {
    private static final String[] NOTES = {"C", "D", "E", "F", "G2", "A2", "B2", "C2", "D2", "E2", "F2"};
    public static final String TIME_DATA = "timeData";
    public static final String STORED_TIME = "storedTime";

    public TimeInABottle(Properties properties) {
        super(properties.maxStackSize(1));
    }

    // when right clicking a block this is called
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();

        if (world.isRemote) {
            return ActionResultType.PASS;
        }
        BlockPos pos = context.getPos();
        BlockState blockState = world.getBlockState(pos);
        TileEntity targetTileEntity = world.getTileEntity(pos);
        ItemStack itemStack = context.getItem();
        PlayerEntity player = context.getPlayer();

        //TODO check if this works with furnaces from .ticksRandomly()
        if (!blockState.ticksRandomly() && (targetTileEntity == null) || !(targetTileEntity instanceof ITickableTileEntity)) {
            return ActionResultType.FAIL;
        }


        boolean isCreativeMode = player != null && player.abilities.isCreativeMode;

        Optional<TimeAcceleratorEntity> optionalTAEntity = context.getWorld().getEntitiesWithinAABB(TimeAcceleratorEntity.class, new AxisAlignedBB(pos).shrink(0.2)).stream().findFirst();

        // if previous Time Accelerator Entity exists already
        if (optionalTAEntity.isPresent()) {
            TimeAcceleratorEntity acceleratorEntity = optionalTAEntity.get();
            int currentRate = acceleratorEntity.getTimeRate();
            if (currentRate >= 5) {
                return ActionResultType.SUCCESS;
            }

            int nextRate = currentRate + 1;
            int energyRequired = getEnergyCost(nextRate);

            int usedUpTime = (30 * 20) - acceleratorEntity.getRemainingTime();
            int timeAdded = usedUpTime / 2;

            if (getStoredEnergy(itemStack) >= energyRequired || isCreativeMode) {
                return ActionResultType.SUCCESS;
            }

            acceleratorEntity.setTimeRate(nextRate);
            acceleratorEntity.setRemainingTime(acceleratorEntity.getRemainingTime() + timeAdded);
        } else {
            if (getStoredEnergy(itemStack) >= getEnergyCost(1) || isCreativeMode) {
                return ActionResultType.SUCCESS;
            }

            TimeAcceleratorEntity entityTA = new TimeAcceleratorEntity(context.getWorld(), pos, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            entityTA.setRemainingTime(30 * 20);
            world.addEntity(entityTA);
        }

        return ActionResultType.SUCCESS;
    }

    private int getEnergyCost(int nextRate) {
        return nextRate * 30;
    }

    // called every tick inside players inv
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);

        if (worldIn.isRemote) {
            return;
        }

        //checks every second, to add one second
        if (worldIn.getWorldInfo().getGameTime() % 20 == 0) {
            CompoundNBT nbtTagCompound = stack.getOrCreateChildTag(TIME_DATA);
            int storedTime = nbtTagCompound.getInt(STORED_TIME);
            if (storedTime < Integer.MAX_VALUE) {
                nbtTagCompound.putInt(STORED_TIME, storedTime + 20);
            }
        }

        // remove time if player has other TIAB items in his inventory
        if (worldIn.getWorldInfo().getGameTime() % (20 * 10) == 0) {
            if (!(entityIn instanceof PlayerEntity)) {
                return;
            }

            PlayerEntity player = (PlayerEntity) entityIn;

            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack invStack = player.inventory.getStackInSlot(i);
                if (invStack.getItem() == this && invStack != stack) {
                    int otherTimeData = invStack.getOrCreateChildTag(TIME_DATA).getInt(STORED_TIME);
                    int myTimeData = stack.getOrCreateChildTag(TIME_DATA).getInt(STORED_TIME);

                    if (myTimeData < otherTimeData) {
                        setStoredEnergy(stack, 0);
                    }
                }
            }
        }
    }

    public void setStoredEnergy(ItemStack stack, int energy) {
        CompoundNBT timeData = stack.getChildTag(TIME_DATA);
        if (timeData == null) {
            return;
        }
        int newStoredTime = Math.min(energy, Integer.MAX_VALUE);
        timeData.putInt(STORED_TIME, newStoredTime);
    }

    public int getStoredEnergy(ItemStack stack) {
        CompoundNBT timeData = stack.getChildTag(TIME_DATA);
        return timeData == null ? 0 : timeData.getInt(STORED_TIME);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbtTagCompound = stack.getOrCreateChildTag(TIME_DATA);

        int storedTime = nbtTagCompound.getInt(STORED_TIME);
        int storedSeconds = storedTime / 20; // ticks in a second
        int hours = storedSeconds / 3600;
        int minutes = (storedSeconds % 3600) / 60;
        int seconds = storedSeconds % 60;

        tooltip.add(new StringTextComponent(I18n.format("item.randomthings.time_in_a_bottle.tooltip", hours, String.format("%02d", minutes), String.format("%02d", seconds))));
    }
}
