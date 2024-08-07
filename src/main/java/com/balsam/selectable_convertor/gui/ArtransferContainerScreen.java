package com.balsam.selectable_convertor.gui;

import com.balsam.selectable_convertor.config.Constants;
import com.balsam.selectable_convertor.container.ArtransferContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ArtransferContainerScreen extends ContainerScreen<ArtransferContainer> {
    private static final ResourceLocation BACKGROUND_RESOURCE = new ResourceLocation(Constants.MOD_ID, "textures/gui/artransfer.png");
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    public ArtransferContainerScreen(ArtransferContainer container, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(container, playerInventory, textComponent);
        this.imageWidth = TEXTURE_WIDTH;
        this.imageHeight = TEXTURE_HEIGHT;
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(BACKGROUND_RESOURCE);
        int x = (this.width - this.imageWidth)/2;
        int y = (this.height - this.imageHeight)/2;
        blit(matrixStack, x,y,0,0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }
}
