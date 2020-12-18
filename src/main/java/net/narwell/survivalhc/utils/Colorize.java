/*
 * Colorize
 *
 * 1.0-SNAPSHOT
 *
 * 17/12/2020
 *
 * Copyright All rights reserved
 */

package net.narwell.survivalhc.utils;

import org.bukkit.ChatColor;

public class Colorize {

    /**
     * The method takes care of translating alternate color codes
     * and returning it as String
     */

    public static String set(String textToColorize) {
        return ChatColor.translateAlternateColorCodes('&', textToColorize);
    }

}
