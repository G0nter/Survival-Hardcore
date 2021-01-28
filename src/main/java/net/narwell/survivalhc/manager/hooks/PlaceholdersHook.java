package net.narwell.survivalhc.manager.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholdersHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "shc";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Gonter";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) return "error";


        return "";
    }

}
