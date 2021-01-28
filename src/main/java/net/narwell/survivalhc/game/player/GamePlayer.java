package net.narwell.survivalhc.game.player;

import net.narwell.survivalhc.Survival;

import java.util.UUID;

public class GamePlayer {

    private boolean wasTeleported;
    private int kills;
    private int deaths;
    private int time;

    public GamePlayer(final Survival main, UUID uuid) {
        wasTeleported = main.getUserDataHandler().read("WasTeleported", uuid);
        kills = main.getUserDataHandler().read(uuid, "Kills");
        deaths = main.getUserDataHandler().read(uuid, "Deaths");
        time = main.getUserDataHandler().read(uuid, "Time");
    }

    public void setWasTeleported(boolean wasTeleported) {
        this.wasTeleported = wasTeleported;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public void addDeaths(int deaths) {
        this.deaths += deaths;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public boolean isWasTeleported() {
        return wasTeleported;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTime() {
        return time;
    }

}
