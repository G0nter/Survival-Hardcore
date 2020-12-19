package net.narwell.survivalhc.database.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.database.SQLDatabase;

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
        HikariConfig hikari = new HikariConfig();

        hikari.setJdbcUrl("jdbc:mysql://" + main.getConfig().getString("data.address") + ":" + main.getConfig().getString("data.port") + "/" + main.getConfig().getString("data.database"));
        hikari.setDriverClassName("com.mysql.jdbc.Driver");
        hikari.setUsername(main.getConfig().getString("data.username"));
        hikari.setPassword(main.getConfig().getString("data.password"));
        hikari.setMinimumIdle(main.getConfig().getInt("data.pool-settings.minimum-idle"));
        hikari.setMaximumPoolSize(main.getConfig().getInt("data.pool-settings.maximum-pool-size"));
        hikari.setConnectionTimeout(main.getConfig().getInt("data.pool-settings.connection-timeout"));
        //hikari.setConnectionTestQuery(testQuery);
        dataSource = new HikariDataSource(hikari);
        main.getLogger().log(Level.INFO, "Plugin conectado a MySQL");
    }

    @Override
    public void create() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `SurvivalHC_players` (`UUID` VARCHAR(36), `WasTeleported` VARCHAR(16), `Kills` INT, `Deaths` INT, `Time` INT)");

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public void close() {
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
