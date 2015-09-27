package backend;

/**
 * Global - holds all the shared variables and functions
 * Created by ryan on 9/24/15.
 */
public class Global
{
    //  GAME
    public static final String GAME_TITLE = "Veril";

    //  VIEW
    public static final int VIEW_WIDTH = 16,
                            VIEW_HEIGHT = 9,
                            VIEW_PADDING = 1;

    //  WORLD
    public static final int WORLD_WIDTH = 16,
                            WORLD_HEIGHT = 9;

    //  TILE
    public static final int TILE_SIZE = 50;

    //  PLAYER
    public static final int PLAYER_INIT_X = (VIEW_WIDTH / 2)%WORLD_WIDTH,
                            PLAYER_INIT_Y = (VIEW_HEIGHT / 2)%WORLD_HEIGHT;

    //  DIRECTIONS
    public enum Direction{
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public static final int NUM_DIRS = Direction.values().length;


}