package net.narwell.survivalhc.game.menu;

import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ArenaCreatorGui {

    private Inventory inv;

    public ArenaCreatorGui(String arenaMode) {

        inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Creación de la arena " + arenaMode);
        initializeGuiItem();

    }

    public void openGui(Player player) {
        player.openInventory(inv);
    }

    private void initializeGuiItem() {
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

        inv.setItem(0, filler);
        inv.setItem(1, filler);
        inv.setItem(2, filler);
        inv.setItem(3, filler);
        inv.setItem(4, filler);
        inv.setItem(5, filler);
        inv.setItem(6, filler);
        inv.setItem(7, filler);
        inv.setItem(8, filler);
        inv.setItem(9, filler);
        inv.setItem(11, filler);
        inv.setItem(12, filler);
        inv.setItem(14, filler);
        inv.setItem(15, filler);
        inv.setItem(17, filler);
        inv.setItem(18, filler);
        inv.setItem(19, filler);
        inv.setItem(20, filler);
        inv.setItem(21, filler);
        inv.setItem(22, filler);
        inv.setItem(23, filler);
        inv.setItem(24, filler);
        inv.setItem(25, filler);
        inv.setItem(26, filler);


        inv.setItem(10, createGuiItem(Material.ANVIL, Colorize.set("&eRandomTP"), Colorize.set("&fAlterna el tp a coordenadas aleatorias")));
        inv.setItem(13, createGuiItem(Material.ANVIL, Colorize.set("&fLimite de la arena"), Colorize.set("&cXDD3")));
        inv.setItem(16, createGuiItem(Material.ANVIL, Colorize.set("&fModo: &cXD3"), Colorize.set("&cXD2D")));

    }

    private ItemStack createGuiItem(final Material material, final String name, final String... lore) {

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
}
