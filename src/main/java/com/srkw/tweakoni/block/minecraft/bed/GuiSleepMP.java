package com.srkw.tweakoni.block.minecraft.bed;

import com.srkw.tweakoni.network.PacketHandler;
import com.srkw.tweakoni.network.PacketSleepSpawn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiSleepMP extends GuiChat {

    GuiButton removeSpawn;
    boolean keepSpawn = true;
    BlockPos spawnPoint;

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 40, I18n.format("multiplayer.stopSleeping")));
        this.buttonList.add(removeSpawn = new GuiButton(2, this.width / 2 - 100, this.height - 60, I18n.format("spawn.remove")));
        spawnPoint = Minecraft.getMinecraft().player.getBedLocation();
    }

   @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (Minecraft.getMinecraft().player.getSleepTimer() >= 90 && !keepSpawn) {
           PacketHandler.INSTANCE.sendToServer(new PacketSleepSpawn(spawnPoint, 0));
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
   }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.wakeFromSleep();
        } else if (keyCode != 28 && keyCode != 156) {
            super.keyTyped(typedChar, keyCode);
        } else {
            String s = this.inputField.getText().trim();

            if (!s.isEmpty()) {
                this.sendChatMessage(s);
            }

            this.inputField.setText("");
            this.mc.ingameGUI.getChatGUI().resetScroll();
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            this.wakeFromSleep();
        }
        if (button.id == 2) {
            removeSpawn.enabled = false;
            keepSpawn = false;
        } else {
            super.actionPerformed(button);
        }
    }

    private void wakeFromSleep() {
        NetHandlerPlayClient nethandlerplayclient = this.mc.player.connection;
        nethandlerplayclient.sendPacket(new CPacketEntityAction(this.mc.player, CPacketEntityAction.Action.STOP_SLEEPING));
    }
}
