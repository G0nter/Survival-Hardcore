package net.narwell.survivalhc.database.datahandler;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.database.SQLDatabase;
import net.narwell.survivalhc.database.manager.MySQLDatabase;
import net.narwell.survivalhc.database.manager.SQLiteDatabase;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class UserDataHandler {

    private final Survival main;
    private SQLDatabase sqlDatabase;

    public UserDataHandler(final Survival main) {
        this.main = main;

        switch (main.getConf().getString("Storage")) {
            case "MySQL":
                sqlDatabase = new MySQLDatabase(main);
                break;

            case "SQLite":
                sqlDatabase = new SQLiteDatabase(main);
                break;

            default:
                main.getLogger().log(Level.WARNING, "Storage type not found, please try existing one");
                Bukkit.getPluginManager().disablePlugin(main);
        }

    }

    public void create(UUID uuid) {
        try {
            if (!exist(uuid)) {
                PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("INSERT INTO `SurvivalHC_players` (`UUID`, `WasTeleported`, `Kills`, `Deaths`, `Time`) VALUES (?, ?, ?, ?, ?);");

                statement.setString(1, uuid.toString());
                statement.setBoolean(2, false);
                statement.setInt(3, 0);
                statement.setInt(4, 0);
                statement.setInt(5, 0);
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean read(String searchString, UUID uuid) {
        try {

            PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("SELECT * FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                return rs.getBoolean(searchString);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public int read(UUID uuid, String read) {
        try {

            PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("SELECT * FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                return rs.getInt(read);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    public void update(UUID uuid) {
        try {
            PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("UPDATE `SurvivalHC_players` SET `WasTeleported` = ?, `Kills` = ?, `Deaths` = ?, `Time` = ? WHERE `UUID` = ?;");
            statement.setString(5, String.valueOf(uuid));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean exist(UUID uuid) throws SQLException {
        PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("SELECT `UUID` FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
        ResultSet rs = statement.getResultSet();

        while (rs.next()) {
            return true;
        }

        return false;
    }

    public SQLDatabase getSqlDatabase() {
        return sqlDatabase;
    }

}
