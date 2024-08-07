package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.config.Constants;
import com.balsam.selectable_convertor.tileEntity.ArtransferTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegistry{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);

    public static final RegistryObject<TileEntityType<ArtransferTileEntity>> ArtransferTileEntity = TILE_ENTITIES.register("artransfer_tile_entity",
             () -> TileEntityType.Builder.of(ArtransferTileEntity::new, BlockRegistry.ArtransferBlock.get()).build(null));

}
