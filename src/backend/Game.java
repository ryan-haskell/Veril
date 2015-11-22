package backend;

import backend.interfaces.Display;
import backend.interfaces.Input;

/**
 * Game - ties input, output, and world together.
 * Created by ryan on 9/24/15.
 */
public class Game
{
    private Display display;
    private Input input;
    private World world;

    public enum Command {
        MOVE_UP,
        MOVE_RIGHT,
        MOVE_DOWN,
        MOVE_LEFT,
        RANDOMIZE
    }

    public Game(Display display, Input input)
    {
        //  Set up game
        this.display = display;
        this.input = input;
        input.setGame(this);
        display.setGame(this);
        this.world = new World();

        this.updateScreen();
    }

    public void userInput(Command cmd)
    {
        switch (cmd)
        {
            case MOVE_UP:
            case MOVE_RIGHT:
            case MOVE_DOWN:
            case MOVE_LEFT:
                Global.Direction dir = Global.Direction.values()[cmd.ordinal() - Command.MOVE_UP.ordinal()];
                if(world.movePlayer(dir)) {
                    display.updatePlayer(world.getPlayer());
                    display.slide(dir);
                }
                else enableInput();
                break;
            case RANDOMIZE:
                world.generateRandomWorld();
                this.updateScreen();
                enableInput();
                break;
        }

    }

    public void animationComplete()
    {
        this.updateScreen();

        Command nextCommand = input.getNextCommand();

        if(nextCommand != null)
        {
            this.userInput(nextCommand);
        }
        else this.enableInput();
    }

    public void enableInput()
    {
        input.enableInput();
    }

    public void updateScreen()
    {
        display.render(world.getPlayerView(), world.getPlayer());
    }
}
