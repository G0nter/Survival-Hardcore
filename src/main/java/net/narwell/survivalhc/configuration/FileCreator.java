package net.narwell.survivalhc.configuration;

import net.narwell.survivalhc.utils.Colorize;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;



public class FileCreator extends YamlConfiguration{

    private final String fileName;
    private final File file;

    public FileCreator(final Plugin plugin, final File fileFolder, final String fileName) {
        this.fileName = fileName;
        this.file = new File(fileFolder, fileName);
        createFile(plugin);
    }

    public FileCreator(final Plugin plugin, final String fileName) {
        this(plugin, plugin.getDataFolder(), fileName);
    }

    public void createFile(final Plugin plugin) {
        try {

            if (!file.exists()) {
                if (plugin.getResource(fileName) != null) {
                    plugin.saveResource(fileName, false);
                } else {
                    save(file);
                }

                load(file);
                plugin.getLogger().log(Level.INFO, file.toString());
                return;
            }

            load(file);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String get(String string) {
        if (super.getString(string) == null) return "path no found: " + string;

        return Colorize.set(super.getString(string).replace("<prefix>", super.getString("prefix")));
    }

    public void save() {
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
