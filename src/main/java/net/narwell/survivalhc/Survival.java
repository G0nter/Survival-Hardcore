/*
 * Survival
 *
 * 1.0-SNAPSHOT
 *
 * 17/12/2020
 *
 * Copyright All rights reserved
 */

package net.narwell.survivalhc;

import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.database.Database;
import net.narwell.survivalhc.database.manager.MySQLDatabase;
import net.narwell.survivalhc.database.manager.SQLiteDatabase;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin {

    private FileCreator conf;
    private FileCreator lang;
    private Database database;

    @Override
    public void onEnable() {
        conf = new FileCreator(this, "config.yml");
        lang = new FileCreator(this, "lang.yml");
        loadDatabase();
    }

    @Override
    public void onDisable() {
        database.close();
    }

    private void loadDatabase() {

        switch (getConfig().getString("Storage")) {
            case "MySQL":
                database = new MySQLDatabase(this);
                break;
            case "SQLite":
                database = new SQLiteDatabase(this);
                break;
        }
    }

    public FileCreator getConf() {
        return conf;
    }

    public FileCreator getLang() {
        return lang;
    }

}
