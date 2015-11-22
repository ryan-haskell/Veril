package backend.interfaces;

import backend.Game;

/**
 * Input - Abstracts user input to game
 * Created by ryan on 9/24/15.
 */
public interface Input
{
    void setGame(Game game);
    void enableInput();
    void disableInput();
    Game.Command getNextCommand();
}
