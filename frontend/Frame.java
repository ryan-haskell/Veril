package frontend;

import backend.interfaces.Display;
import backend.Global;
import backend.Tile;
import backend.actors.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ryan on 9/24/15.
 */
public class Frame extends JFrame implements Display
{
    private static final int    SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width,
                                SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height,

                                FRAME_WIDTH = 800,
                                FRAME_HEIGHT = 600,
                                FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH)/2,
                                FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT)/2;

    private BufferedImage canvas;
    private Graphics graphics;


    public Frame(Keyboard kb)
    {
        this.initFrame();
        this.addKeyListener(kb);
        this.initCanvas();
    }

    private void initFrame()
    {
        this.setTitle(Global.GAME_TITLE);
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setUndecorated(false);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private void initCanvas()
    {
        if(canvas == null)
            canvas = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        graphics = canvas.getGraphics();
    }

    public void paint(Graphics g)
    {
        g.drawImage(canvas, 0,0,null);
    }

    public void update()
    {
        this.repaint();
    }

    private BufferedImage getImage(String filename)
    {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
        }
        catch (IOException e) {
            e.printStackTrace();
            image = new BufferedImage(Global.TILE_WIDTH, Global.TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        }
        return image;
    }

    //  Display implementation
    public void render(Tile[][] playerScreen, Player player)
    {
        int rows = playerScreen.length;
        int cols = playerScreen[0].length;

        for(int y = 0; y < rows; y++)
            for(int x = 0; x < cols; x++)
            {
                graphics.drawImage(
                        this.getImage(playerScreen[y][x].getImage()),
                        x * Global.TILE_WIDTH,
                        y * Global.TILE_HEIGHT,
                        null
                );
            }

        graphics.drawImage(
                this.getImage(player.getImage()),
                (Global.VIEW_WIDTH/2)*Global.TILE_WIDTH,
                (Global.VIEW_HEIGHT/2)*Global.TILE_HEIGHT,
                null
        );

        this.update();
    }
}
