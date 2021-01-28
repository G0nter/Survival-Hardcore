package net.narwell.survivalhc.database.manager;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.database.SQLDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class SQLiteDatabase implements SQLDatabase {

    private Connection connection;

    public SQLiteDatabase(final Survival main) {
        init(main);
        create();
    }

    @Override
    public void init(Survival main) {

        try {
            File dataFile = new File(main.getDataFolder(), "SurvivalHC.db");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                main.getLogger().log(Level.INFO, dataFile.toString());
            }

            //if (!(connection != null && !connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + dataFile);
                main.getLogger().log(Level.INFO, "Plugin connected to SQLite");
            //}

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create() {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `SurvivalHC_players` (`UUID` VARCHAR(36), `WasTeleported` VARCHAR(16), `Kills` INT, `Deaths` INT, `Time` INT)");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
