package backend.interfaces;

import backend.Tile;
import backend.actors.*;

/**
 * Created by ryan on 9/24/15.
 */
public interface Display
{
    void render(Tile[][] playerScreen, Player player);
}
