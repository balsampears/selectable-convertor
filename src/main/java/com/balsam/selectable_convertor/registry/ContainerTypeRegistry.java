package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.config.Constants;
import com.balsam.selectable_convertor.container.ArtransferContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    public static final RegistryObject<ContainerType<ArtransferContainer>> ArtransferContainer = CONTAINERS.register("artransfer_container",
        () -> IForgeContainerType.create((windowId, inv, data) ->
                new ArtransferContainer(windowId, Minecraft.getInstance().level, data.readBlockPos(), inv)));

}
