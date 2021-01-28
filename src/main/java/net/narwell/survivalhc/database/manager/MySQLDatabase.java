package net.narwell.survivalhc.database.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.database.SQLDatabase;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLDatabase implements SQLDatabase {

    private HikariDataSource dataSource;

    public MySQLDatabase(final Survival main) {
        init(main);
        create();
    }

    @Override
    public void init(Survival main) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        HikariConfig hikari = new HikariConfig();

        hikari.setJdbcUrl("jdbc:mysql://" + main.getConfig().getString("data.address") + ":" + main.getConfig().getString("data.port") + "/" + main.getConfig().getString("data.database"));
        hikari.setDriverClassName("com.mysql.jdbc.Driver");
        hikari.setUsername(main.getConfig().getString("data.username"));
        hikari.setPassword(main.getConfig().getString("data.password"));
        hikari.setMinimumIdle(main.getConfig().getInt("data.pool-settings.minimum-idle"));
        hikari.setMaximumPoolSize(main.getConfig().getInt("data.pool-settings.maximum-pool-size"));
        hikari.setConnectionTimeout(main.getConfig().getInt("data.pool-settings.connection-timeout"));
        //hikari.setConnectionTestQuery(testQuery);

        try {
            dataSource = new HikariDataSource(hikari);
            main.getLogger().log(Level.INFO, "Plugin connected to MySQL");
        }catch (Exception e) {
            main.getLogger().log(Level.WARNING, "A problem has occurred with the plugin, I have been deactivated");
            Bukkit.getPluginManager().disablePlugin(main);
        }

    }

    @Override
    public void create() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `SurvivalHC_players` (`UUID` VARCHAR(36), `WasTeleported` VARCHAR(16), `Kills` INT, `Deaths` INT, `Time` INT)");

            ps.executeUpdate();
        } catch (SQLException exeption) {
            exeption.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public void close() throws SQLException {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ignored) {}

        if (ps != null)
            try {
                ps.close();
            } catch (SQLException ignored) {}

        if (res != null)
            try {
                res.close();
            } catch (SQLException ignored) {}
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
