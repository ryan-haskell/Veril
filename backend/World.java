package backend;

import backend.actors.Player;

/**
 * Created by ryan on 9/24/15.
 */
public class World
{
    private Tile[][] tiles;
    private Player player;

    public World()
    {
        this.initTiles();
        this.generateRandomWorld();
        this.initPlayer();
    }

    private void initTiles()
    {
        int ww = Global.WORLD_WIDTH,
            wh = Global.WORLD_HEIGHT;

        tiles = new Tile[wh][ww];

        for(int y = 0; y < wh; y++)
            for(int x = 0; x < ww; x++)
            {
                tiles[y][x] = new Tile();
            }
    }

    public void generateRandomWorld()
    {
        for(Tile[] ts : tiles)
            for(Tile t : ts)
            {
                int num_tiles = Tile.Type.values().length;
                int rand = (int)(Math.random()*num_tiles);
                t.setType(Tile.Type.values()[rand]);
            }
    }

    private void initPlayer()
    {
        player = new Player();
    }

    public Tile[][] getPlayerView()
    {
        int px = player.getX(),
            py = player.getY();

        int vw = Global.VIEW_WIDTH,
            vh = Global.VIEW_HEIGHT,
            vp = Global.VIEW_PADDING,
            ww = Global.WORLD_WIDTH,
            wh = Global.WORLD_HEIGHT;

        Tile[][] viewableTiles = new Tile[vh][vw];

        for(int y = 0; y < vh; y++)
            for(int x = 0; x < vw; x++)
            {
                int ty = (py - (vh/2)%wh + y + wh)%wh;
                int tx = (px - (vw/2)%ww + x + ww)%ww;

                viewableTiles[y][x] = tiles[ty][tx];
            }

        return viewableTiles;
    }

    public Player getPlayer()
    {
        return player;
    }

    public boolean movePlayer(Global.Direction dir)
    {
        Tile adjacentTile = getTileInDirection(dir,player.getX(), player.getY());

        boolean canMove = player.canMove(adjacentTile);

        if(canMove)
            player.move(dir);

        return canMove;
    }

    public Tile getTileInDirection(Global.Direction dir, int x, int y)
    {
        switch (dir)
        {
            case UP:
                return tiles[(y - 1 + Global.WORLD_HEIGHT) % Global.WORLD_HEIGHT][x];
            case DOWN:
                return tiles[(y + 1 + Global.WORLD_HEIGHT) % Global.WORLD_HEIGHT][x];
            case LEFT:
                return tiles[y][(x - 1 + Global.WORLD_WIDTH) % Global.WORLD_WIDTH];
            case RIGHT:
                return tiles[y][(x + 1 + Global.WORLD_WIDTH) % Global.WORLD_WIDTH];
        }

        return null;
    }

}