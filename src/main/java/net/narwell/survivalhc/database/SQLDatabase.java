package net.narwell.survivalhc.database;

import net.narwell.survivalhc.Survival;

public interface SQLDatabase {

    void init(Survival main);
    void create();
    void close();
    

}
