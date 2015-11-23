package frontend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import backend.Game;
import backend.Global;
import backend.interfaces.Input.*;
import backend.Global.Direction;
import backend.interfaces.Input;

public class Keyboard implements KeyListener, Input
{
	private boolean[]	moveCommands, devCommands;

	public Keyboard()
	{
		resetCommands();
	}
	
	@Override
	public void keyPressed(KeyEvent event) 
	{
		setCommand(event, true);		
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		setCommand(event, false);
	}
	
	private void setCommand(KeyEvent event, boolean value)
	{
		Direction moveCommand = getMoveCommand(event);
		DevCommand devCommand = getDevCommand(event);
		
		if(moveCommand != null)
			moveCommands[moveCommand.ordinal()] = value;
		else if(devCommand != null)
			devCommands[devCommand.ordinal()] = value;
	}
	
    private Direction getMoveCommand(KeyEvent keyEvent)
    {
        switch(keyEvent.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                return (Direction.UP);
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                return (Direction.LEFT);
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                return (Direction.RIGHT);
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                return (Direction.DOWN);
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }

        return null;
    }
    
    private DevCommand getDevCommand(KeyEvent keyEvent)
    {
    	switch(keyEvent.getKeyCode())
    	{
	        case KeyEvent.VK_R:
	            return (DevCommand.RANDOMIZE);
    	}
    	
    	return null;
    }
	
	@Override
	public void keyTyped(KeyEvent event){}
	
	private void resetCommands()
	{
		this.moveCommands = new boolean[Direction.values().length];
		for(int i = 0; i < moveCommands.length; i++)
			moveCommands[i] = false;
		
		this.devCommands = new boolean[DevCommand.values().length];
		for(int i = 0; i < devCommands.length; i++)
			devCommands[i] = false;
	}

	@Override
	public boolean[] getMoveCommands() {
		return moveCommands;
	}

	@Override
	public boolean[] getDevCommands() {
		return devCommands;
	}

}
