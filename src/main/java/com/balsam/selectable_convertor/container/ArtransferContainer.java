package com.balsam.selectable_convertor.container;

import com.balsam.selectable_convertor.container.slot.ArtransferIngredientSlot;
import com.balsam.selectable_convertor.container.slot.ArtransferProductSlot;
import com.balsam.selectable_convertor.registry.ContainerTypeRegistry;
import com.balsam.selectable_convertor.tileEntity.ArtransferTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArtransferContainer extends Container {

    /**
     * 原材料格子数量
     */
    public static final int INGREDIENT_AMOUNT = 2;

    public ArtransferContainer(int id, World world, BlockPos blockPos, PlayerInventory playerInventory) {
        super(ContainerTypeRegistry.ArtransferContainer.get(), id);
        this.layoutPlayerInventorySlots(playerInventory, 8, 84);
        ArtransferTileEntity blockEntity = (ArtransferTileEntity) world.getBlockEntity(blockPos);
        addSlotStation(blockEntity);
    }

    private void addSlotStation(ArtransferTileEntity blockEntity) {
        Inventory inventory = blockEntity.getInventory();
        //添加原材料的两个槽位
        this.addSlot(new ArtransferIngredientSlot(inventory, 0, 27, 33));
        this.addSlot(new ArtransferIngredientSlot(inventory, 1, 68, 33));
        //添加产物的槽位
        int index = INGREDIENT_AMOUNT;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Slot slot = new ArtransferProductSlot(inventory, index++, 108 + x * 18, 15 + y * 18);
                this.addSlot(slot);
            }
        }
//        this.addSlotBox(inventory, 2, 108, 15, 3, 18, 3, 18);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return true;
    }

    /**
     * shift快捷转移
     *
     * @param playerEntity
     * @param index
     * @return
     */
    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        int containerSlotAmount = 4 * 9;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < containerSlotAmount) {
                if (!this.moveItemStackTo(itemstack1, containerSlotAmount, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, containerSlotAmount, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    /**
     * 添加玩家快捷栏与库存
     *
     * @param inventory
     * @param leftCol
     * @param topRow
     */
    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // 快捷栏
        addSlotRow(inventory, 0, leftCol, topRow + 58, 9, 18);
        // 库存
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
    }

    /**
     * 添加一行格子
     *
     * @param inventory 库存
     * @param index     格子在库存的序号
     * @param x         在整个界面的横坐标
     * @param y         在整个界面的纵坐标
     * @param amount    本行格子数量
     * @param dx        格子间距
     * @return
     */
    private int addSlotRow(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    /**
     * 添加一箱格子
     *
     * @param inventory 库存
     * @param index     格子在库存的序号
     * @param x         在整个界面的横坐标
     * @param y         在整个界面的纵坐标
     * @param horAmount 横向格子数量
     * @param dx        横向格子间距
     * @param verAmount 竖向格子数量
     * @param dy        竖向格子间距
     * @return
     */
    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRow(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
}
