package com.balsam.selectable_convertor.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ArtransferProductSlot extends Slot {

    public ArtransferProductSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    @Override
    public void set(ItemStack itemStack) {
        super.set(itemStack);
        if (itemStack.getItem() == Items.AIR){
            removeAll();
        }
    }

    @Override
    public ItemStack remove(int index) {
        ItemStack remove = super.remove(index);
        removeAll();
        return remove;
    }

    private void removeAll() {
        for (int i = 0; i < this.container.getContainerSize(); i++) {
            this.container.removeItem(i, 1);
        }
    }
}
