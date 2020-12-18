package net.narwell.survivalhc.game.manager;

import net.narwell.survivalhc.game.Game;
import net.narwell.survivalhc.game.player.GamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameSolo implements Game {

    private String gameState;
    private int gameLimit;
    private int playersPlaying;

    private Map<UUID, GamePlayer> playersInGame = new HashMap<>();

    public GameSolo() {

    }


    @Override
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    @Override
    public void setGameLimit(int gameLimit) {
        this.gameLimit = gameLimit;
    }

    @Override
    public void setPlayersPlaying(int playersPlaying) {
        this.playersPlaying = playersPlaying;
    }

    @Override
    public String getGameState() {
        return gameState;
    }

    @Override
    public int getGameLimit() {
        return gameLimit;
    }

    @Override
    public int getPlayersPlaying() {
        return playersPlaying;
    }

}
