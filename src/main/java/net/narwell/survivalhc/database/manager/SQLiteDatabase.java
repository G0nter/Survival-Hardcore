package net.narwell.survivalhc.database.manager;

import net.narwell.survivalhc.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteDatabase implements Database {

    @Override
    public void open() {

    }

    @Override
    public void makeTable() {


    }

    @Override
    public void close(Connection conn, PreparedStatement ps, ResultSet res) {

    }

    @Override
    public void closePool() {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
