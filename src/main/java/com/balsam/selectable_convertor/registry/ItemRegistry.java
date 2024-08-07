package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.config.Constants;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static final RegistryObject<BlockItem> ArtransferBlock = ITEMS.register("artransfer_block", () ->
            new BlockItem(BlockRegistry.ArtransferBlock.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
}

