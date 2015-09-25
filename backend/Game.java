package backend;

import backend.interfaces.Display;
import backend.interfaces.Input;

/**
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
                if(world.movePlayer(dir))
                    this.updateScreen();
                break;
            case RANDOMIZE:
                world.generateRandomWorld();
                this.updateScreen();
                break;
        }

        input.enableInput();
    }

    private void updateScreen()
    {
        display.render(world.getPlayerView(), world.getPlayer());
    }
}
