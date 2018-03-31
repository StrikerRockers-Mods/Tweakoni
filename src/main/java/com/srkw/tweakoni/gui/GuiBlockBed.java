package com.srkw.tweakoni.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiBlockBed extends GuiScreen {

    GuiButton leftArrowButton;
    GuiButton middleButton;
    GuiButton rightArrowButton;

    private int LEFT = -1;
    private int MIDDLE = 0;
    private int RIGHT = 1;

    private int arrowWidth = 20;
    private int arrowHeight = 20;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    public void initGui() {

        int centerX = (width / 2) - ((arrowWidth) / 2);
        int centerY = (height / 2) - ((arrowHeight) / 2);

        buttonList.add(leftArrowButton = new GuiButton(LEFT, -10, 45, arrowHeight, arrowWidth, ">"));
        buttonList.add(rightArrowButton = new GuiButton(RIGHT, 10, 45, arrowHeight, arrowWidth, "<"));
        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        //switch(button.id) {


        //}

        super.actionPerformed(button);

    }

    @Override
    public boolean doesGuiPauseGame() {

        return false;

    }

}
