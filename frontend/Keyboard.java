package frontend;

import backend.Game;
import backend.interfaces.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard - Implements Input interface with the keyboard.
 * Created by ryan on 9/24/15.
 */
public class Keyboard implements KeyListener, Input
{
    private Game game;
    private boolean inputEnabled;

    //  Keeps track of key states
    private Game.Command nextCommand;

    public Keyboard()
    {
        disableInput();
    }

    public void keyTyped(KeyEvent keyEvent){}

    public void keyPressed(KeyEvent keyEvent)
    {
        Game.Command command = getCommand(keyEvent);

        if(command == null)
        {
            enableInput();
        }
        else
        {
            if(!inputEnabled)
            {
                if(command != nextCommand)
                    nextCommand = command;
            }
            else
            {
                disableInput();
                game.userInput(command);
            }

        }

    }

    public void keyReleased(KeyEvent keyEvent)
    {
        Game.Command command = getCommand(keyEvent);

        if(command != null)
        {
            if (command == nextCommand)
            {
                nextCommand = null;
            }
        }
    }

    private Game.Command getCommand(KeyEvent keyEvent)
    {
        switch(keyEvent.getKeyCode())
        {
            case KeyEvent.VK_UP:
                return (Game.Command.MOVE_UP);
            case KeyEvent.VK_RIGHT:
                return (Game.Command.MOVE_RIGHT);
            case KeyEvent.VK_DOWN:
                return (Game.Command.MOVE_DOWN);
            case KeyEvent.VK_LEFT:
                return (Game.Command.MOVE_LEFT);
            case KeyEvent.VK_R:
                return (Game.Command.RANDOMIZE);
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }

        return null;
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

    public Game.Command getNextCommand()
    {
        return nextCommand;
    }
}
