package net.thejeezed.craftplusplus.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MessageRenderer
{
    public static void renderMessage(String message)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.gui.getChat().addMessage(Component.nullToEmpty(message));
    }
}
