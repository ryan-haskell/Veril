package backend.actors;

import backend.Global;
import backend.Tile;

/**
 * Created by ryan on 9/24/15.
 */
public class Actor
{
    protected enum MoveType{
        LAND,
        SEA,
        AIR
    }

    protected int x, y;
    protected MoveType moveType;
    protected Global.Direction dir;

    public Actor(int x, int y)
    {
        this.setLocation(x,y);
        this.dir = Global.Direction.DOWN;
        this.moveType = MoveType.LAND;
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean canMove(Tile adjacentTile)
    {
        switch (moveType)
        {
            case LAND:
                return adjacentTile.isWalkable();
            case SEA:
                return adjacentTile.isSwimmable();
            default:
                return true;
        }
    }

    public void move(Global.Direction dir)
    {
        switch (dir)
        {
            case UP:
                y = (y - 1 + Global.WORLD_HEIGHT) % Global.WORLD_HEIGHT;
                break;
            case DOWN:
                y = (y + 1 + Global.WORLD_HEIGHT) % Global.WORLD_HEIGHT;
                break;
            case RIGHT:
                x = (x + 1 + Global.WORLD_WIDTH) % Global.WORLD_WIDTH;
                break;
            case LEFT:
                x = (x - 1 + Global.WORLD_WIDTH) % Global.WORLD_WIDTH;
                break;
        }

        this.dir = dir;
    }
}
