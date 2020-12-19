package net.narwell.survivalhc.utils;

import org.bukkit.ChatColor;

public class Colorize {
    
    public static String set(String textToColorize) {
        return ChatColor.translateAlternateColorCodes('&', textToColorize);
    }

}
