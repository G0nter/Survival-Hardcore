package net.narwell.survivalhc.database;

import net.narwell.survivalhc.Survival;

public interface Database {

    void init(Survival main);
    void close();

}
