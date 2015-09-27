package backend.interfaces;

import backend.Game;
import backend.Global;
import backend.Tile;
import backend.actors.*;

/**
 * Display - Abstracts what the user sees in-game
 * Created by ryan on 9/24/15.
 */
public interface Display
{
    void setGame(Game game);
    void render(Tile[][] playerScreen, Player player);
    void slide(Global.Direction dir);
    void updatePlayer(Player player);
}
