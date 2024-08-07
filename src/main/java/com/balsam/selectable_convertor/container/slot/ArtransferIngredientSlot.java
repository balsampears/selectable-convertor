package com.balsam.selectable_convertor.container.slot;

import com.balsam.selectable_convertor.container.ArtransferContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ArtransferIngredientSlot extends Slot {

    public ArtransferIngredientSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public void set(ItemStack itemStack) {
        super.set(itemStack);
        if (itemStack.getItem() == Items.AIR){
            removeAfter(ArtransferContainer.INGREDIENT_AMOUNT);
        }
    }

    @Override
    public ItemStack remove(int index) {
        removeAfter(ArtransferContainer.INGREDIENT_AMOUNT);
        return super.remove(index);
    }

    @Override
    protected void onSwapCraft(int p_190900_1_) {
        super.onSwapCraft(p_190900_1_);
    }

    private void removeAfter(int afterIndex) {
        for (int i = afterIndex; i < this.container.getContainerSize(); i++) {
            this.container.removeItem(i, 1);
        }
    }
}
