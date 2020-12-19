package net.narwell.survivalhc;

import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.database.SQLDatabase;
import net.narwell.survivalhc.database.datahandler.UserDataHandler;
import net.narwell.survivalhc.database.manager.MySQLDatabase;
import net.narwell.survivalhc.database.manager.SQLiteDatabase;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Survival extends JavaPlugin {

    private FileCreator conf;
    private FileCreator lang;
    private FileCreator scoreboard;
    private SQLDatabase sqlDatabase;
    private UserDataHandler userDataHandler;

    @Override
    public void onEnable() {
        conf = new FileCreator(this, "config.yml");
        lang = new FileCreator(this, "lang.yml");
        scoreboard = new FileCreator(this, "scoreboard.yml");

        initDatabase();

        userDataHandler = new UserDataHandler(this);
    }

    @Override
    public void onDisable() {
        sqlDatabase.close();
    }

    private void initDatabase() {

        switch (getConfig().getString("Storage")) {
            case "MySQL":
                sqlDatabase = new MySQLDatabase(this);
                break;

            case "SQLite":
                sqlDatabase = new SQLiteDatabase(this);
                break;

            default:
                getLogger().log(Level.WARNING, "Storage type not found, please try existing one");
        }

    }

    public FileCreator getConf() {
        return conf;
    }

    public FileCreator getLang() {
        return lang;
    }

    public FileCreator getScoreboard() {
        return scoreboard;
    }

    public SQLDatabase getSqlDatabase() {
        return sqlDatabase;
    }

}
