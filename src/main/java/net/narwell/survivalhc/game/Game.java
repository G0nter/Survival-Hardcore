package net.narwell.survivalhc.game;

public interface Game {

    void setGameState(String gameState);

    void setGameLimit(int gameLimit);

    void setPlayersPlaying(int playersPlaying);

    String getGameState();

    int getGameLimit();

    int getPlayersPlaying();

}
