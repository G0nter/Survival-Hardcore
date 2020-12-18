package net.narwell.survivalhc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    void open();
    void makeTable();
    void close(Connection conn, PreparedStatement ps, ResultSet res);
    void closePool();
    Connection getConnection() throws SQLException;

}
