package net.narwell.survivalhc.database.datahandler;

import net.narwell.survivalhc.Survival;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDataHandler {

    private final Survival main;

    public UserDataHandler(final Survival main) {
        this.main = main;
    }

    public void create(UUID uuid) {
        try {
            if (!exist(uuid)) {
                PreparedStatement statement = main.getSqlDatabase().getConnection().prepareStatement("INSERT INTO `SurvivalHC_players` (`UUID`, `WasTeleported`, `Kills`, `Deaths`, `Time`) VALUES (?, ?, ?, ?, ?);");

                statement.setString(1, uuid.toString());
                statement.setBoolean(2, false);
                statement.setInt(3, 0);
                statement.setInt(4, 0);
                statement.setInt(5, 0);
                statement.executeUpdate();
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean read(String searchString, UUID uuid) {
        try {

            PreparedStatement statement = main.getSqlDatabase().getConnection().prepareStatement("SELECT * FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
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

            PreparedStatement statement = main.getSqlDatabase().getConnection().prepareStatement("SELECT * FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
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
            PreparedStatement statement = main.getSqlDatabase().getConnection().prepareStatement("UPDATE `SurvivalHC_players` SET `WasTeleported` = ?, `Kills` = ?, `Deaths` = ?, `Time` = ? WHERE `UUID` = ?;");

            //statement.setBoolean(1, gamePlayer.getIsTeleport());
            //statement.setInt(2, gamePlayer.getKills());
            //statement.setInt(3, gamePlayer.getDeaths());
            //statement.setInt(4, gamePlayer.getTime());
            statement.setString(5, String.valueOf(uuid));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean exist(UUID uuid) throws SQLException {
        PreparedStatement statement = main.getSqlDatabase().getConnection().prepareStatement("SELECT `UUID` FROM `SurvivalHC_players` WHERE `UUID` ='" + uuid + "'");
        ResultSet rs = statement.getResultSet();

        while (rs.next()) {
            return true;
        }

        return false;
    }

}
