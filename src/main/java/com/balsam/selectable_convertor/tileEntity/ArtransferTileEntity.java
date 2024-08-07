package com.balsam.selectable_convertor.tileEntity;

import com.balsam.selectable_convertor.config.Constants;
import com.balsam.selectable_convertor.container.ArtransferContainer;
import com.balsam.selectable_convertor.recipe.ArtransferRecipe;
import com.balsam.selectable_convertor.recipe.ModRecipeType;
import com.balsam.selectable_convertor.registry.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class ArtransferTileEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity {

    private Inventory inventory = new Inventory(11);

    public ArtransferTileEntity() {
        super(TileEntityTypeRegistry.ArtransferTileEntity.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Constants.MOD_ID + ".artransfer");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ArtransferContainer(id, this.level, this.worldPosition, playerInventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void tick() {
        if (hasRecipe()) {
            displayItem();
        }
    }

    private boolean hasRecipe() {
        Optional<ArtransferRecipe> recipeOpt = this.level.getRecipeManager().getRecipeFor(ModRecipeType.ARTRANSFER, inventory, level);
        if (!recipeOpt.isPresent()) return false;
        ArtransferRecipe recipe = recipeOpt.get();
        return recipe.matches(inventory, level);
    }

    private void displayItem() {
        Optional<ArtransferRecipe> recipeOpt = this.level.getRecipeManager().getRecipeFor(ModRecipeType.ARTRANSFER, inventory, level);
        if (!recipeOpt.isPresent()) return;
        ArtransferRecipe recipe = recipeOpt.get();
        List<ItemStack> resultItems = recipe.getResultItems();
        int startIndex = ArtransferContainer.INGREDIENT_AMOUNT;
        for (int i = startIndex; i < inventory.getContainerSize(); i++) {
            //防止数组越界
            if (i - startIndex >= resultItems.size()) break;
            ItemStack itemStack = new ItemStack(resultItems.get(i - startIndex).getItem(), 1);
            inventory.setItem(i, itemStack);
        }
    }

    @Override
    public void load(BlockState blockState, CompoundNBT compoundNBT) {
        ItemStack item0 = ItemStack.of(compoundNBT.getCompound("item0"));
        ItemStack item1 = ItemStack.of(compoundNBT.getCompound("item1"));
        inventory.addItem(item0);
        inventory.addItem(item1);
        super.load(blockState, compoundNBT);
    }

    @Override
    public CompoundNBT save(CompoundNBT compoundNBT) {
        compoundNBT.put("item0", inventory.getItem(0).copy().serializeNBT());
        compoundNBT.put("item1", inventory.getItem(1).copy().serializeNBT());
        return super.save(compoundNBT);
    }
}
