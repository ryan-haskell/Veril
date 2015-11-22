package backend.actors;

import backend.Global;

/**
 * Created by ryan on 9/24/15.
 */
public class Player extends Actor
{
    public Player()
    {
        super(Global.PLAYER_INIT_X, Global.PLAYER_INIT_Y);
    }
    
    public String getImage()
    {
        return Global.IMG_DIR + "actors/veril/" + 
        		dir.name().toLowerCase()+".png";
    }
}
