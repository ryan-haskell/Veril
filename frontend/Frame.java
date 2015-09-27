package frontend;

import backend.Game;
import backend.interfaces.Display;
import backend.Global;
import backend.Tile;
import backend.actors.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Frame - Implements Display interface with a JFrame and Paint AWT.
 * Created by ryan on 9/24/15.
 */
public class Frame extends JFrame implements Display, ActionListener
{
    private static final int    SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width,
                                SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height,

                                FRAME_WIDTH = Global.TILE_SIZE*Global.VIEW_WIDTH,
                                FRAME_HEIGHT = Global.TILE_SIZE*Global.VIEW_HEIGHT,
                                FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH)/2,
                                FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT)/2,

                                CANVAS_WIDTH = Global.TILE_SIZE*(Global.VIEW_WIDTH + (2*Global.VIEW_PADDING)),
                                CANVAS_HEIGHT = Global.TILE_SIZE*(Global.VIEW_HEIGHT + (2*Global.VIEW_PADDING));

    //  Drawing
    private BufferedImage canvas, player;
    private Graphics canvasGraphics, playerGraphics;

    //  Sliding
    private Game game;
    private static final int TIMER_DELAY = 5;
    private static final int NUM_FRAMES = 50;
    private double numSlides;
    private Global.Direction slideDirection;
    private Timer timer;
    private int canvasOffsetX, canvasOffsetY;

    public Frame(Keyboard kb)
    {
        this.initFrame();
        this.addKeyListener(kb);
        this.initCanvas();
        this.initPlayerImage();
    }

    private void initFrame()
    {
        this.setTitle(Global.GAME_TITLE);
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setUndecorated(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    private void initCanvas()
    {
        canvas = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        canvasGraphics = canvas.getGraphics();
    }

    private void initPlayerImage()
    {
        player = new BufferedImage(Global.TILE_SIZE, Global.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        playerGraphics = player.getGraphics();
    }

    public void paint(Graphics g)
    {
        int canvasX = -Global.VIEW_PADDING * Global.TILE_SIZE;
        int canvasY = -Global.VIEW_PADDING * Global.TILE_SIZE;

        int playerX = (Global.VIEW_WIDTH/2)*Global.TILE_SIZE;
        int playerY = (Global.VIEW_HEIGHT/2)*Global.TILE_SIZE;

        BufferedImage buffer = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics bufferGraphics = buffer.getGraphics();

        bufferGraphics.drawImage(canvas, canvasX + canvasOffsetX, canvasY + canvasOffsetY, null);
        bufferGraphics.drawImage(player, playerX, playerY, null);

        g.drawImage(buffer, 0,0,null);
    }

    private BufferedImage getImage(String filename)
    {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
        }
        catch (IOException e) {
            e.printStackTrace();
            image = new BufferedImage(Global.TILE_SIZE, Global.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        }
        return image;
    }

    //  Display implementation
    public void render(Tile[][] playerScreen, Player player)
    {
        int rows = playerScreen.length;
        int cols = playerScreen[0].length;

        canvasOffsetX = 0;
        canvasOffsetY = 0;

        for(int y = 0; y < rows; y++)
            for(int x = 0; x < cols; x++)
            {
                canvasGraphics.drawImage(
                        this.getImage(playerScreen[y][x].getImage()),
                        x * Global.TILE_SIZE,
                        y * Global.TILE_SIZE,
                        null
                );
            }

        this.updatePlayer(player);

        this.repaint();
    }

    public void slide(Global.Direction dir)
    {
        slideDirection = dir;
        numSlides = 0;
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        numSlides++;

        switch(slideDirection)
        {
            case UP:
                canvasOffsetY = (int)(Global.TILE_SIZE * (numSlides/NUM_FRAMES));
                break;
            case DOWN:
                canvasOffsetY = (int)(-Global.TILE_SIZE * (numSlides/NUM_FRAMES));
                break;
            case LEFT:
                canvasOffsetX = (int)(Global.TILE_SIZE * (numSlides/NUM_FRAMES));
                break;
            case RIGHT:
                canvasOffsetX = (int)(-Global.TILE_SIZE * (numSlides/NUM_FRAMES));
                break;
        }

        this.repaint();

        if(numSlides - 1 == NUM_FRAMES)
        {
                timer.stop();
                game.animationComplete();
        }
    }

    public void updatePlayer(Player player)
    {
        initPlayerImage();
        playerGraphics.drawImage(this.getImage(player.getImage()), 0, 0, null);
    }
}
