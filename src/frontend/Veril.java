package frontend;

import backend.interfaces.Display;
import backend.Game;
import backend.interfaces.Input;

/**
 * Veril - Launches the game with the main method.
 * Created by ryan on 9/24/15.
 */
public class Veril
{
    public static void main(String args[])
    {
        Input input = new Keyboard();
        Display display = new Frame((Keyboard)input);
        new Game(display, input);
    }
}
