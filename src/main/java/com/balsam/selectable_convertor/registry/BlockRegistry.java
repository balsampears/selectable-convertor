package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.block.*;
import com.balsam.selectable_convertor.config.Constants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final RegistryObject<ArtransferBlock> ArtransferBlock = BLOCKS.register("artransfer_block", ArtransferBlock::new);
}
