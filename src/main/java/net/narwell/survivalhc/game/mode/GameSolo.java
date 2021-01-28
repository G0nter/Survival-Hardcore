package net.narwell.survivalhc.game.mode;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.game.Game;
import net.narwell.survivalhc.game.player.GamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameSolo implements Game {

    private final Survival main;

    private String gameState;
    private int gameLimit;
    private int playersPlaying;

    private Map<UUID, GamePlayer> playersInGame = new HashMap<>();

    public GameSolo(final Survival main, final FileCreator arena) {
        this.main = main;
        gameState = arena.getString("Configuration.state");
        gameLimit = arena.getInt("Configuration.limit");
        playersPlaying = arena.getInt("Configuration.playing");
    }

    @Override
    public void setPlayerGame(UUID uuid) {
        playersInGame.put(uuid, new GamePlayer(main, uuid));
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
    public GamePlayer getGamePlayer(UUID uuid) {
        return playersInGame.get(uuid);
    }

    @Override
    public Map<UUID, GamePlayer> getPlayersInGame() {
        return playersInGame;
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
