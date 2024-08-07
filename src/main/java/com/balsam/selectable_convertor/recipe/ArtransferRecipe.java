package com.balsam.selectable_convertor.recipe;

import com.balsam.selectable_convertor.config.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ArtransferRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final List<Ingredient> recipeItems;
    private final List<ItemStack> outputs;

    public ArtransferRecipe(ResourceLocation id, List<Ingredient> recipeItems, List<ItemStack> outputs) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.outputs = outputs;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        ItemStack item = inventory.getItem(0);
        ItemStack item2 = inventory.getItem(1);
        return !item.isEmpty() && !item2.isEmpty()
                && matchOneRecipeItems(item)
                && matchOneRecipeItems(item2);
    }

    private boolean matchOneRecipeItems(ItemStack item){
        boolean hasMatchOne = false;
        for (int i = 0; i < recipeItems.size(); i++) {
            hasMatchOne = hasMatchOne || recipeItems.get(i).test(item);
        }
        return hasMatchOne;
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        return outputs.get(0);
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return outputs.get(0);
    }

    public List<ItemStack> getResultItems() {
        return outputs;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipeType.ARTRANSFER;
    }

    public static class Serializer implements IRecipeSerializer<ArtransferRecipe>{

        public static final Serializer INSTANCE = new Serializer();
        private static final ResourceLocation RECIPE_RESOURCE = new ResourceLocation(Constants.MOD_ID, "artransfer");

        @Override
        public ArtransferRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            JsonArray allCombines = jsonObject.getAsJsonArray("ingredients");

            ArrayList<Ingredient> ingredients = new ArrayList();
            ArrayList<ItemStack> outputs = new ArrayList<>();
            for (JsonElement element : allCombines) {
                Ingredient ingredient = Ingredient.fromJson(element);
                ingredients.add(ingredient);
                ItemStack itemStack = ShapedRecipe.itemFromJson(element.getAsJsonObject());
                outputs.add(itemStack);
            }
            return new ArtransferRecipe(resourceLocation, ingredients, outputs);
        }

        @Nullable
        @Override
        public ArtransferRecipe fromNetwork(ResourceLocation resourceLocation, PacketBuffer packetBuffer) {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < packetBuffer.readInt(); i++) {
                ingredients.add(Ingredient.fromNetwork(packetBuffer));
            }
            ArrayList<ItemStack> itemStacks = new ArrayList<>();
            for (int i = 0; i < packetBuffer.readInt(); i++) {
                itemStacks.add(packetBuffer.readItem());
            }
            return new ArtransferRecipe(resourceLocation, ingredients, itemStacks);
        }

        @Override
        public void toNetwork(PacketBuffer packetBuffer, ArtransferRecipe artransferRecipe) {
            packetBuffer.writeInt(artransferRecipe.getIngredients().size());
            for (Ingredient ingredient : artransferRecipe.getIngredients()) {
                ingredient.toNetwork(packetBuffer);
            }
            packetBuffer.writeInt(artransferRecipe.getResultItems().size());
            for (ItemStack resultItem : artransferRecipe.getResultItems()) {
                packetBuffer.writeItemStack(resultItem, false);
            }
        }

        @Override
        public IRecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return RECIPE_RESOURCE;
        }

        @Override
        public Class<IRecipeSerializer<?>> getRegistryType() {
            //先强制转为object，再强制转换对应类型
            return (Class<IRecipeSerializer<?>>)(Object)IRecipeSerializer.class;
        }
    }

}