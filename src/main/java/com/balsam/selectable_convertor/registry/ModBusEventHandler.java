package com.balsam.selectable_convertor.registry;

import com.balsam.selectable_convertor.gui.ArtransferContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.register(ContainerTypeRegistry.ArtransferContainer.get(), ArtransferContainerScreen::new);
        });
    }

//    @SubscribeEvent
//    public static void onCommonnSetupEvent(FMLCommonSetupEvent event){
//        event.enqueueWork(()->{
//            ModRecipeType.ARTRANSFER = IRecipeType.register("artransfer_recipe");
//        });
//    }
}
