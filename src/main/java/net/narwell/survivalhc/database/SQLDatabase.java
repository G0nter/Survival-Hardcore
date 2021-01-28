package net.narwell.survivalhc.database;

import net.narwell.survivalhc.Survival;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLDatabase {

    void init(Survival main);

    void create();

    void close() throws SQLException;

    Connection getConnection() throws SQLException;

}
