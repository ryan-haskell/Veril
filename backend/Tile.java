package backend;

/**
 * Created by ryan on 9/24/15.
 */
public class Tile
{
    public enum Type {
        GRASS,
        WATER,
        FLOWER,
        TREE
    }

    private Type type;

    public Tile()
    {
        type = Type.GRASS;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type.ordinal();
    }

    public String getTypeName()
    {
        return type.name();
    }

    public boolean isWalkable()
    {
        switch (type)
        {
            case GRASS:
            case FLOWER:
                return true;
            default:
                return false;
        }
    }


    public boolean isSwimmable()
    {
        switch (type)
        {
            case WATER:
                return true;
            default:
                return false;
        }
    }

    public String getImage()
    {
        return "images/tiles/" + type.ordinal() + ".png";
    }
}
