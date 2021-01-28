package net.narwell.survivalhc.game;

import net.narwell.survivalhc.game.player.GamePlayer;

import java.util.Map;
import java.util.UUID;

public interface Game {

    void setPlayerGame(UUID uuid);

    void setGameState(String gameState);

    void setGameLimit(int gameLimit);

    void setPlayersPlaying(int playersPlaying);

    GamePlayer getGamePlayer(UUID uuid);

    Map<UUID, GamePlayer> getPlayersInGame();

    String getGameState();

    int getGameLimit();

    int getPlayersPlaying();

}
