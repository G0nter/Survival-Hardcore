package net.narwell.survivalhc.database.manager;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.database.Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class SQLiteDatabase implements Database {

    private Connection connection;

    public SQLiteDatabase(final Survival main) {
        init(main);
    }

    @Override
    public void init(Survival main) {

        try {
            File dataFile = new File(main.getDataFolder(), "SurvivalHC.db");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                main.getLogger().log(Level.INFO, dataFile.toString());
            }

            if (connection != null && !connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + dataFile);
                main.getLogger().log(Level.INFO, "Plugin conectado a SQLite");
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
