package lumien.randomthings.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class TimeInABottle extends Item
{

    public TimeInABottle(Properties properties)
    {
        super(properties.maxStackSize(1));

    }

    // when right clicking a block this is called
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return ActionResultType.SUCCESS;
    }

    // called every tick inside players inv
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

    }

    // when item is first crafted this is called
    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {

    }
}
