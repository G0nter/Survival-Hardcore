package net.narwell.survivalhc;

import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.database.datahandler.UserDataHandler;
import net.narwell.survivalhc.manager.InitializerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Survival extends JavaPlugin {

    private FileCreator conf;
    private FileCreator lang;
    private FileCreator scoreboard;
    private UserDataHandler userDataHandler;
    private InitializerManager initManager;

    @Override
    public void onEnable() {
        conf = new FileCreator(this, "config.yml");
        lang = new FileCreator(this, "lang.yml");
        scoreboard = new FileCreator(this, "scoreboard.yml");
        userDataHandler = new UserDataHandler(this);
        initManager = new InitializerManager(this);

    }

    @Override
    public void onDisable() {
        try {
            userDataHandler.getSqlDatabase().close();
        } catch (SQLException exception) {
            exception.printStackTrace();
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

    public UserDataHandler getUserDataHandler() {
        return userDataHandler;
    }
}
