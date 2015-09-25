package backend.interfaces;

import backend.Game;

/**
 * Created by ryan on 9/24/15.
 */
public interface Input
{
    void setGame(Game game);
    void enableInput();
    void disableInput();
}
