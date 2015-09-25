package frontend;

import backend.Game;
import backend.interfaces.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ryan on 9/24/15.
 */
public class Keyboard implements KeyListener, Input
{
    private Game game;
    private boolean inputEnabled;

    public Keyboard()
    {
        disableInput();
    }

    public void keyTyped(KeyEvent keyEvent)
    {
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        if(!inputEnabled)
            return;

        disableInput();
        switch(keyEvent.getKeyCode())
        {
            case KeyEvent.VK_UP:
                game.userInput(Game.Command.MOVE_UP);
                break;
            case KeyEvent.VK_RIGHT:
                game.userInput(Game.Command.MOVE_RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                game.userInput(Game.Command.MOVE_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                game.userInput(Game.Command.MOVE_LEFT);
                break;
            case KeyEvent.VK_R:
                game.userInput(Game.Command.RANDOMIZE);
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                enableInput();
        }

    }

    public void keyReleased(KeyEvent keyEvent)
    {

    }

    //  Input implementation
    public void setGame(Game game)
    {
        this.game = game;
        enableInput();
    }

    public void enableInput()
    {
        this.inputEnabled = true;
    }

    public void disableInput()
    {
        this.inputEnabled = false;
    }
}
