package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.config.Constants;
import com.balsam.selectable_convertor.recipe.ArtransferRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeRegistry {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> ArtransferSerializer = RECIPE_SERIALIZER.register("artransfer_recipe",
            () -> ArtransferRecipe.Serializer.INSTANCE);
}
